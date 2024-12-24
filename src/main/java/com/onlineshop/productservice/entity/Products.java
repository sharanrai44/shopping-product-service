package com.onlineshop.productservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onlineshop.productservice.entity.util.Attribute;
import com.onlineshop.productservice.entity.util.Currency;
import com.onlineshop.productservice.entity.util.Inventory;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Setter
@Getter
@Data
@Document(collection = "products")
public class Products {

    @Id
    @JsonProperty("id")
    private String id;

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

    public Products() {
    }

    public Products(String id, String category, String name, String brand,
                    String description, Currency price, Inventory inventory, List<Attribute> attributes) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.attributes = attributes;
    }
}
