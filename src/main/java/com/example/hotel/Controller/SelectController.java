package com.example.hotel.Controller;

import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.model.RoomOrder;
import com.example.hotel.model.VipCard;
import com.example.hotel.service.RoomOrderService;
import com.example.hotel.service.RoomService;
import com.example.hotel.service.UserService;
import com.example.hotel.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SelectController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomOrderService roomOrderService;

    @Autowired
    private VipService vipService;

    /*
     *  one 成功支付待入住 2
     *  two 已入住 4
     *  three 已退房离开 5
     */

    @RequestMapping("/api/back/selectPayone")
    public ResultDTO selectPayone(String uphone){

        ResultDTO resultDTO = new ResultDTO();
        try{
        List<RoomOrder> data = new ArrayList<RoomOrder>();
        data=roomOrderService.selectByPhone(2,uphone);
        return  resultDTO.ok(data);

            }catch (Exception e){
                return resultDTO.unkonwFail(e.toString());
            }
    }

    @RequestMapping("/api/back/selectPaytwo")
    public ResultDTO selectPaytwo(String uphone){

        ResultDTO resultDTO = new ResultDTO();
        try{
        List<RoomOrder> data = new ArrayList<RoomOrder>();
        data=roomOrderService.selectByPhone(4,uphone);
        return  resultDTO.ok(data);
    }catch (Exception e){
        return resultDTO.unkonwFail(e.toString());
    }
    }


    @RequestMapping("/api/back/selectPaythree")
    public ResultDTO selectPaythree(String uphone){
        ResultDTO resultDTO = new ResultDTO();
        try {
            List<RoomOrder> data = new ArrayList<RoomOrder>();
            data = roomOrderService.selectByPhone(5, uphone);
            return resultDTO.ok(data);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    /*
    * 按手机号查询vip
    * */
    @RequestMapping("/api/back/selectVipByUphone")
    public ResultDTO selectVipByUphone(String phone){

        ResultDTO resultDTO = new ResultDTO();

        try{
            List<VipCard> data = new ArrayList<VipCard>();
            data =vipService.selectVipByUphone(phone);
            return resultDTO.ok(data);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }

    }







}
