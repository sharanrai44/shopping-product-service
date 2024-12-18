package com.onlineshop.shopping_application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onlineshop.shopping_application.entity.util.Attribute;
import com.onlineshop.shopping_application.entity.util.Currency;
import com.onlineshop.shopping_application.entity.util.Inventory;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getPrice() {
        return price;
    }

    public void setPrice(Currency price) {
        this.price = price;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
