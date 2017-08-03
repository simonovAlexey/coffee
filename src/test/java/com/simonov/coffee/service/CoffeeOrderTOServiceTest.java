package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa, mysql")
public class CoffeeOrderTOServiceTest extends AbstractCoffeeOrderServiceTest {




    @Test
    public void getOneCoffeeOrderTest() {
        CoffeeOrder one = coffeeOrderService.getOne(1);
        System.out.println("");
    }


}