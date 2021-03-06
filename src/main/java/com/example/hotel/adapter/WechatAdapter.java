package com.example.hotel.adapter;

import com.alibaba.fastjson.JSON;
import com.example.hotel.DTO.SessionDTO;
import com.example.hotel.error.CommonErrorCode;
import com.example.hotel.error.ErrorCodeException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by codedrinker on 2018/11/24.
 *
 */
@Service
public class WechatAdapter {
    private final Logger logger = LoggerFactory.getLogger(WechatAdapter.class);
    //从全局配置文件中获取属性值
    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.secret}")
    private String secret;

    /**
     * 利用微信自身的api获取openid和session
     * @param code
     * @return
     */
    public SessionDTO jscode2session(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        OkHttpClient okHttpClient = new OkHttpClient();
        //建造一个request
        Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(String.format(url, appid, secret, code))
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            if (execute.isSuccessful()) {
                SessionDTO sessionDTO = JSON.parseObject(execute.body().string(), SessionDTO.class);
                logger.info("jscode2session get url -> {}, info -> {}", String.format(url, appid, secret, code), JSON.toJSONString(sessionDTO));
                return sessionDTO;
            } else {
                logger.error("jscode2session authorize error -> {}", code);
                throw new ErrorCodeException(CommonErrorCode.OBTAIN_OPENID_ERROR);
            }

        } catch (IOException e) {
            logger.error("jscode2session authorize error -> {}", code, e);
            throw new ErrorCodeException(CommonErrorCode.OBTAIN_OPENID_ERROR);
        }
    }
}
