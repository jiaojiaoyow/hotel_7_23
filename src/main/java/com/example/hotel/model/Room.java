package com.example.hotel.model;

public class Room {
    private String roomname;

    private String roomintroduce;

    private Integer roomnumber;

    private Double roomprice;

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname == null ? null : roomname.trim();
    }

    public String getRoomintroduce() {
        return roomintroduce;
    }

    public void setRoomintroduce(String roomintroduce) {
        this.roomintroduce = roomintroduce == null ? null : roomintroduce.trim();
    }

    public Integer getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(Integer roomnumber) {
        this.roomnumber = roomnumber;
    }

    public Double getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(Double roomprice) {
        this.roomprice = roomprice;
    }
}