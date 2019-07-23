package com.example.hotel.dao;

import com.example.hotel.model.Room;
import com.example.hotel.model.TroRoom;
import com.example.hotel.model.TroRoomExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TroRoomMapper {
    long countByExample(TroRoomExample example);

    int deleteByExample(TroRoomExample example);

    int deleteByPrimaryKey(String roomintroduce);

    int insert(TroRoom record);

    int insertSelective(TroRoom record);

    List<TroRoom> selectByExampleWithRowbounds(TroRoomExample example, RowBounds rowBounds);

    List<TroRoom> selectByExample(TroRoomExample example);

    TroRoom selectByPrimaryKey(String roomintroduce);

    int updateByExampleSelective(@Param("record") TroRoom record, @Param("example") TroRoomExample example);

    int updateByExample(@Param("record") TroRoom record, @Param("example") TroRoomExample example);

    int updateByPrimaryKeySelective(TroRoom record);

    int updateByPrimaryKey(TroRoom record);

    List<TroRoom> selectAllTroRoom();

    int selectCount();
    List<TroRoom> selectRoomPage(Map map);

}