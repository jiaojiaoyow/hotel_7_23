package com.example.hotel.Controller;

import com.example.hotel.DTO.AdminDTO;
import com.example.hotel.DTO.ObjectDTO;
import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.model.Admin;
import com.example.hotel.service.AdminService;
import com.example.hotel.util.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/api/back/login")
    public ResultDTO login(@RequestBody AdminDTO admin){
        ResultDTO resultDTO=new ResultDTO();
        try {
//            Admin admin2=new Admin();
//            BeanUtils.copyProperties(admin2,admin);
            if(admin.getUname()==null){
                return resultDTO.nothing();
            }
            Admin admin1=adminService.selectByUname(admin.getUname());
            if(!admin1.getPassword().equals(admin.getPassword())){
                return resultDTO.fail("密码错误");
            }
            return resultDTO.ok(null);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/selAdmin")
    public ResultDTO selAdmin(int currPage,int pageSize){
        ResultDTO resultDTO=new ResultDTO();
        try {
            int total=adminService.selectCount();
            //创建当前页的分页对象，计算四个参数
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize, total);
            /*-------------------向数据库中查询当前页的数据-------------------*/
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<Admin> admin1=adminService.selectPage(parameter);
            if(admin1.size()==0){
                return resultDTO.fail();
            }
            ObjectDTO object=new ObjectDTO(total,admin1);
            return resultDTO.ok(object);
        }catch (org.springframework.jdbc.BadSqlGrammarException e){
            return resultDTO.fail();
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    //查找用户
    @RequestMapping("/api/back/findAdmin")
    public ResultDTO findCoupon(String uname){
        ResultDTO resultDTO=new ResultDTO();
        try {
            List<Admin> mess=adminService.selectByUnameLike(uname);
            //如果结束时间大于系统给的时间，这在数据库中删除数据，并且不返回。
            if(mess.size()==0){
                return resultDTO.fail();
            }
            return resultDTO.ok(mess);

        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }


    @RequestMapping("/api/back/alterOrSaveAdmin")
    public ResultDTO alter(@RequestBody AdminDTO admin){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(admin.getUname()==null){
                return resultDTO.nothing();
            }
            Admin admin1=new Admin();
            BeanUtils.copyProperties(admin,admin1);
            int flag=adminService.saveOrUpdate(admin1);
            if(flag==0){
                return resultDTO.fail("新建或更改管理员失败");
            }
            return resultDTO.ok(null);
        }catch (org.springframework.dao.DuplicateKeyException e){
            return resultDTO.interFail("Admin");
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/deleteAdmin")
    public ResultDTO delete(Integer adminId){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(adminId==null){
                return resultDTO.nothing();
            }
            int flag=adminService.deleteByPrimaryKey(adminId);
            if(flag==0){
                return resultDTO.fail("管理员删除失败");
            }
            return resultDTO.ok(null);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }
}
