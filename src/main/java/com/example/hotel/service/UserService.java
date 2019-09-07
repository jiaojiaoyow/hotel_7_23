package com.example.hotel.service;

import com.example.hotel.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by codedrinker on 2018/11/29.
 */

public interface UserService {
    public void saveOrUpdate(User user);

    User selectByPrimaryKey(String uid);

    public int updateByPrimaryKeyForBalance(String userid, Double rebalance);

    User  selectByVip(String vip);

    int updateByPrimaryKeySelective(User record);





}
