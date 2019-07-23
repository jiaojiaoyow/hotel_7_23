package com.example.hotel.service.Impl;

import com.example.hotel.dao.ActivityMapper;
import com.example.hotel.model.Activity;
import com.example.hotel.model.ActivityExample;
import com.example.hotel.service.ActivityService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public long countByExample(ActivityExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ActivityExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer actid) {
        return this.activityMapper.deleteByPrimaryKey(actid);
    }

    @Override
    public int insert(Activity record) {
        return 0;
    }

    @Override
    public int insertSelective(Activity record) {
        return this.activityMapper.insertSelective(record);
    }

    @Override
    public List<Activity> selectByExampleWithRowbounds(ActivityExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Activity> selectByExample(ActivityExample example) {
        return null;
    }

    @Override
    public Activity selectByPrimaryKey(Integer actid) {
        return this.activityMapper.selectByPrimaryKey(actid);
    }

    @Override
    public int updateByExampleSelective(Activity record, ActivityExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Activity record, ActivityExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Activity record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Activity record) {
        return this.activityMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Activity> selectAll() {
        return this.activityMapper.selectAll();
    }
}
