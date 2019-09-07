package com.example.hotel.DTO;

public class AllUserMesDto {
    private Double ubalance;

    private Integer ugrade;

    private String nickname;

    private Integer totalamount;

    private Integer viplevel;

    public Double getUbalance() {
        return ubalance;
    }

    public void setUbalance(Double ubalance) {
        this.ubalance = ubalance;
    }

    public Integer getUgrade() {
        return ugrade;
    }

    public void setUgrade(Integer ugrade) {
        this.ugrade = ugrade;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getViplevel() {
        return viplevel;
    }

    public void setViplevel(Integer viplevel) {
        this.viplevel = viplevel;
    }
}
