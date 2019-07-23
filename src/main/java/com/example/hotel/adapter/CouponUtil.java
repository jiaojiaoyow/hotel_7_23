package com.example.hotel.adapter;

import com.example.hotel.model.Coupon;
import com.example.hotel.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class CouponUtil {
    @Autowired
    private CouponService couponService;

    //将已经过期的优惠卷删除
    public List<Coupon> FilterTime(List<Coupon> datas) throws ParseException{
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date temp=null;
        //时间转换
        for (int i=0;i<datas.size();i++) {
            Coupon data=datas.get(i);
            temp=df.parse(data.getSendEndDate());
            //判断是否已过时，过时就从数据库里直接删除,并且从list中删除
            if(temp.getTime()<new Date().getTime()){
                couponService.deleteByPrimaryKey(data.getCid());
                datas.remove(data);
                i=i-1;
            }
        }
        return datas;
    }
}
