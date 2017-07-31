package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.to.CoffeeOrderItemTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa, mysql")
public class CoffeeOrderTOServiceTest extends AbstractCoffeeOrderServiceTest {

    @Test
    public void getAllEnabledCoffeTypeTest() throws Exception {
        List<CoffeeType> allEnabledCoffeType = coffeeOrderService.getAllEnabledCoffeType();
        System.out.println("done");
    }

    @Test
    public void getCoffeeTypeToByOrderIdTest() {
        List<CoffeeOrderItemTo> coffeeOrderItemTos = coffeeOrderService.getByOrderId(3);
        System.out.println("done");

    }

    @Test
    public void getOneCoffeeOrderTest() {
        CoffeeOrder one = coffeeOrderService.getOne(1);
        System.out.println("");
    }


}