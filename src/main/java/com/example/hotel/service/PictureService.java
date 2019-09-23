package com.example.hotel.service;

import com.example.hotel.model.Picture;

import java.util.List;

public interface PictureService {
    int deleteByPrimaryKey(Integer pid);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    List<Picture> selectAll();
}
