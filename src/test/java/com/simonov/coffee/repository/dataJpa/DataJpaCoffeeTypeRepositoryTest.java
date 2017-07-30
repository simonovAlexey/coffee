package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.repository.CoffeeTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa, mysql")
public class DataJpaCoffeeTypeRepositoryTest {

    @Autowired
    CoffeeTypeRepository coffeeTypeRepository;

    @Test
    public void getAllEnabled() throws Exception {
        List<CoffeeType> allEnabled = coffeeTypeRepository.getAllEnabled();
        System.out.println("");
    }

}