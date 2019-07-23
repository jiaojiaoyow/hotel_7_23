package com.example.hotel.service.Impl;

import com.example.hotel.model.Essay;
import com.example.hotel.model.EssayExample;
import com.example.hotel.service.EssayService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel.dao.EssayMapper;
import java.util.List;
import java.util.Map;

@Service("EssayService")
public class EssayServiceImpl implements EssayService {

    @Autowired
    private EssayMapper esseyMapper;

    @Override
    public long countByExample(EssayExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(EssayExample example) {
        return 0;
    }



    @Override
    public int insert(Essay record) {
        return 0;
    }



    @Override
    public List<Essay> selectByExampleWithBLOBsWithRowbounds(EssayExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Essay> selectByExampleWithBLOBs(EssayExample example) {
        return null;
    }

    @Override
    public List<Essay> selectByExampleWithRowbounds(EssayExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Essay> selectByExample(EssayExample example) {
        return null;
    }

    @Override
    public Essay selectByPrimaryKey(Integer aid) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Essay record, EssayExample example) {
        return 0;
    }

    @Override
    public int updateByExampleWithBLOBs(Essay record, EssayExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Essay record, EssayExample example) {
        return 0;
    }



    @Override
    public int updateByPrimaryKeyWithBLOBs(Essay record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Essay record) {
        return 0;
    }

    @Override
    public List<Essay> selectAll() {
        return esseyMapper.selectAll();
    }

    @Override
    public int selectCount() {
        return this.esseyMapper.selectCount();
    }

    @Override
    public List<Essay> selectPage(Map map) {
        return this.esseyMapper.selectPage(map);
    }

    //自己更新的
    @Override
    public int updateByPrimaryKeySelective(Essay record) {
        return this.esseyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer aid) {
        return this.esseyMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public int insertSelective(Essay record) {
        return this.esseyMapper.insertSelective(record);
    }


}
