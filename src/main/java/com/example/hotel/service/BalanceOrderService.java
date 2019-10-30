package com.example.hotel.service;

import com.example.hotel.model.BalanceOrder;
import com.example.hotel.model.User;
import com.example.hotel.model.VipCard;

public interface BalanceOrderService {

    int insertSelective (BalanceOrder record) throws Exception ;

    BalanceOrder selectByPrimaryKey(String orderid);

    int updateByPrimaryKey(VipCard vipCard,User user, BalanceOrder record);
}
