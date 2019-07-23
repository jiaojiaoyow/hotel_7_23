package com.example.hotel.model;

public class TroRoom {
    private String roomintroduce;

    private String roomimage;

    private String roomarea;

    private String roomwifi;

    private String roombed;

    private String roombreakfast;

    private String roomnum;

    private Double roomprice;

    public String getRoomintroduce() {
        return roomintroduce;
    }

    public void setRoomintroduce(String roomintroduce) {
        this.roomintroduce = roomintroduce == null ? null : roomintroduce.trim();
    }

    public String getRoomimage() {
        return roomimage;
    }

    public void setRoomimage(String roomimage) {
        this.roomimage = roomimage == null ? null : roomimage.trim();
    }

    public String getRoomarea() {
        return roomarea;
    }

    public void setRoomarea(String roomarea) {
        this.roomarea = roomarea == null ? null : roomarea.trim();
    }

    public String getRoomwifi() {
        return roomwifi;
    }

    public void setRoomwifi(String roomwifi) {
        this.roomwifi = roomwifi == null ? null : roomwifi.trim();
    }

    public String getRoombed() {
        return roombed;
    }

    public void setRoombed(String roombed) {
        this.roombed = roombed == null ? null : roombed.trim();
    }

    public String getRoombreakfast() {
        return roombreakfast;
    }

    public void setRoombreakfast(String roombreakfast) {
        this.roombreakfast = roombreakfast == null ? null : roombreakfast.trim();
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum == null ? null : roomnum.trim();
    }

    public Double getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(Double roomprice) {
        this.roomprice = roomprice;
    }
}