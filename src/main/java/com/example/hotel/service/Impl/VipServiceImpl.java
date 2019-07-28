package com.example.hotel.service.Impl;

import com.example.hotel.dao.VipCardMapper;
import com.example.hotel.model.VipCard;
import com.example.hotel.model.VipCardExample;
import com.example.hotel.service.VipService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("VipService")
public class VipServiceImpl implements VipService {

    @Autowired
    private VipCardMapper vipCardMapper;

    @Override
    public long countByExample(VipCardExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(VipCardExample example) {
        return 0;
    }



    @Override
    public List<VipCard> selectByExampleWithRowbounds(VipCardExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<VipCard> selectByExample(VipCardExample example) {
        return null;
    }



    @Override
    public int updateByExampleSelective(VipCard record, VipCardExample example) {
        return 0;
    }

    @Override
    public int updateByExample(VipCard record, VipCardExample example) {
        return 0;
    }



    @Override
    public int updateByPrimaryKey(VipCard record) {
        return 0;
    }

    @Override
    public List<VipCard> selectAllCard(Map map) {
        return vipCardMapper.selectAllCard(map);
    }

    @Override
    public int selectCount() {
        return vipCardMapper.selectCount();
    }


    @Override
    public int insertSelective(VipCard record) {
        return this.vipCardMapper.insertSelective(record);
    }

    @Override
    public VipCard selectByPrimaryKey(String vid) {
        return this.vipCardMapper.selectByPrimaryKey(vid);
    }

    @Override
    public int updateByPrimaryKeySelective(VipCard record) {
        return vipCardMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String vid) {
        return vipCardMapper.deleteByPrimaryKey(vid);
    }

    @Override
    public int insert(VipCard record) {
        return vipCardMapper.insert(record);
    }

}
