package com.simonov.coffee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "coffeeorder")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoffeeOrder extends BaseEntity{

    @Column(name = "order_date", nullable = false)
    @NotNull
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime orderDate;

    @Column(name = "name")
    @NotNull
    @Length(min = 3, max=200)
    private String name;

    @Column(name = "delivery_address")
    @NotNull
    @Length(min = 3, max=200)
    private String deliveryAdress;

    @Column(name = "cost")
    @NotNull
    @Digits(fraction = 2, integer = 5)
    private Double cost;

    @OneToMany(mappedBy = "coffeeOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoffeeOrderItem> orderItems;


    public CoffeeOrder(LocalDateTime orderDate, String name, String deliveryAdress, Double cost) {
        this.orderDate = orderDate;
        this.name = name;
        this.deliveryAdress = deliveryAdress;
        this.cost = cost;
    }
}
