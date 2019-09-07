package com.example.hotel.Controller;

import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.model.RoomOrder;
import com.example.hotel.model.TroRoom;
import com.example.hotel.service.RoomOrderService;
import com.example.hotel.service.TroRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class adminController{

    @RequestMapping("/api/back/hello")
    public String  ResponseHtml(){

        return "index";
    }





}