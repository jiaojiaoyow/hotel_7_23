package com.example.hotel.dao;

import com.example.hotel.model.Activity;
import com.example.hotel.model.ActivityExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(Integer actid);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExampleWithRowbounds(ActivityExample example, RowBounds rowBounds);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Integer actid);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> selectAll();
}