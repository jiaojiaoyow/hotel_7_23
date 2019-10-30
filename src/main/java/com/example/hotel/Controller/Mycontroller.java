package com.example.hotel.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.DTO.WXConst;
import com.example.hotel.model.RoomOrder;
import com.example.hotel.service.RoomOrderService;
import com.example.hotel.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@Slf4j
public class Mycontroller {

    @Autowired
    private RoomOrderService roomOrderService;

    @RequestMapping("/api/getorder")
    public ResultDTO getOrder(String openid) {  //返回所有订单
        ResultDTO resultDTO = new ResultDTO();
        try {
            if(openid !="" && !openid.equals("")) {
                List<RoomOrder> roomOrder = new ArrayList<RoomOrder>();
                roomOrder = roomOrderService.selectByUserid(openid);
                log.info("返回所有订单成功。");

                return ResultDTO.ok(roomOrder);
            }
            return ResultDTO.fail("openid为空");
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }

    /*
     * 重新发起支付
     *
     * */
    @RequestMapping("/api/back/rePayOrder")
    public ResultDTO rePayOrder(String orderid, HttpServletRequest request){
        ResultDTO resultDTO = new ResultDTO();

        if(orderid !=null) {
            RoomOrder roomOrder=roomOrderService.selectByOrderidqu(orderid);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime= DateUtil.change_str3(new Date());
                String endTime= roomOrder.getCreatedate();
            try {
                Date date1 = format.parse(nowTime);
                Date date2 = format.parse(endTime);

                long beginMillisecond = date1.getTime();
                long endMillisecond = date2.getTime();
                if((beginMillisecond - endMillisecond )/1000 >= 60*17){

                    return resultDTO.fail("订单失效");


                }


            } catch (ParseException e) {
                e.printStackTrace();
            }





                System.out.println(roomOrder.toString());
                if(roomOrder.getOrderstatus()== 1){   //判断订单是否有效
                    log.info("first ------------");
                    JSONObject json=  WeChatPay.wxPay(roomOrder.getUid(),roomOrder.getOrderid(),roomOrder.getTotalprice(),WXConst.WxGetOpenIdUrl,request);
                    log.info("------再次调起微信支付------");
                    log.info("----"+json+"-----发起成功-----");
                    return resultDTO.ok(json);
                }
            return resultDTO.fail("订单失效");

        }
        return resultDTO.fail("发起失败！");
    }

    /*
     *
     * 返回个人
     */
}