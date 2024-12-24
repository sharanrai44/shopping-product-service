package com.onlineshop.productservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onlineshop.productservice.entity.util.Attribute;
import com.onlineshop.productservice.entity.util.Currency;
import com.onlineshop.productservice.entity.util.Inventory;
import lombok.Data;

import java.util.List;

@Data
public class ProductsDTO {

    @JsonProperty("category")
    private String category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Currency price;

    @JsonProperty("inventory")
    private Inventory inventory;

    @JsonProperty("attributes")
    private List<Attribute> attributes;

}
