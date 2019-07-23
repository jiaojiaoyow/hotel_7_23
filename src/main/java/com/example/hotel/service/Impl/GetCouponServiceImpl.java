package com.example.hotel.service.Impl;

import com.example.hotel.dao.GetCouponMapper;

import com.example.hotel.model.Coupon;

import com.example.hotel.model.GetCoupon;
import com.example.hotel.model.GetCouponExample;
import com.example.hotel.model.GetCouponKey;
import com.example.hotel.service.GetCouponService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GetCouponService")
public class GetCouponServiceImpl implements GetCouponService {

    @Autowired
    private GetCouponMapper getCouponMapper;

    @Override
    public long countByExample(GetCouponExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(GetCouponExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(GetCouponKey key) {
        return 0;
    }

    @Override
    public int insert(GetCoupon record) {
        return 0;
    }



    @Override
    public List<GetCoupon> selectByExampleWithRowbounds(GetCouponExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<GetCoupon> selectByExample(GetCouponExample example) {
        return null;
    }



    @Override
    public int updateByExampleSelective(GetCoupon record, GetCouponExample example) {
        return 0;
    }

    @Override
    public int updateByExample(GetCoupon record, GetCouponExample example) {
        return 0;
    }



    @Override
    public int updateByPrimaryKey(GetCoupon record) {
        return 0;
    }

//    自己添加的

    @Override
    public List<GetCoupon> selectByUid(String uid) {
        return this.getCouponMapper.selectByUid(uid);
    }

    @Override
    public int insertSelective(GetCoupon record) {
        return this.getCouponMapper.insertSelective(record);
    }

    @Override
    public GetCoupon selectByPrimaryKey(GetCouponKey key) {
        return this.getCouponMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(GetCoupon record) {
        return this.getCouponMapper.updateByPrimaryKeySelective(record);
    }


}
