package com.example.hotel.service;

import com.example.hotel.model.Admin;
import com.example.hotel.model.AdminExample;
import com.example.hotel.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface AdminService {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExampleWithRowbounds(AdminExample example, RowBounds rowBounds);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);



    //自己加的
    public int saveOrUpdate(Admin admin);

    List<Admin> selectAll();

    Admin selectByUname(String uname);

    int selectCount();

    List<Admin> selectPage(Map map);

    List<Admin> selectByUnameLike(String uname);
}
