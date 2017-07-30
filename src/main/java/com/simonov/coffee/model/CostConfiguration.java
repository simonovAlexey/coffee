package com.simonov.coffee.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
@Data
public class CostConfiguration{

    @Id
    @Column(name = "id")
    @NotBlank
    private String id;

    @Column(name = "value")
    @NotBlank
    private String value;
}
