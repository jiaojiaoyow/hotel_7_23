package com.example.hotel.DTO;

import com.example.hotel.error.IErrorCode;
import lombok.Data;

/**
 * Created by codedrinker on 2018/11/25.
 * 返回登陆成功或者失败等信息
 */


public class ResultDTO {
    private Integer status;
    private Object data;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultDTO ok(Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(200);
        resultDTO.setData(data);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO fail() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(201);
        resultDTO.setMessage("没有相应的数据");
        return resultDTO;
    }

    public static ResultDTO unkonwFail(String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(202);
        resultDTO.setMessage("请求失败，因为："+message);
        return resultDTO;
    }

    public static ResultDTO fail(String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(204);
        resultDTO.setMessage("请求失败,"+message);
        return resultDTO;
    }



    public static ResultDTO interFail(String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(205);
        resultDTO.setMessage(message+"已存在");
        return resultDTO;
    }


    public static ResultDTO nothing() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(206);
        resultDTO.setMessage("没有接受到数据");
        return resultDTO;
    }



}
