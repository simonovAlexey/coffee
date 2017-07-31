package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.repository.CoffeeOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa, mysql")
public class DataJpaCoffeeOrderRepositoryTest {

    @Autowired
    private CoffeeOrderRepository repository;

    @Test
    public void getOneTest() throws Exception {
        CoffeeOrder order = repository.findById(1);
        System.out.println(order);
    }

}