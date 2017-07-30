package com.simonov.coffee.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
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
public class CoffeeOrder extends BaseEntity{

    @Column(name = "order_date", nullable = false)
    @NotNull
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
//    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime orderDate;

    @Column(name = "name")
    @NotBlank
    @SafeHtml
    private String name;

    @Column(name = "delivery_address")
    @NotBlank
    @SafeHtml
    private String deliveryAdress;

    @Column(name = "cost")
    @NotNull
    @Digits(fraction = 2, integer = 5)
    private Double cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoffeeOrder)) return false;
        if (!super.equals(o)) return false;

        CoffeeOrder that = (CoffeeOrder) o;

        if (!getOrderDate().equals(that.getOrderDate())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getDeliveryAdress().equals(that.getDeliveryAdress())) return false;
        return getCost().equals(that.getCost());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getOrderDate().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDeliveryAdress().hashCode();
        result = 31 * result + getCost().hashCode();
        return result;
    }
}
