package com.simonov.coffee.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoffeeOrderItemTo extends BaseTo{

    @Size(min = 3,max = 200)
    private  String typeName;

    @Min(value=1)
    private  int quantity;
    @NotNull
    private  Double price;
    @NotNull
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
}
