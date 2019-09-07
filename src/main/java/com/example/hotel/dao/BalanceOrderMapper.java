package com.example.hotel.dao;

import com.example.hotel.model.BalanceOrder;
import com.example.hotel.model.BalanceOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BalanceOrderMapper {
    long countByExample(BalanceOrderExample example);

    int deleteByExample(BalanceOrderExample example);

    int deleteByPrimaryKey(String orderid);

    int insert(BalanceOrder record);

    int insertSelective(BalanceOrder record);

    List<BalanceOrder> selectByExampleWithRowbounds(BalanceOrderExample example, RowBounds rowBounds);

    List<BalanceOrder> selectByExample(BalanceOrderExample example);

    BalanceOrder selectByPrimaryKey(String orderid);

    int updateByExampleSelective(@Param("record") BalanceOrder record, @Param("example") BalanceOrderExample example);

    int updateByExample(@Param("record") BalanceOrder record, @Param("example") BalanceOrderExample example);

    int updateByPrimaryKeySelective(BalanceOrder record);

    int updateByPrimaryKey(BalanceOrder record);
}