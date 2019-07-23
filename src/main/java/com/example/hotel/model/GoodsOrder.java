package com.example.hotel.model;

public class GoodsOrder {
    private Integer gorderid;

    private String uid;

    private Integer gid;

    private String uname;

    private String address;

    private String phone;

    public Integer getGorderid() {
        return gorderid;
    }

    public void setGorderid(Integer gorderid) {
        this.gorderid = gorderid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}