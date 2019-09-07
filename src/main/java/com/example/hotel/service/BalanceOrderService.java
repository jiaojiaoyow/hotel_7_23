package com.example.hotel.service;

import com.example.hotel.model.BalanceOrder;

public interface BalanceOrderService {

    int insertSelective(BalanceOrder record);

    BalanceOrder selectByPrimaryKey(String orderid);

    int updateByPrimaryKey(BalanceOrder record);
}
