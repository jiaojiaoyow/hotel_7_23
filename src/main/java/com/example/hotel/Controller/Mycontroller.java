package com.example.hotel.Controller;

import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.model.RoomOrder;
import com.example.hotel.service.RoomOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Mycontroller {

    @Autowired
    private RoomOrderService roomOrderService;


    @RequestMapping("/api/getorder")
    public ResultDTO getOrder(String openid) {  //返回的是已经成功支付的订单
        ResultDTO resultDTO = new ResultDTO();
        try {
            if(openid !="" && !openid.equals("")) {
                List<RoomOrder> roomOrder = new ArrayList<RoomOrder>();
                roomOrder = roomOrderService.selectByUserid(openid);
                System.out.println("返回所有已支付订单成功。");

                return ResultDTO.ok(roomOrder);
            }
            return ResultDTO.fail("openid为空");
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }
}