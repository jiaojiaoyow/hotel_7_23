package com.example.hotel.DTO;

import lombok.Data;

/**
 * Created by codedrinker on 2018/11/25.
 */
@Data
public class TokenDTO {
    private String tokenid;

    private String openid;

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


}