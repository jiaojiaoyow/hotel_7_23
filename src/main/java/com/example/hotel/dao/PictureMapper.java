package com.example.hotel.dao;

import com.example.hotel.model.Picture;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    List<Picture> selectAll();
}