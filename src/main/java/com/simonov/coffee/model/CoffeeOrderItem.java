package com.simonov.coffee.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "coffeeorderitem")
@Data
public class CoffeeOrderItem extends BaseEntity {


    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private CoffeeType coffeeType;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "order_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private CoffeeOrder coffeeOrder;

    @Column(name = "quantity")
    @NotNull
    @Digits(fraction = 0, integer = 5)
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoffeeOrderItem)) return false;
        if (!super.equals(o)) return false;

        CoffeeOrderItem that = (CoffeeOrderItem) o;

        if (!getCoffeeOrder().equals(that.getCoffeeOrder())) return false;
        return getQuantity().equals(that.getQuantity());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCoffeeOrder().hashCode();
        result = 31 * result + getQuantity().hashCode();
        return result;
    }
}
