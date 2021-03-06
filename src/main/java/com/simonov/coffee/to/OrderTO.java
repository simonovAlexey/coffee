package com.simonov.coffee.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderTO extends BaseTo{

    @Valid
    @NotNull
    private List<TypeToSelected> items;

    @NotNull
    private double subtotal;

    @NotNull
    private double delivery;

    @NotNull
    private double total;

    @NotNull
    @Length(min = 3, max=100)
    private String name;

    @NotNull
    @Length(min = 10, max=200)
    private String deliveryAdress;

    public OrderTO(List<TypeToSelected> items, double subtotal, double delivery, double total) {
        this.items = items;
        this.subtotal = subtotal;
        this.delivery = delivery;
        this.total = total;
    }
}
