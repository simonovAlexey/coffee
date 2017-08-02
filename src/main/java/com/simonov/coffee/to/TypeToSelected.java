package com.simonov.coffee.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TypeToSelected extends BaseTo{

    @NotBlank
    @Length(min = 3, max=200)
    private  String typeName;
    @Min(1)
    private  Integer quantity;
    @NotNull
    private  Double price;
    private boolean selected;
    private Double total;

    public TypeToSelected(int id, String typeName, Integer quantity, Double price, boolean selected) {
        this.id = id;
        this.typeName = typeName;
        this.quantity = quantity;
        this.price = price;
        this.selected = selected;
    }

    public TypeToSelected(Integer id, String typeName, Double price) {
        this.id=id;
        this.typeName = typeName;
        this.price = price;
    }
}
