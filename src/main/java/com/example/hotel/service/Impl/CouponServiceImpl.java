package com.example.hotel.service.Impl;


import com.example.hotel.dao.CouponMapper;
import com.example.hotel.model.Coupon;
import com.example.hotel.model.CouponExample;
import com.example.hotel.service.CouponService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("CouponService")
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;


    @Override
    public long countByExample(CouponExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CouponExample example) {
        return 0;
    }


    @Override
    public int insert(Coupon record) {
        return 0;
    }



    @Override
    public List<Coupon> selectByExampleWithRowbounds(CouponExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Coupon> selectByExample(CouponExample example) {
        return null;
    }



    @Override
    public int updateByExampleSelective(Coupon record, CouponExample example) {
        return this.couponMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Coupon record, CouponExample example) {
        return 0;
    }


    @Override
    public int updateByPrimaryKey(Coupon record) {
        return 0;
    }

    //自己改的
    @Override
    public List<Coupon> SelectAll() {

        return this.couponMapper.SelectAll();

    }

    @Override
    public List<Coupon> selectByCname(String cname) {
        return this.couponMapper.selectByCname(cname);
    }

    @Override
    public int selectCount() {
        return this.couponMapper.selectCount();
    }

    @Override
    public List<Coupon> selectPage(Map map) {
        return this.couponMapper.selectPage(map);
    }

    @Override
    public int insertSelective(Coupon record) {
        return this.couponMapper.insertSelective(record);
    }

    @Override
    public Coupon selectByPrimaryKey(Integer cid) {
        return this.couponMapper.selectByPrimaryKey(cid);
    }

    @Override
    public int updateByPrimaryKeySelective(Coupon record) {
        return this.couponMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int deleteByPrimaryKey(Integer cid) {
        return this.couponMapper.deleteByPrimaryKey(cid);
    }


}
