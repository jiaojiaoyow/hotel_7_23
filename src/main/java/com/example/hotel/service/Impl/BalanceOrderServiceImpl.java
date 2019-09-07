package com.example.hotel.service.Impl;

import com.example.hotel.dao.BalanceOrderMapper;
import com.example.hotel.model.BalanceOrder;
import com.example.hotel.service.BalanceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BalanceOrderService")
public class BalanceOrderServiceImpl implements BalanceOrderService {
    @Autowired
    private BalanceOrderMapper balanceOrderMapper;

    @Override
    public int insertSelective(BalanceOrder record) {
        return balanceOrderMapper.insertSelective(record);
    }

    @Override
    public BalanceOrder selectByPrimaryKey(String orderid) {
        return balanceOrderMapper.selectByPrimaryKey(orderid);
    }

    @Override
    public int updateByPrimaryKey(BalanceOrder record) {
        return balanceOrderMapper.updateByPrimaryKey(record);
    }
}
