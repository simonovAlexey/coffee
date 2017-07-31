package com.simonov.coffee.repository.dataJpa;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.repository.CoffeeOrderRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("spring-data-jpa")
//@Transactional(readOnly = true)
public interface DataJpaCoffeeOrderRepository extends CoffeeOrderRepository, JpaRepository<CoffeeOrder,Integer> {

}
