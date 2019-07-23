package com.example.hotel.service;

import com.example.hotel.model.Essay;
import com.example.hotel.model.EssayExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface EssayService {
    long countByExample(EssayExample example);

    int deleteByExample(EssayExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(Essay record);

    int insertSelective(Essay record);

    List<Essay> selectByExampleWithBLOBsWithRowbounds(EssayExample example, RowBounds rowBounds);

    List<Essay> selectByExampleWithBLOBs(EssayExample example);

    List<Essay> selectByExampleWithRowbounds(EssayExample example, RowBounds rowBounds);

    List<Essay> selectByExample(EssayExample example);

    Essay selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") Essay record, @Param("example") EssayExample example);

    int updateByExampleWithBLOBs(@Param("record") Essay record, @Param("example") EssayExample example);

    int updateByExample(@Param("record") Essay record, @Param("example") EssayExample example);

    int updateByPrimaryKeySelective(Essay record);

    int updateByPrimaryKeyWithBLOBs(Essay record);

    int updateByPrimaryKey(Essay record);


    //    自己写的

    List<Essay> selectAll();

    int selectCount();

    List<Essay> selectPage(Map map);

}
