package com.example.hotel.service.Impl;

import com.example.hotel.dao.GoodsOrderMapper;
import com.example.hotel.model.GoodsOrder;
import com.example.hotel.model.GoodsOrderExample;
import com.example.hotel.service.GoodsOrderService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("GoodsOrderService")
public class GoodsOrderServiceImpl implements GoodsOrderService {
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Override
    public long countByExample(GoodsOrderExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(GoodsOrderExample example) {
        return 0;
    }



    @Override
    public int insert(GoodsOrder record) {
        return 0;
    }



    @Override
    public List<GoodsOrder> selectByExampleWithRowbounds(GoodsOrderExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<GoodsOrder> selectByExample(GoodsOrderExample example) {
        return null;
    }

    @Override
    public GoodsOrder selectByPrimaryKey(Integer gorderid) {
        return null;
    }

    @Override
    public int updateByExampleSelective(GoodsOrder record, GoodsOrderExample example) {
        return 0;
    }

    @Override
    public int updateByExample(GoodsOrder record, GoodsOrderExample example) {
        return 0;
    }



    @Override
    public int updateByPrimaryKey(GoodsOrder record) {
        return 0;
    }

    //改变
    @Override
    public int selectCount() {
        return this.goodsOrderMapper.selectCount();
    }

    @Override
    public List<GoodsOrder> selectPage(Map map) {
        return this.goodsOrderMapper.selectPage(map);
    }

    @Override
    public List<GoodsOrder> selectByUidPage(Integer begin, Integer num, String uid) {
        return this.goodsOrderMapper.selectByUidPage(begin,num,uid);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsOrder record) {
        return this.goodsOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(GoodsOrder record) {
        return this.goodsOrderMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer gorderid) {
        return this.goodsOrderMapper.deleteByPrimaryKey(gorderid);
    }
}
