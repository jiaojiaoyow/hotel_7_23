package com.example.hotel.model;

public class EssayWithBLOBs extends Essay {
    private String content;

    private byte[] image;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}