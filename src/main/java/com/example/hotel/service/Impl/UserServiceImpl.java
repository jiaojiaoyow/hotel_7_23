package com.example.hotel.service.Impl;

import com.example.hotel.dao.UserMapper;
import com.example.hotel.model.User;
import com.example.hotel.model.UserExample;
import com.example.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    public void saveOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andUidEqualTo(user.getUid());
        try {
            List<User> users = userMapper.selectByExample(example);

            // 先查看是否有，如果有更新，没有创建
            if (users != null && users.size() != 0) {
                user.setGmtModified(System.currentTimeMillis());
                userMapper.updateByExampleSelective(user, example);
            } else {
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(System.currentTimeMillis());
                userMapper.insert(user);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public User selectByPrimaryKey(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKeyForBalance(String userid, Double rebalance) {
        return userMapper.updateByPrimaryKeyForBalance(userid,rebalance);
    }

    @Override
    public User selectByVip(String vip) {
        return userMapper.selectByVip(vip);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


}
