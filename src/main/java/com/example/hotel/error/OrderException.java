package com.example.hotel.error;

public class OrderException extends RuntimeException {

    private boolean flag=false;

    public OrderException(){};

    public OrderException(String message){
        super(message);
    };
    public OrderException(boolean flag){
        this.flag=flag;
    }

    public boolean isFlag() {
        return flag;
    }
}
