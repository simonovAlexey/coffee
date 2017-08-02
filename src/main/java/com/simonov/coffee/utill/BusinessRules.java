package com.simonov.coffee.utill;

public interface BusinessRules {

    double calculateSubTotalCost(Integer quantity, double price);
	double calculateDelivery(double subtotal);
	int getNFreeCup();
}
