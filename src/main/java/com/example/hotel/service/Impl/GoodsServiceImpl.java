package com.example.hotel.service.Impl;

import com.example.hotel.dao.GoodsMapper;
import com.example.hotel.model.Goods;
import com.example.hotel.model.GoodsExample;
import com.example.hotel.service.GoodsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("GoodsService")
public class  GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public long countByExample(GoodsExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(GoodsExample example) {
        return 0;
    }



    @Override
    public int insert(Goods record) {
        return 0;
    }


    @Override
    public List<Goods> selectByExampleWithRowbounds(GoodsExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Goods> selectByExample(GoodsExample example) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Goods record, GoodsExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Goods record, GoodsExample example) {
        return 0;
    }



    @Override
    public int updateByPrimaryKey(Goods record) {
        return 0;
    }

    @Override
    public int selectCount() {
        return this.goodsMapper.selectCount();
    }

    @Override
    public List<Goods> selectPage(Map map) {
        return this.goodsMapper.selectPage(map);
    }

    //改过
    @Override
    public Goods selectByPrimaryKey(Integer gid) {
        return this.goodsMapper.selectByPrimaryKey(gid);
    }


    @Override
    public int insertSelective(Goods record) {
        return this.goodsMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return this.goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer gid) {
        return this.goodsMapper.deleteByPrimaryKey(gid);
    }

}
