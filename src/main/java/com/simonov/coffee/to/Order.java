package com.simonov.coffee.to;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;


public class Order extends BaseTo{

    @Valid
    private List<CoffeeOrderItemTo> items;
    private double subtotal;
    private double delivery;
    private double total;

    @NotBlank
    @Size(min=2, max=100)
//    @SafeHtml
    private String name;

    @NotBlank
    @Size(min=5, max=50)
//    @SafeHtml
    private String deliveryAdress;

    public Order(List<CoffeeOrderItemTo> items, double subtotal, double delivery, double total) {
        this.items = items;
        this.subtotal = subtotal;
        this.delivery = delivery;
        this.total = total;
    }

    public Order() {
    }

    public List<CoffeeOrderItemTo> getItems() {
        return items;
    }

    public void setItems(List<CoffeeOrderItemTo> items) {
        this.items = items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", subtotal=" + subtotal +
                ", delivery=" + delivery +
                ", total=" + total +
                ", name='" + name + '\'' +
                ", deliveryAdress='" + deliveryAdress + '\'' +
                ", id=" + id +
                '}';
    }
}
