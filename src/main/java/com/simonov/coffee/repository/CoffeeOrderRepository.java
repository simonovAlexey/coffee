package com.simonov.coffee.repository;

import com.simonov.coffee.model.CoffeeOrder;

public interface CoffeeOrderRepository {
    CoffeeOrder getOneById(int id);
}
