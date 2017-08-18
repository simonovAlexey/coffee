package com.simonov.coffee.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "coffeetype")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoffeeType extends BaseEntity {

    @Column(name = "type_name")
    @NotNull
    @Size(min = 1)
    private String typeName;

    @Column(name = "price")
    @NotNull
    @Digits(fraction = 2, integer = 5)
    private Double price;

    @Column(name = "disabled")
    @NotNull
    private char disabled;

}
