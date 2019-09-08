package com.example.hotel;

import com.example.hotel.model.RoomOrder;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestController {

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }





    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        try {
            RoomOrder roomOrder=new RoomOrder();
            roomOrder.setUid("ookL25Q9bypvjSm8uYYJud6R0JIU");
            roomOrder.setRoomname("阳光大床");
            roomOrder.setTotalprice(500.0);
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/createorder").param("uid",
                    "ookL25Q9bypvjSm8uYYJud6R0JIU"))
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();


    }}
    public void test1(){



    }
}
