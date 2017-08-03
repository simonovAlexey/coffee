package com.simonov.coffee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @NotBlank
    @Length(min = 3, max=200)
    private String name;

    @Column(name = "delivery_address")
    @NotBlank
    @Length(min = 3, max=200)
    private String deliveryAdress;

    @Column(name = "cost")
    @NotNull
    @Digits(fraction = 2, integer = 5)
    private Double cost;

    public CoffeeOrder(LocalDateTime orderDate, String name, String deliveryAdress, Double cost) {
        this.orderDate = orderDate;
        this.name = name;
        this.deliveryAdress = deliveryAdress;
        this.cost = cost;
    }
}
