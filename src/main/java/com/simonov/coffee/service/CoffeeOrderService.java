package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.to.CoffeeOrderItemTo;
import com.simonov.coffee.to.Order;

import java.util.List;
import java.util.Map;

public interface CoffeeOrderService {

    List<CoffeeType> getAllEnabledCoffeType();

    Order create(CoffeeOrder coffeeOrder, List<CoffeeOrderItemTo> coffeeTypeList);

    Order save(Order order);

    CoffeeOrder getOne(int id);

    List<CoffeeOrderItemTo> getByOrderId(int id);

    List<CoffeeOrderItemTo> getByCoffeeTypeIdAndQuantity(Map<Integer,Integer> ctm);
    Order prepareOrder(List<CoffeeOrderItemTo> items);

}
