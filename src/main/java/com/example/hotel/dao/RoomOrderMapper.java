package com.example.hotel.dao;

import com.example.hotel.model.RoomOrder;
import com.example.hotel.model.RoomOrderExample;
import com.example.hotel.model.RoomOrderKey;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoomOrderMapper {
    long countByExample(RoomOrderExample example);

    int deleteByExample(RoomOrderExample example);

    int deleteByPrimaryKey(RoomOrderKey key);

    int insert(RoomOrder record);

    int insertSelective(RoomOrder record);

    List<RoomOrder> selectByExampleWithRowbounds(RoomOrderExample example, RowBounds rowBounds);

    List<RoomOrder> selectByExample(RoomOrderExample example);

    RoomOrder selectByPrimaryKey(RoomOrderKey key);

    int updateByExampleSelective(@Param("record") RoomOrder record, @Param("example") RoomOrderExample example);

    int updateByExample(@Param("record") RoomOrder record, @Param("example") RoomOrderExample example);

    int updateByPrimaryKeySelective(RoomOrder record);

    int updateByPrimaryKey(RoomOrder record);
    //zi

    List <RoomOrder> selectByUserid(String uid);

    String selectByRDU(RoomOrder record);

    RoomOrder selectByOrderid(String  orderid);

    RoomOrder selectByOrderidqu (String  orderid);


    List<RoomOrder> selectAllCompleteOrder(Map map);

    List<RoomOrder> selectAllTuiOrder(Map map);

    List<RoomOrder> selectPage(Map map);


    List<RoomOrder> selectAllOrder(Map map);

    List<RoomOrder> selectAllPayOrder(Map map);

    int selectCount();

    int selectPayCount();

    int selectTuiCount();

    RoomOrder findOrderid(String orderid);

    int updateByOrderidForStatus(String orderid);

    int  selectByCount();

    List<RoomOrder>  selectByPhone(@Param("ostatus") int ostatus,@Param("uphone") String uphone);


}