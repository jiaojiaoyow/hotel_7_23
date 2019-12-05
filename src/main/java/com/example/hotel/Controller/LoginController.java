package com.example.hotel.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hotel.DTO.*;
import com.example.hotel.adapter.WechatAdapter;
import com.example.hotel.error.CommonErrorCode;
import com.example.hotel.error.ErrorCodeException;
import com.example.hotel.model.BalanceOrder;
import com.example.hotel.model.User;
import com.example.hotel.model.VipCard;
import com.example.hotel.service.BalanceOrderService;
import com.example.hotel.service.UserService;
import com.example.hotel.service.VipService;
import com.example.hotel.util.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.example.hotel.util.DateUtil.change_str2;

/**
 * 不要在意get和set方法的
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private WechatAdapter wechatAdapter;

    @Autowired
    private UserService userService;

    @Autowired
    private VipService vipService;

    @Autowired
    private BalanceOrderService balanceOrderService;

    @RequestMapping("/api/login")
    public ResultDTO login( LoginDTO loginDTO){
        ResultDTO resultDTO=new ResultDTO();
        try{
            //HttpSession session=
            //使用code调用微信api来获取openid和
            SessionDTO sessionDTO=wechatAdapter.jscode2session(loginDTO.getCode());
            //检验传递过来的用户信息是否合法
            //DigestUtil.checkDigest(loginDTO.getRawData(),sessionDTO.getSessionKey(),loginDTO.getSignature());
            //保存微信来的数据
            System.out.println(loginDTO.getRawData());
            User user=JSON.parseObject(loginDTO.getRawData(),User.class);
            UserDTO user1=JSON.parseObject(loginDTO.getRawData(),UserDTO.class);
            user.setUid(sessionDTO.getOpenid());
            userService.saveOrUpdate(user);
            if (user==null){
                return resultDTO.fail();
            }
            return resultDTO.ok(user);

        } catch (Exception e) {
            resultDTO.unkonwFail(e.toString());
            return null;
        }
    }

    /*
    充值接口
    充值的id为100+openid
     */
    @RequestMapping("/api/reCharge")
    public ResultDTO reCharge(String openid, double price, HttpServletRequest request){
        ResultDTO resultDTO=new ResultDTO();
        try
        {

            if(openid==null&&price==0){
                return resultDTO.fail("没有接受到数据");
            }
            Date date=new Date();
            int ram=10000+(int)(Math.random()*10000);
            String orderid=change_str2(date)+String.valueOf(ram);//10000-19999随机数+当前时间构成订单号

            BalanceOrder balanceOrder=new BalanceOrder();
            //首先设置一个订单，设置为未支付
            balanceOrder.setOrderid(orderid);
            balanceOrder.setFlag(0);
            balanceOrder.setOpenid(openid);
            balanceOrder.setCreateTime(change_str2(date));
            balanceOrder.setBalance(price);
            balanceOrderService.insertSelective(balanceOrder);
            //调用微信支付,openid，订单id，金额，加一个回调函数
            JSONObject json = WeChatPay.wxPay(openid,orderid,price,"https://wx.gdcpo.cn/api/reChargeBack",request);

            if(json.get("errMsg")==null){
                return resultDTO.ok(json);
            }
            else{
                return resultDTO.fail("错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resultDTO.fail("未成功支付");
        }

    }


    /*
    个人充值后接口
     */
    @RequestMapping("/api/reChargeBack")
    public synchronized void reChargeBackPay(HttpServletRequest request, HttpServletResponse response){
        try {
            Map map=WeChatPay.wxNotify(request, response);

            if(map!=null){
                log.info("回调函数的下一步,修改数据库"+map.toString());
                String openid=(String) map.get("openid");
                log.info("openid为"+openid);
                double price=Double.parseDouble((String)map.get("total_fee"));
                String orderid=(String) map.get("out_trade_no");
                BalanceOrder balanceOrder=balanceOrderService.selectByPrimaryKey(orderid);
                log.info("价格为"+price+"openid为"+openid+"oreder_id为"+orderid);
                if(balanceOrder==null){
                    log.info("没有此订单");
                }
                if(balanceOrder.getFlag()==0){
                    log.info("进入订单");
                    User user=userService.selectByPrimaryKey(openid);
                    //贵宾卡，金额是以百元为单位（精确到分），所以要除一百，变成以元为单位
                    price/=100;
                    if(user.getUgrade()==null){
                        user.setUgrade(0);
                    }
                    if(price>=5000){
                        log.info("金额超过5000");
                        user.setUgrade(user.getUgrade()+1288);
                        price+=1288;
                    }else if(price>=3000){
                        log.info("金额超过3000");
                        user.setUgrade(user.getUgrade()+800);
                        price+=800;
                    }else if(price>=1000){
                        log.info("金额超过1000");
                        user.setUgrade(user.getUgrade()+179);
                        price+=179;
                    }else if(price>=500){
                        log.info("金额超过500");
                        user.setUgrade(user.getUgrade()+50);
                        price+=50;
                    }
                    if(user.getUbalance()==null){
                        user.setUbalance(price);
                    }else {
                        user.setUbalance(price+user.getUbalance());
                        log.info("改变用户充值金额为"+user.getUbalance());
                    }
                    log.info("改变用户充值金额为"+user.getUbalance());
                    VipCard vipCard=null;
                    if(user.getVip()!=null){
                        vipCard=vipService.selectByPrimaryKey(user.getVip());
                        //累积的金额到达限额就升级会员
                        log.info("改变用户的累计充值金额");
                        if(user.getTotalamount()==null){
                            user.setTotalamount((int)price);
                        } else {
                            user.setTotalamount(user.getTotalamount()+(int)price);
                            int totalamount=user.getTotalamount();
                            if(totalamount>=5000){
                                log.info("累计金额超过5000");
                                vipCard.setViplevel(3);
                            }else if(totalamount>=3000){
                                log.info("累计金额超过3000");
                                vipCard.setViplevel(2);
                            }
                        }
                    }
                    log.info("设置为已支付");
                    balanceOrder.setFlag(1);
                    log.info("修改订单数据库");
                    balanceOrderService.updateByPrimaryKey(vipCard,user,balanceOrder);
                    log.info("订单修改已支付");
                }
            }
        }catch (NullPointerException e){
            log.info("空值错误");
        }
        catch (Exception e){
            log.info("其他错误"+e.toString());
        }
    }

    //获取所有的信息，包括是否会员，会员等级，余额，昵称，累计充值金额，
    //获取所有的信息，包括是否会员，会员等级，余额，昵称，累计充值金额，
    @RequestMapping("/api/selUserMessage")
    public ResultDTO getmessage(String openid){
        ResultDTO resultDTO=new ResultDTO();
        if(openid==null){
            return resultDTO.nothing();
        }
        try{
            AllUserMesDto allUserMesDto=new AllUserMesDto();
            User user=userService.selectByPrimaryKey(openid);
            VipCard vip=null;
            if(user!=null){
                if(user.getVip()!=null){
                    vip=vipService.selectByPrimaryKey(user.getVip());
                    if(vip!=null){
                        allUserMesDto.setViplevel(vip.getViplevel());
                    }else {
                        allUserMesDto.setViplevel(0);
                    }
                }else {
                    allUserMesDto.setViplevel(0);
                }
            }

            allUserMesDto.setPhone(vip.getPhone());
            allUserMesDto.setUsername(vip.getUsername());
            allUserMesDto.setNickname(user.getNickname());
            allUserMesDto.setTotalamount(user.getTotalamount());
            allUserMesDto.setUbalance(user.getUbalance());
            allUserMesDto.setUgrade(user.getUgrade());
            return resultDTO.ok(allUserMesDto);
        }catch (Exception e){
            return resultDTO.fail(e.toString());
        }
    }

//    @RequestMapping("/api/reChargeBacktest")
//    public void reChargeBackPaytest(String openid, double price,String orderid){
//        try {
//                System.out.println("回调函数的下一步,修改数据库"+orderid);
//
//                BalanceOrder balanceOrder=balanceOrderService.selectByPrimaryKey(orderid);
//                System.out.println("价格为"+price+"openid为"+openid+"oreder_id为"+orderid);
//                if(balanceOrder==null){
//                    System.out.println("没有此订单");
//                }
//                if(balanceOrder.getFlag()==0){
//                    System.out.println("进入订单");
//                    User user=userService.selectByPrimaryKey(openid);
//                    //贵宾卡，金额是以分为单位，所以要除一百，变成以元为单位
//                    price/=100;
//                    if(price>=5000){
//                        user.setUgrade(user.getUgrade()+1288);
//                        price+=1288;
//                    }else if(price>=3000){
//                        user.setUgrade(user.getUgrade()+800);
//                        price+=800;
//                    }else if(price>=1000){
//                        user.setUgrade(user.getUgrade()+179);
//                        price+=179;
//                    }else if(price>=500){
//                        user.setUgrade(user.getUgrade()+50);
//                        price+=50;
//                    }
//                    if(user.getUbalance()==null){
//                        user.setUbalance(price);
//                    }else {
//                        user.setUbalance(price+user.getUbalance());
//                    }
//                    System.out.println("改变金额为"+user.getUbalance());
//                    if(user.getVip()!=null){
//                        //累积的金额到达限额就升级会员
//                        if(user.getTotalamount()!=null&&user.getTotalamount()<5000){
//                            user.setTotalamount(user.getTotalamount()+(int)price);
//                            int totalamount=user.getTotalamount();
//                            if(totalamount>=5000){
//                                VipCard vipCard=vipService.selectByPrimaryKey(user.getVip());
//                                vipCard.setViplevel(3);
//                                vipService.updateByPrimaryKey(vipCard);
//                            }else if(totalamount>=3000){
//                                VipCard vipCard=vipService.selectByPrimaryKey(user.getVip());
//                                vipCard.setViplevel(2);
//                                vipService.updateByPrimaryKey(vipCard);
//                            }
//                        }
//                    }
//                    System.out.println("开始修改数据库");
//                    userService.saveOrUpdate(user);
//                    System.out.println("修改数据库");
//                    //设置为已支付
//                    balanceOrder.setFlag(1);
//                    balanceOrderService.updateByPrimaryKey(balanceOrder);
//                }
//
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }
//    }

}
