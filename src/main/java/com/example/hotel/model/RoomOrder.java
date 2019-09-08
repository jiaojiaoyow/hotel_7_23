package com.example.hotel.model;

import java.util.Date;

public class RoomOrder extends RoomOrderKey {
    private String uname; //用户名字

    private String uphone; //手机号码

    private Integer roomnumber;//房间数量

    private String ordertime;//到店时间

    private String leavetime;//离店时间

    private Integer orderday;//天数

    private Double totalprice;//总

    private Integer orderstatus;

    private Integer cid;

    private String arrivetime;

    private String comment;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone == null ? null : uphone.trim();
    }

    public Integer getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(Integer roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    public Integer getOrderday() {
        return orderday;
    }

    public void setOrderday(Integer orderday) {
        this.orderday = orderday;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime == null ? null : arrivetime.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @Override
    public String toString() {
        return "RoomOrder{" +
                "uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                ", roomnumber=" + roomnumber +
                ", ordertime='" + ordertime + '\'' +
                ", leavetime='" + leavetime + '\'' +
                ", orderday=" + orderday +
                ", totalprice=" + totalprice +
                ", orderstatus=" + orderstatus +
                ", cid=" + cid +
                ", arrivetime='" + arrivetime + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}