package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.repository.CoffeeOrderRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Profile("spring-data-jpa")
@Transactional(readOnly = true)
public interface DataJpaCoffeeOrderRepository extends JpaRepository<CoffeeOrder,Integer>, CoffeeOrderRepository {

    @Override
    CoffeeOrder save(CoffeeOrder coffeeOrder);

    @Override
    CoffeeOrder getOne(Integer id);
}
