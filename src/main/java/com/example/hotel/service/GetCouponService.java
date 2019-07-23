package com.example.hotel.service;

import com.example.hotel.model.Coupon;
import com.example.hotel.model.GetCoupon;
import com.example.hotel.model.GetCouponExample;
import com.example.hotel.model.GetCouponKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface GetCouponService {
    long countByExample(GetCouponExample example);

    int deleteByExample(GetCouponExample example);

    int deleteByPrimaryKey(GetCouponKey key);

    int insert(GetCoupon record);

    int insertSelective(GetCoupon record);

    List<GetCoupon> selectByExampleWithRowbounds(GetCouponExample example, RowBounds rowBounds);

    List<GetCoupon> selectByExample(GetCouponExample example);

    GetCoupon selectByPrimaryKey(GetCouponKey key);

    int updateByExampleSelective(@Param("record") GetCoupon record, @Param("example") GetCouponExample example);

    int updateByExample(@Param("record") GetCoupon record, @Param("example") GetCouponExample example);

    int updateByPrimaryKeySelective(GetCoupon record);

    int updateByPrimaryKey(GetCoupon record);

    List<GetCoupon> selectByUid(String uid);
}
