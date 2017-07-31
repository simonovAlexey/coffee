package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.model.CoffeeOrderItem;
import com.simonov.coffee.model.CoffeeType;
import com.simonov.coffee.to.CoffeeOrderItemTo;
import com.simonov.coffee.to.OrderTO;

import java.util.List;
import java.util.Map;

public interface CoffeeOrderService {

    List<CoffeeType> getAllEnabledCoffeType();

    OrderTO create(CoffeeOrder coffeeOrder, List<CoffeeOrderItemTo> coffeeTypeList);

    List<CoffeeOrderItem> save(OrderTO orderTO);

    CoffeeOrder getOne(int id);

    List<CoffeeOrderItemTo> getByOrderId(int id);

    List<CoffeeOrderItemTo> getByCoffeeTypeIdAndQuantity(Map<Integer,Integer> ctm);

    OrderTO prepareOrder(List<CoffeeOrderItemTo> items);

}
