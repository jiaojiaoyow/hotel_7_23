package com.example.hotel.Controller;

import com.example.hotel.DTO.CouponDTO;
import com.example.hotel.DTO.ObjectDTO;
import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.DTO.TokenDTO;
import com.example.hotel.model.Coupon;
import com.example.hotel.model.GetCoupon;
import com.example.hotel.model.GetCouponKey;
import com.example.hotel.service.CouponService;
import com.example.hotel.service.GetCouponService;
import com.example.hotel.adapter.CouponUtil;
import com.example.hotel.util.DateUtil;
import com.example.hotel.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;
@Slf4j
@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponUtil couponUtil;

    @Autowired
    private GetCouponService getCouponService;

    //获取所有的优惠卷，供主界面展示用
    @RequestMapping("/api/selCoupon")
    public ResultDTO Sel_message(int currPage,int pageSize) throws ParseException {

        ResultDTO resultDTO=new ResultDTO();
        try {
            int total=couponService.selectCount();
            if((currPage-1)*pageSize>total){
                return resultDTO.ok(null);
            }
            //创建当前页的分页对象，计算四个参数
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize,total);
            /*-------------------向数据库中查询当前页的数据-------------------*/
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<Coupon> mess=couponService.selectPage(parameter);
            //如果结束时间大于系统给的时间，这在数据库中删除数据，并且不返回。
//            couponUtil.FilterTime(mess);
            if(mess.size()==0){
                return resultDTO.fail();
            }
            ObjectDTO object=new ObjectDTO(total,mess);
            return resultDTO.ok(object);

        } catch (org.springframework.jdbc.BadSqlGrammarException e){
            return resultDTO.fail();
        }catch(Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    //查询用户个人拥有的不过期的优惠卷,传回uid
    @RequestMapping("/api/selUserCoupon")
    public ResultDTO Sel_User_message(String uid) throws ParseException{

        ResultDTO resultDTO=new ResultDTO();
        try {
            Date nowtime=new Date();
            List<Coupon> UserCoupon=new ArrayList<Coupon>();
            List<GetCoupon> mess=new ArrayList<GetCoupon>();
            mess=getCouponService.selectByUid(uid);
            for (GetCoupon temp:mess
            ) {
                if(temp.getStatus()!=1&&DateUtil.change_Date(temp.getUseEndDate()).getTime()>nowtime.getTime()){//未使用和未过期
                    UserCoupon.add(couponService.selectByPrimaryKey(temp.getCid()));
                }
                else {
                    int i=couponService.deleteByPrimaryKey(temp.getCid());
                }
            }
            if(UserCoupon.size()==0){
                return resultDTO.fail();
            }
            return resultDTO.ok(UserCoupon);

        }
        catch (org.springframework.jdbc.BadSqlGrammarException e){
            return resultDTO.fail();
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    //领取优惠卷,需要前端传回uid，cid
    @RequestMapping("/api/getCoupon")
    public ResultDTO get_coupon(@RequestBody GetCoupon getCoupon){

        ResultDTO resultDTO=new ResultDTO();
        try {
            GetCouponKey key=new GetCouponKey();
            key.setCid(getCoupon.getCid());
            key.setUid(getCoupon.getUid());
            GetCoupon gc=getCouponService.selectByPrimaryKey(key);
            if(gc!=null)
                return resultDTO.fail("您已经领取过该优惠券了哦");

            Date nowtime=new Date();
            //设置开始和结束时间
            getCoupon.setUseStartDate(DateUtil.change_str(nowtime));
            getCoupon.setUseEndDate(DateUtil.addtime(nowtime,5));//有五天的时间
            int flag=getCouponService.insertSelective(getCoupon);
            if(flag==0){
                return resultDTO.fail("领取优惠卷失败");
            }
            return resultDTO.ok(null);

        } catch (org.springframework.dao.DuplicateKeyException e){
            return resultDTO.interFail("优惠卷");
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }

    }

    //新建优惠卷 get方式
    @RequestMapping("/api/back/putCoupon")
    public ResultDTO putCoupon( Coupon coupon){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(coupon.getCname()==null){
                return resultDTO.nothing();
            }
            coupon.setSendStartDate(DateUtil.change_form(coupon.getSendStartDate()));
            coupon.setSendEndDate(DateUtil.change_form(coupon.getSendEndDate()));
            int flag=couponService.insertSelective(coupon);
            if(flag==0){
                return resultDTO.fail("新建优惠卷失败");
            }
            return resultDTO.ok(null);
        }catch (org.springframework.dao.DuplicateKeyException e){
            return resultDTO.interFail("优惠卷");
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    //修改优惠卷  Get方式
    @RequestMapping("/api/back/alterCoupon")
    public ResultDTO alterCoupon(Coupon coupon){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(coupon.getCid()==null){
                return resultDTO.nothing();
            }
            coupon.setSendStartDate(DateUtil.change_form(coupon.getSendStartDate()));
            coupon.setSendEndDate(DateUtil.change_form(coupon.getSendEndDate()));
            int flag=couponService.updateByPrimaryKeySelective(coupon);
            if(flag==0){
                return resultDTO.fail("修改优惠卷失败");
            }
            return resultDTO.ok(null);

        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    //查找优惠卷
    @RequestMapping("/api/back/findCoupon")
    public ResultDTO findCoupon(String cname){
        ResultDTO resultDTO=new ResultDTO();
        try {
            List<Coupon> mess=couponService.selectByCname(cname);
            //如果结束时间大于系统给的时间，这在数据库中删除数据，并且不返回。
            if(mess.size()==0){
                return resultDTO.fail();
            }
            return resultDTO.ok(mess);

        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/deleteCoupon")
    public ResultDTO deleteCoupon(Integer cid){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(cid==null){
                return resultDTO.nothing();
            }
            int flag=couponService.deleteByPrimaryKey(cid);
            if(flag==0){
                return resultDTO.fail("优惠卷删除失败");
            }
            return resultDTO.ok(null);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }
}
