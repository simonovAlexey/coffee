package com.simonov.coffee.repository;

import com.simonov.coffee.model.CoffeeOrderItem;

import java.util.List;

public interface CoffeeOrderItemRepository {

    List<CoffeeOrderItem> findAllByCoffeeOrderId(int id);

    CoffeeOrderItem save(CoffeeOrderItem coffeeOrderItem, int typeId);

    CoffeeOrderItem get(int id, int typeId);
}
