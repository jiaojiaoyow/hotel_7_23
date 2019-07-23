package com.example.hotel.DTO;

import lombok.Data;

/**
 * Created by codedrinker on 2018/11/24.
 */
@Data
public class UserDTO {

    // 微信昵称
    private String nickName;

    //性别
    private Integer gender;

    // 语言
    private String language;

    // 头像
    private String avatarUrl;

    // 国家
    private String country;

    // 省份
    private String province;

    // 城市
    private String city;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
