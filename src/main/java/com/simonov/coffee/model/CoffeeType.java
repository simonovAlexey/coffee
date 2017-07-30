package com.simonov.coffee.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "coffeetype")
@Data
public class CoffeeType extends BaseEntity {

    @Column(name = "type_name")
    @NotBlank
    @SafeHtml
    private String typeName;

    @Column(name = "price")
    @NotNull
    @Digits(fraction = 2, integer = 5)
    private Double price;

    @Column(name = "disabled")
    @NotNull
    private char disabled;

}
