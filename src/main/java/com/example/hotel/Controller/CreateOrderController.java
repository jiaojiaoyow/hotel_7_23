package com.example.hotel.Controller;


import com.alibaba.fastjson.JSONObject;
import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.DTO.cOrderDTO;
import com.example.hotel.error.OrderException;
import com.example.hotel.model.*;

import com.example.hotel.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;



import static com.example.hotel.util.DateUtil.*;

@Slf4j
@RestController
public class CreateOrderController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomOrderService roomOrderService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private GetCouponService getCouponService;

    //检查优惠卷
    public  Double getCoupon(int cid,String uid){
        if(cid != 0  && uid !=null && !uid.equals("")) {
            GetCouponKey key=new GetCouponKey();
            key.setCid(cid);
            key.setUid(uid);
            GetCoupon gc=getCouponService.selectByPrimaryKey(key);//获取用户领取的优惠卷信息
            Coupon coupon = couponService.selectByPrimaryKey(cid);
            //if(gc.getUseEndDate())
            if (coupon != null ) {
                Double amount = coupon.getAmount();
                return amount;
            }
        }
        return 0.0;
    }

    //设定优惠卷生效
    public void setCoupon(String uid,int cid){
        if(cid!=0 ) {
            Byte x = 1;
            GetCoupon key = new GetCoupon();
            key.setUid(uid);
            key.setCid(cid);
            key.setStatus(x);  //优惠卷设定为1，标记为已使用
            getCouponService.updateByPrimaryKeySelective(key);//优惠券生效
        }


    }

    //支付回调地址
    @RequestMapping("/api/backPay")
    public void BackPay(HttpServletRequest request, HttpServletResponse response){
        try {

            Map map=WeChatPay.wxNotify(request, response);
            if(map!=null){
                String orderid=(String) map.get("out_trade_no");
                String openid=(String) map.get("openid");
                cOrderDTO cOrderDTO=new cOrderDTO(openid,orderid);
                JudgeOrder( cOrderDTO);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    //调起支付,测试用
    @RequestMapping("/api/TestPay")
    public JSONObject  TestPay(@RequestBody String openid, HttpServletRequest request) {

        String []temp;
        String tem="";
        temp=openid.split(tem);
        System.out.println(temp[3]);
        Date date=new Date();
        System.out.println("openid:"+temp[3]);
        openid= temp[3];
        JSONObject json=WeChatPay.wxPay(openid,"100"+change_str2(date),0.01,"https://wx.gdcpo.cn/api/backPay",request);//调用微信支付
        return json;

    }

    //创建订单，微信支付调用
    @RequestMapping("/api/createorder")
    public ResultDTO createOrder( RoomOrder roomOrder, HttpServletRequest request){
        ResultDTO resultDTO = new ResultDTO();
        try {
            System.out.println(roomOrder.toString());
            User user=userService.selectByPrimaryKey(roomOrder.getUid());
            System.out.println("user.uid"+user.getUid());
            //ookL25Q9bypvjSm8uYYJud6R0JIU
            if(roomOrder !=null  && user.getUid()!=null ) {

                String uid = roomOrder.getUid();                 //"1234";
                String roomname = roomOrder.getRoomname();       //"阳光大床房";
                String ordertime = roomOrder.getOrdertime();     //"2019-06-20";
                String leavetime = roomOrder.getLeavetime();     //"2019-06-21";
                int days = roomOrder.getOrderday();             //2;
                int roomnumber = roomOrder.getRoomnumber();     //2;
                String username = roomOrder.getUname();         //"许文强";
                String uphone = roomOrder.getUphone();          //"18316102612";
                String arrivetime = roomOrder.getArrivetime();    //到达时间
                Room r = roomService.selectByPrimaryKey(roomname);//查询roomname的房间信息
                if (r != null) {
                    int num = r.getRoomnumber(); //剩余房间数
                    if (num > 0 && num - roomnumber >= 0) {
                        // double amount = getCoupon(roomOrder.getCid(), uid);
                        //double price = r.getRoomprice() * roomnumber * days - amount; //总价，算优惠卷
                        double price=roomOrder.getTotalprice();
                        Date date=new Date();
                        String s=change_str3(date);
                        ordertime += s.substring(10);
                        leavetime += s.substring(10);
                        RoomOrder order = new RoomOrder();

                        int ram=10000+(int)(Math.random()*10000);
                        String orderid=change_str2(date)+String.valueOf(ram);//100+当前时间构成订单号
                        order.setOrderid(orderid);
                        order.setUid(uid);
                        order.setRoomnumber(roomnumber);
                        order.setUname(username);
                        order.setUphone(uphone);
                        order.setRoomname(roomname);
                        order.setTotalprice(price);
                        order.setOrdertime(ordertime);
                        order.setLeavetime(leavetime);
                        order.setArrivetime(arrivetime);
                        order.setOrderday(days);
                        order.setCreatedate(s);


                        OrderException oe=roomOrderService.beforehandOrder(order);  //预创建订单

                        if(oe.isFlag()){
                            log.info("创建订单成功 ："+order.toString()+"---------");
                            log.info("----开始调用微信支付-----");
                            JSONObject json = WeChatPay.wxPay(uid,orderid,price,"https://wx.gdcpo.cn/api/backPay",request);
                            return resultDTO.ok(json);
                        }else {
                            log.info("----房间不足-----");
                            System.out.println("房间不足");
                            return resultDTO.fail("房间不足！");
                        }


                        // JSONObject json=wxPay(uid,"100"+change_str2(date),request);//调用微信支付

                        //cOrderDTO cd = new cOrderDTO(uid, orderid, 1, 0.0);

                        //return cd;
                    } else {
                        System.out.println("房间不足");
                        return resultDTO.fail("房间不足！");
                    }

                }
            }
        }catch(Exception e){

            log.info("----未知错误-----");
            return resultDTO.unkonwFail(e.toString());

        }
        log.info("----数据为空或用户不存在-----");
        System.out.println("数据为空或用户不存在");
        return  resultDTO.fail("数据为空或用户不存在！");
    }


    //创建订单，余额支付调用
    @RequestMapping("/api/balancePay")
    public ResultDTO balancePay( RoomOrder roomOrder){
        ResultDTO resultDTO = new ResultDTO();
        try {
            System.out.println("roomname: " + roomOrder.getRoomname());
            System.out.println("uid: " + roomOrder.getUid());
            User user=userService.selectByPrimaryKey(roomOrder.getUid());

            if(user.getUbalance()> roomOrder.getTotalprice()) {
                //HttpServletRequest request
                if (roomOrder != null && roomOrder.getUid() != null ) {

                    String uid = roomOrder.getUid();                 //"1234";
                    String roomname = roomOrder.getRoomname();       //"阳光大床房";
                    String ordertime = roomOrder.getOrdertime();     //"2019-06-20";
                    String leavetime = roomOrder.getLeavetime();     //"2019-06-21";
                    int days = roomOrder.getOrderday();             //2;
                    int roomnumber = roomOrder.getRoomnumber();     //2;
                    String username = roomOrder.getUname();         //"许文强";
                    String uphone = roomOrder.getUphone();          //"18316102612";
                    String arrivetime = roomOrder.getArrivetime();    //到达时间
                    Room r = roomService.selectByPrimaryKey(roomname);//查询roomname的房间信息
                    if (r != null) {
                        int num = r.getRoomnumber(); //剩余房间数
                        if (num > 0 && num - roomnumber >= 0) {
                            //double amount = getCoupon(roomOrder.getCid(), uid);
                            //double price = r.getRoomprice() * roomnumber * days - amount; //总价，算优惠卷
                            double price = roomOrder.getTotalprice();
                            Date date = new Date();
                            String s = change_str3(date);
                            ordertime += s.substring(10);
                            leavetime += s.substring(10);
                            RoomOrder order = new RoomOrder();


                            int ram = 10000 + (int) (Math.random() * 10000);
                            String orderid = change_str2(date) + String.valueOf(ram);//10000+当前时间构成订单号
                            order.setOrderid(orderid);
                            order.setUid(uid);
                            order.setRoomnumber(roomnumber);
                            order.setUname(username);
                            order.setUphone(uphone);
                            order.setRoomname(roomname);
                            order.setTotalprice(price);
                            order.setOrdertime(ordertime);
                            order.setLeavetime(leavetime);
                            order.setArrivetime(arrivetime);
                            order.setOrderday(days);
                            order.setCreatedate(s);

                            OrderException oe = roomOrderService.beforehandOrder(order);  //预创建订单
                            System.out.println("---------预创建订单成功--------------");
                            if (oe.isFlag()) {
                                //调用余额支付
                                RoomOrder roo = new RoomOrder();
                                roo.setUid(uid);
                                roo.setOrderid(orderid);
                                roo.setRoomname(roomname);
                                roo.setOrderstatus(2);
                                roomOrderService.updateByPrimaryKeySelective(roo);//更改订单为2 成功
                                userService.updateByPrimaryKeyForBalance(uid, price);//减余额
                                setCoupon(uid, roomOrder.getCid());//优惠卷生效
                                SetCard(uid, price);
                                return resultDTO.ok(null);
                            } else {
                                System.out.println("房间不足");
                                return resultDTO.fail("房间不足！");
                            }


                        } else {
                            System.out.println("房间不足");
                            return resultDTO.fail("房间不足！");
                        }

                    }
                }
            }
        }catch(Exception e){
            return resultDTO.unkonwFail(e.toString());

        }
        System.out.println("数据为空或用户不存在");
        return  resultDTO.fail("数据为空或余额不足！");


    }


    /*
     *
     * @param uid
     * @param orderid
     * @param  status 订单状态
     * */
    public  ResultDTO JudgeOrder( cOrderDTO Dt){
        ResultDTO resultDTO = new ResultDTO();
        try {

            if(Dt !=null){
                User u=userService.selectByPrimaryKey(Dt.getUid());//查用户是否存在
                RoomOrder re=roomOrderService.selectByOrderidqu(Dt.getOrderid());//查订单信息

                if( Dt.getUid().equals(re.getUid()) &&  re.getOrderstatus()==1 && re.getUid().equals(u.getUid())) { //uid相同且订单状态为1,and uid存在
                    RoomOrder roomOrder=new RoomOrder();
                    roomOrder.setUid(Dt.getUid());
                    roomOrder.setOrderid(re.getOrderid());
                    roomOrder.setRoomname(re.getRoomname());
                    roomOrder.setOrderstatus(2);
                    roomOrderService.updateByPrimaryKeySelective(roomOrder);//更改订单为2 成功

                    //设定优惠卷生效
                    setCoupon(re.getUid(),re.getCid());
                    //积分
                    SetCard(Dt.getUid(),re.getTotalprice());
                    return resultDTO.ok(null);

                }
                return  resultDTO.fail("数据有误，用户id不符合");
            }
            return  resultDTO.fail("数据为空");

        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }


    }


    /*
     * 增加积分
     * */
    public void SetCard(String openid, double price){

        User user =new User();
        int jifen=((int) price)*5;
        user.setUid(openid);
        user.setUgrade(jifen);
        userService.updateByPrimaryKeyForGrade(user);
        System.out.println("增加积分成功:"+jifen);


    }


    //获取当前时间
    public static String DateToString() {
        String dateStr = "hhh";

        try{
            java.util.Date  date = new Date();
            SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = DF.format(date);

        } catch (Exception e) {
            System.out.println("Date error");
        }
        return dateStr;
    }




}
