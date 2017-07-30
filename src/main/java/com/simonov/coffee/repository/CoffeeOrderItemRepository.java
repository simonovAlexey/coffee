package com.simonov.coffee.repository;

import com.simonov.coffee.model.CoffeeOrderItem;

import java.util.List;

public interface CoffeeOrderItemRepository {

    List<CoffeeOrderItem> findAllByCoffeeOrderId(int id);
}
