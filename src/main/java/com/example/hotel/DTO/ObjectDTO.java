package com.example.hotel.DTO;

public class ObjectDTO {
    int total;
    Object data;

    public ObjectDTO(int total,Object object ){
        this.total=total;
        this.data=object;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
