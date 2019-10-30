package com.example.hotel;

import com.example.hotel.model.BalanceOrder;
import com.example.hotel.service.BalanceOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelApplicationTests {

    @Autowired
    private BalanceOrderService balanceOrderService;

    @Test
    public void contextLoads()  throws Exception {

        BalanceOrder balanceOrder=new BalanceOrder();
        balanceOrder.setOrderid("12");
        balanceOrder.setOrderid("sdadasd");
        balanceOrder.setFlag(0);
        balanceOrderService.insertSelective(balanceOrder);

    }

}
