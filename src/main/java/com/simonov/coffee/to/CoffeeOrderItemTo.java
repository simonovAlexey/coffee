package com.simonov.coffee.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoffeeOrderItemTo extends BaseTo{
    @Size(min = 3,max = 50)
    private  String typeName;
    private  int quantity;
    private  Double price;
    private Double total;

    public CoffeeOrderItemTo(int id, String typeName, int quantity, Double price) {
        this.id=id;
        this.typeName = typeName;
        this.quantity = quantity;
        this.price = price;
    }

    public CoffeeOrderItemTo(String typeName, int quantity, Double price, Double total) {
        this.typeName = typeName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    public CoffeeOrderItemTo(int id, String typeName, int quantity, Double price, Double total) {
        this(typeName,quantity,price,total);
        this.id=id;

    }

    public CoffeeOrderItemTo() {
    }

    @Override
    public String toString() {
        return "CoffeeOrderItemTo{" +
                "typeName='" + typeName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", id=" + id +
                '}';
    }
}
