package com.simonov.coffee.utill;

import com.simonov.coffee.to.CoffeeOrderItemTo;

import java.util.List;

public interface BusinessRules {

    double calculateSubTotalCost(CoffeeOrderItemTo item);
    double calculateSubTotalCost(Integer quantity, double price);
    double calculateOrderSubTotal(List<CoffeeOrderItemTo> items);
	double calculateDelivery(double subtotal);
	int getNFreeCup();
}
