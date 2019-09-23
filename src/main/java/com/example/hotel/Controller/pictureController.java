package com.example.hotel.Controller;

import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.model.Picture;
import com.example.hotel.service.Impl.PictureServiceImpl;
import com.example.hotel.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class pictureController {

    @Autowired
    private PictureService pictureService;


    @RequestMapping("/api/getPicture")
    public ResultDTO get() {
        ResultDTO resultDTO = new ResultDTO();
        try {
            List<Picture> pictures=pictureService.selectAll();
            if(pictures!=null){
                return resultDTO.ok(pictures);
            }
            return resultDTO.fail("没有值");
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/insertPicture")
    public ResultDTO in(Picture picture) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            int flag=pictureService.insertSelective(picture);
            if(flag>0){
                return resultDTO.ok("新增成功");
            }
            return resultDTO.fail("新增失败");
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }


    @RequestMapping("/api/back/deletePicture")
    public ResultDTO del(Integer pid) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            int flag=pictureService.deleteByPrimaryKey(pid);
            if(flag>0){
                return resultDTO.ok("删除成功");
            }
            return resultDTO.fail("删除失败");
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/updatePicture")
    public ResultDTO upd(Picture picture) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            int flag=pictureService.updateByPrimaryKeySelective(picture);
            if(flag>0){
                return resultDTO.ok("修改成功");
            }
            return resultDTO.fail("修改失败");
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }

}
