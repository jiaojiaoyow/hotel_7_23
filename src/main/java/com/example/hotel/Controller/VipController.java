package com.example.hotel.Controller;

import com.example.hotel.DTO.ObjectDTO;
import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.DTO.VipDTO;
import com.example.hotel.model.User;
import com.example.hotel.model.VipCard;
import com.example.hotel.service.UserService;
import com.example.hotel.service.VipService;
import com.example.hotel.util.DateUtil;
import com.example.hotel.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class VipController {

    @Autowired
    private UserService userService;

    @Autowired
    private VipService vipService;

    @RequestMapping("/api/getVip")
    public ResultDTO getVip(VipDTO vipDTO){
        ResultDTO resultDTO=new ResultDTO();
        try {

            if(vipDTO.getPhone()==null){
                return resultDTO.nothing();
            }
            VipCard vipCard1=vipService.selectByPrimaryKey(vipDTO.getPhone());
            if(vipCard1==null){
                //新增Vip
                VipCard vipCard=new VipCard();
                //加时间
                Date date= new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.YEAR, 2);
                Date endDate=c.getTime();
                vipCard.setVid(vipDTO.getPhone());
                vipCard.setGender(vipDTO.getGender());
                vipCard.setPhone(vipDTO.getPhone());
                vipCard.setUsername(vipDTO.getUsername());
                vipCard.setBirthday(vipDTO.getBirthday());
                vipCard.setEndday(DateUtil.change_str(endDate));
                vipCard.setViplevel(1);
                vipCard.setSummoney(0.00);
                int flag=vipService.insertSelective(vipCard);
                if(flag==0) {
                    return resultDTO.fail("无法获得会员卡");
                }
            }

            //更改用户表
            User user=userService.selectByPrimaryKey(vipDTO.getUid());
            user.setStatus(1);
            user.setVip(vipDTO.getPhone());
            userService.saveOrUpdate(user);
            return resultDTO.ok(null);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/isVip")
    public ResultDTO isVip(String uid){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(uid==null){
                return resultDTO.nothing();
            }
            User user =userService.selectByPrimaryKey(uid);
            if(user.getUid()==null){
                return resultDTO.fail();
            }
            if(user.getStatus()==1){
                return resultDTO.ok("是Vip");

            }
            return resultDTO.ok("非Vip");
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/getVipMessage")
    public ResultDTO getVipMessage(String uid){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(uid==null){
                return resultDTO.nothing();
            }
            User user =userService.selectByPrimaryKey(uid);
            if(user.getUid()==null){
                return resultDTO.fail();
            }
            if(user.getStatus()==1){
                VipCard vipCard=vipService.selectByPrimaryKey(user.getVip());
                Date date=new Date();
                if(DateUtil.change_Date(vipCard.getEndday()).getTime()<date.getTime()){
                    vipService.deleteByPrimaryKey(vipCard.getVid());
                    return resultDTO.fail();
                }
                return resultDTO.ok(vipCard);
            }
            return resultDTO.ok("非Vip");
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }
@RequestMapping("/api/back/getAllVip")
    public ResultDTO getAllVip(int currPage,int pageSize) {   //获得所有会员信息
        ResultDTO resultDTO = new ResultDTO();
        try {
            int total=vipService.selectCount();
            if((currPage-1)*pageSize>total){
                return resultDTO.ok(null);
            }
            //创建当前页的分页对象，计算四个参数
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize,total);
            /*-------------------向数据库中查询当前页的数据-------------------*/
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<VipCard> vipCards=new ArrayList<VipCard>();
            vipCards=vipService.selectAllCard(parameter);

            ObjectDTO objectDTO=new ObjectDTO(total,vipCards);
            return resultDTO.ok(objectDTO);  //有总数返回
        } catch (org.springframework.jdbc.BadSqlGrammarException e){
            return resultDTO.fail();
        }catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/deleteOneVip")
    public ResultDTO deleteOneVip(String vid){
        ResultDTO resultDTO = new ResultDTO();
        try {
               User user= userService.selectByVip(vid);
               if(user!=null){
                   //修改用户
                   User u=new User();
                   u.setUid(user.getUid());
                   u.setStatus(0);
                   u.setVip("0000");
                   userService.saveOrUpdate(u);
                   int x=vipService.deleteByPrimaryKey(vid);
                   if(x==0){
                       return resultDTO.fail("删除失败");
                   }
                   return  resultDTO.ok(null);
               }
               return  resultDTO.fail("该用户不是vip或传入数据为空");

        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }

    }
}
