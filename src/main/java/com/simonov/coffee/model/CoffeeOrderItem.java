package com.simonov.coffee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "coffeeorderitem")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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

    public CoffeeOrderItem(CoffeeOrder coffeeOrder, Integer quantity) {
        this.coffeeOrder = coffeeOrder;
        this.quantity = quantity;
    }
}
