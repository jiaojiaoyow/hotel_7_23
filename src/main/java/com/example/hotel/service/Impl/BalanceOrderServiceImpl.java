package com.example.hotel.service.Impl;

import com.example.hotel.dao.BalanceOrderMapper;
import com.example.hotel.model.BalanceOrder;
import com.example.hotel.model.Essay;
import com.example.hotel.model.User;
import com.example.hotel.model.VipCard;
import com.example.hotel.service.BalanceOrderService;
import com.example.hotel.service.EssayService;
import com.example.hotel.service.UserService;
import com.example.hotel.service.VipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.sql.SQLException;

@Slf4j
@Service("BalanceOrderService")
public class BalanceOrderServiceImpl implements BalanceOrderService {
    @Autowired
    private BalanceOrderMapper balanceOrderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private VipService vipService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insertSelective(BalanceOrder record) throws Exception {

        return balanceOrderMapper.insertSelective(record);
    }

    @Override
    public BalanceOrder selectByPrimaryKey(String orderid) {
        return balanceOrderMapper.selectByPrimaryKey(orderid);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKey(VipCard vipCard,User user, BalanceOrder record) {
        int flag=0;
        try{
            log.info("修改用户vip信息");
            vipService.updateByPrimaryKey(vipCard);

            log.info("开始修改用户数据库，用户余额等");
            userService.saveOrUpdate(user);

            log.info("修改订单数据库");
            flag =balanceOrderMapper.updateByPrimaryKey(record);
        }
        catch (Exception e){
            if(e instanceof DuplicateKeyException){
                log.error("主键已经存在");
            }else {
                log.error("数据库插入异常");
            }
        }
        return flag;
    }
}
