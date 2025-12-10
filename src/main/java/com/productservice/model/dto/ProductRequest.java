package com.productservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    @NotEmpty(message = "Name must be filled")
    private String name;

    @NotEmpty(message = "Description must be filled")
    private String description;

    @NotNull(message = "Price must be filled")
    private BigDecimal price;
}
