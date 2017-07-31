package com.simonov.coffee.repository;

import com.simonov.coffee.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository {

    CoffeeOrder findById(int id);
    List<CoffeeOrder> findAll();
    CoffeeOrder save(CoffeeOrder coffeeOrder);
}
