package com.example.hotel.service.Impl;

import com.example.hotel.dao.PictureMapper;
import com.example.hotel.model.Picture;
import com.example.hotel.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PictureSevice")
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public int deleteByPrimaryKey(Integer pid) {
        return pictureMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int insert(Picture record) {
        return pictureMapper.insert(record);
    }

    @Override
    public int insertSelective(Picture record) {
        return pictureMapper.insertSelective(record);
    }

    @Override
    public Picture selectByPrimaryKey(Integer pid) {
        return pictureMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int updateByPrimaryKeySelective(Picture record) {
        return pictureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Picture record) {
        return pictureMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Picture> selectAll() {
        return pictureMapper.selectAll();
    }
}
