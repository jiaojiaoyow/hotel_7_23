package com.example.hotel.Controller;

import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.model.Activity;
import com.example.hotel.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/api/back/putActivity")
    public ResultDTO putAct(Activity activity){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(activity.getImageurl()==null){
                return resultDTO.nothing();
            }
            int flag=activityService.insertSelective(activity);
            if(flag==0){
                return resultDTO.fail("轮播图插入失败");
            }
            return resultDTO.ok(null);
        }catch (org.springframework.dao.DuplicateKeyException e){
            return resultDTO.interFail("轮播");
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/selActivity")
    public ResultDTO selActivity(){
        ResultDTO resultDTO=new ResultDTO();
        try {

            List<Activity> activities =activityService.selectAll();
            if(activities.size()==0){
                return resultDTO.fail();
            }
            return resultDTO.ok(activities);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/deleteActivity")
    public ResultDTO deleteActivity(Integer actid){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(actid==null){
                return resultDTO.nothing();
            }

            int flag =activityService.deleteByPrimaryKey(actid);
            if(flag==0){
                return resultDTO.fail("活动删除失败");
            }
            return resultDTO.ok(null);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }
}
