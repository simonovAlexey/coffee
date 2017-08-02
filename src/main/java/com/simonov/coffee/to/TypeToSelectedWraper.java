package com.simonov.coffee.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TypeToSelectedWraper {

    @Valid
    @NotNull
    private List<TypeToSelected> items;
    private String wrapper = "wrapper";

    public TypeToSelectedWraper(List<TypeToSelected> items) {
        this.items = items;
    }
}
