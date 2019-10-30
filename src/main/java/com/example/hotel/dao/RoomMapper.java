package com.example.hotel.dao;

import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.model.Room;
import com.example.hotel.model.RoomExample;
import java.util.List;
import java.util.Map;

import com.example.hotel.model.RoomOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoomMapper {
    long countByExample(RoomExample example);

    int deleteByExample(RoomExample example);

    int deleteByPrimaryKey(String roomname);

    int insert(Room record);

    int insertSelective(Room record);

    List<Room> selectByExampleWithRowbounds(RoomExample example, RowBounds rowBounds);

    List<Room> selectByExample(RoomExample example);

    Room selectByPrimaryKey(String roomname);

    int updateByExampleSelective(@Param("record") Room record, @Param("example") RoomExample example);

    int updateByExample(@Param("record") Room record, @Param("example") RoomExample example);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    int updateByPrimaryKeyForNum(@Param("rname") String rname,@Param("roomnum") int roomnum);

    int updateByPrimaryKeyForReduce(@Param("rname") String rname,@Param("roomnum") int roomnum);

    int selectCount();

    List<RoomDTO> selectall(Map map);




}