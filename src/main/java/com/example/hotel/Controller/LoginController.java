package com.example.hotel.Controller;

import com.example.hotel.DTO.*;
import com.example.hotel.adapter.WechatAdapter;
import com.example.hotel.error.CommonErrorCode;
import com.example.hotel.error.ErrorCodeException;
import com.example.hotel.model.User;
import com.example.hotel.service.UserService;
import com.example.hotel.util.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 不要在意get和set方法的
 */
@RestController
public class LoginController {
    @Autowired
    private WechatAdapter wechatAdapter;

    @Autowired
    private UserService userService;

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


}
