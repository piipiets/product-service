package com.productservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document(value = "product")
@Getter
@Setter
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

}
