package com.example.hotel.service.Impl;

import com.example.hotel.dao.CompanyMapper;
import com.example.hotel.model.Admin;
import com.example.hotel.model.AdminExample;
import com.example.hotel.model.Company;
import com.example.hotel.model.CompanyExample;
import com.example.hotel.service.CompanyService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public long countByExample(CompanyExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CompanyExample example) {
        return 0;
    }

    @Override
    public int insert(Company record) {
        return 0;
    }

    @Override
    public int insertSelective(Company record) {
        return this.companyMapper.insertSelective(record);
    }

    @Override
    public List<Company> selectByExampleWithRowbounds(CompanyExample example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<Company> selectByExample(CompanyExample example) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Company record, CompanyExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Company record, CompanyExample example) {
        return 0;
    }

    @Override
    public Company selectAll() {
        return this.companyMapper.selectAll();
    }


    @Override
    public int saveOrUpdate(Company company) {
        CompanyExample example=new CompanyExample();
        example.createCriteria().andCnameEqualTo(company.getCname());
        int flag=0;
        try {
            List<Company> users =companyMapper.selectByExample(example);
            // 先查看是否有，如果有更新，没有创建
            if (users != null && users.size() != 0) {
                flag=companyMapper.updateByExampleSelective(company, example);
            } else {
                flag=companyMapper.insertSelective(company);
            }
            return flag;
        }catch (Exception e){
            System.out.println(e);
            return flag;
        }
    }
}
