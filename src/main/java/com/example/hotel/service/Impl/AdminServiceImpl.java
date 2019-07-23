package com.example.hotel.service.Impl;

import com.example.hotel.dao.AdminMapper;
import com.example.hotel.dao.UserMapper;
import com.example.hotel.model.Admin;
import com.example.hotel.model.AdminExample;
import com.example.hotel.service.AdminService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public long countByExample(AdminExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AdminExample example) {
        return 0;
    }

    @Override
    public int insert(Admin record) {
        return 0;
    }



    @Override
    public List<Admin> selectByExampleWithRowbounds(AdminExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Admin> selectByExample(AdminExample example) {
        return null;
    }

    @Override
    public Admin selectByPrimaryKey(Integer adminId) {
        return null;
    }


    @Override
    public int updateByExample(Admin record, AdminExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return 0;
    }


    //自己添加的

    @Override
    public Admin selectByUname(String uname) {
        return this.adminMapper.selectByUname(uname);
    }

    @Override
    public int selectCount() {
        return this.adminMapper.selectCount();
    }

    @Override
    public List<Admin> selectPage(Map map) {
        return this.adminMapper.selectPage(map);
    }

    @Override
    public List<Admin> selectByUnameLike(String uname) {
        return this.adminMapper.selectByUnameLike(uname);
    }

    @Override
    public int updateByExampleSelective(Admin record, AdminExample example) {
        return this.adminMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int insertSelective(Admin record) {
        return this.adminMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(Admin admin) {
        AdminExample example=new AdminExample();
        example.createCriteria().andUnameEqualTo(admin.getUname());
        int flag=0;
        try {
            List<Admin> users = adminMapper.selectByExample(example);
            // 先查看是否有，如果有更新，没有创建
            if (users != null && users.size() != 0) {
                flag=adminMapper.updateByExampleSelective(admin, example);
            } else {
                flag=adminMapper.insertSelective(admin);
            }
            return flag;
        }catch (Exception e){
            System.out.println(e);
            return flag;
        }
    }

    @Override
    public List<Admin> selectAll() {
        return this.adminMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer adminId) {
        return this.adminMapper.deleteByPrimaryKey(adminId);
    }

}
