package com.example.hotel.dao;

import com.example.hotel.model.GoodsOrder;
import com.example.hotel.model.GoodsOrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodsOrderMapper {
    long countByExample(GoodsOrderExample example);

    int deleteByExample(GoodsOrderExample example);

    int deleteByPrimaryKey(Integer gorderid);

    int insert(GoodsOrder record);

    int insertSelective(GoodsOrder record);

    List<GoodsOrder> selectByExampleWithRowbounds(GoodsOrderExample example, RowBounds rowBounds);

    List<GoodsOrder> selectByExample(GoodsOrderExample example);

    GoodsOrder selectByPrimaryKey(Integer gorderid);

    int updateByExampleSelective(@Param("record") GoodsOrder record, @Param("example") GoodsOrderExample example);

    int updateByExample(@Param("record") GoodsOrder record, @Param("example") GoodsOrderExample example);

    int updateByPrimaryKeySelective(GoodsOrder record);

    int updateByPrimaryKey(GoodsOrder record);

    int selectCount();

    List<GoodsOrder> selectPage(Map map);

    List<GoodsOrder> selectByUidPage(@Param("begin")Integer begin,@Param("num")Integer num,@Param("uid")String uid);
}