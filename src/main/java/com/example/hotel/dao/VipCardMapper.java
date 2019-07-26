package com.example.hotel.dao;

import com.example.hotel.model.VipCard;
import com.example.hotel.model.VipCardExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VipCardMapper {
    long countByExample(VipCardExample example);

    int deleteByExample(VipCardExample example);

    int deleteByPrimaryKey(String vid);

    int insert(VipCard record);

    int insertSelective(VipCard record);

    List<VipCard> selectByExampleWithRowbounds(VipCardExample example, RowBounds rowBounds);

    List<VipCard> selectByExample(VipCardExample example);

    VipCard selectByPrimaryKey(String vid);

    int updateByExampleSelective(@Param("record") VipCard record, @Param("example") VipCardExample example);

    int updateByExample(@Param("record") VipCard record, @Param("example") VipCardExample example);

    int updateByPrimaryKeySelective(VipCard record);

    int updateByPrimaryKey(VipCard record);

    //自己添加的
    List<VipCard> selectAllCard(Map map);

    int selectCount();
}