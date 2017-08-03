package com.simonov.coffee.service;

import com.simonov.coffee.model.CoffeeOrder;
import com.simonov.coffee.model.CoffeeOrderItem;
import com.simonov.coffee.to.OrderTO;
import com.simonov.coffee.to.TypeToSelected;
import com.simonov.coffee.to.TypeToSelectedWraper;

import java.util.List;

public interface CoffeeOrderService {

//    List<CoffeeType> getAllEnabledCoffeType();

    TypeToSelectedWraper getWrapper();

    List<CoffeeOrderItem> save(OrderTO orderTO);

    CoffeeOrder getOne(int id);

    OrderTO prepareOrder(List<TypeToSelected> items);

    int getNFreeCup();

}
