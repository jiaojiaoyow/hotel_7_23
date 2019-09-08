package com.example.hotel.service.Impl;

import com.example.hotel.dao.RoomMapper;
import com.example.hotel.dao.RoomOrderMapper;
import com.example.hotel.error.OrderException;
import com.example.hotel.model.RoomOrder;
import com.example.hotel.model.RoomOrderExample;
import com.example.hotel.model.RoomOrderKey;
import com.example.hotel.service.RoomOrderService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("RoomOrderService")
public class RoomOrderServiceImpl implements RoomOrderService {

    @Autowired
    private RoomOrderMapper roomOrderMapper;
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public long countByExample(RoomOrderExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(RoomOrderExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(RoomOrderKey key) {
        return 0;
    }

    @Override
    public int insert(RoomOrder record) {
        return 0;
    }

    @Override
    public int insertSelective(RoomOrder record) {
        return roomOrderMapper.insertSelective(record);
    }

    @Override
    public List<RoomOrder> selectByExampleWithRowbounds(RoomOrderExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<RoomOrder> selectByExample(RoomOrderExample example) {
        return null;
    }

    @Override
    public RoomOrder selectByPrimaryKey(RoomOrderKey key) {
        return roomOrderMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByExampleSelective(RoomOrder record, RoomOrderExample example) {
        return 0;
    }

    @Override
    public int updateByExample(RoomOrder record, RoomOrderExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(RoomOrder record) {
        return roomOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoomOrder record) {
        return 0;
    }

    @Override
    public List<RoomOrder> selectByUserid(String uid) {
        return roomOrderMapper.selectByUserid(uid);
    }

    @Override
    public String selectByRDU(RoomOrder record) {
        return roomOrderMapper.selectByRDU(record);
    }

    @Override
    public RoomOrder selectByOrderid(String orderid) {
        return roomOrderMapper.selectByOrderid(orderid);
    }

    @Override
    public RoomOrder selectByOrderidqu(String orderid) {
        return roomOrderMapper.selectByOrderidqu(orderid);
    }

    @Override
    public List<RoomOrder> selectAllCompleteOrder(Map map) {
        return roomOrderMapper.selectAllCompleteOrder(map);
    }

    @Override
    public List<RoomOrder> selectPage(Map map) {
        return roomOrderMapper.selectPage(map);
    }


    @Override
    public List<RoomOrder> selectAllOrder(Map map) {
        return roomOrderMapper.selectAllOrder(map);
    }

    @Override
    public List<RoomOrder> selectAllPayOrder(Map map) {
        return roomOrderMapper.selectAllPayOrder(map);
    }

    @Override
    public int selectCount() {
        return roomOrderMapper.selectCount();
    }

    @Override
    public int selectPayCount() {
        return roomOrderMapper.selectPayCount();
    }


    /*
    *
    *
    * */
    @Override
    @Transactional
    public OrderException checkOrder(String orderid) throws OrderException {

        try {
            RoomOrder roomOrder = roomOrderMapper.findOrderid(orderid);
            if (roomOrder != null && roomOrder.getOrderstatus() == 1) {

                int x = roomOrderMapper.updateByOrderidForStatus(orderid);
                int y = roomMapper.updateByPrimaryKeyForReduce(roomOrder.getRoomname(), roomOrder.getRoomnumber());
                if (x <= 0 || y <= 0) {
                    throw new OrderException("无空余房间或订单已处理");
                } else {
                    //更新成功
                    return new OrderException(true);
                }

            }

        }catch (OrderException e){
            throw  e;
        }catch (Exception ee){
            throw  ee;
        }
        return new OrderException(false);

    }

    /*
     * 预创建订单
     * @param 订单信息
     * 预减房间处理
     * */
    @Override
    @Transactional
    public OrderException beforehandOrder(RoomOrder roomOrder) throws OrderException {
        try {
            int x=roomOrderMapper.insertSelective(roomOrder);
            int y = roomMapper.updateByPrimaryKeyForReduce(roomOrder.getRoomname(), roomOrder.getRoomnumber());
            if (x <= 0 || y <= 0) {
                System.out.println("创建订单失败，无空余房间");
                throw new OrderException("创建订单失败，无空余房间");

            } else {
                //更新成功
                System.out.println("更新成功");
                return new OrderException(true);
            }


        }catch (OrderException e){
            throw  e;
        }catch (Exception ee){
            throw  ee;
        }


    }


}
