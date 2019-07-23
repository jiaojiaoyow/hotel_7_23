package com.example.hotel.model;

public class GetCoupon extends GetCouponKey {
    private Byte status;

    private String useStartDate;

    private String useEndDate;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getUseStartDate() {
        return useStartDate;
    }

    public void setUseStartDate(String useStartDate) {
        this.useStartDate = useStartDate == null ? null : useStartDate.trim();
    }

    public String getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(String useEndDate) {
        this.useEndDate = useEndDate == null ? null : useEndDate.trim();
    }
}