package com.example.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.hotel.adapter","com.example.hotel.Controller","com.example.hotel.service"})
@ImportResource({"classpath*:applicationContext.xml"})
@EnableCaching
public class HotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }
}
