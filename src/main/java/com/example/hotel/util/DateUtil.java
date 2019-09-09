package com.example.hotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
    static SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat df2=new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat df3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static public String change_str(Date date){
        return df.format(date);
    }

    static public String change_str2(Date date){
        return df2.format(date);
    }

    static public String change_str3(Date date){
        return df3.format(date);
    }

    static public Date change_Date(String date) throws ParseException{
        return df.parse(date);
    }

    static public String change_form(String date) throws ParseException{
        Date date1=df.parse(date.split("T")[0]);
        return df.format(date1);
    }

    static public String addtime(Date date, int add){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,10);
        return df.format(calendar.getTime());
    }

}
