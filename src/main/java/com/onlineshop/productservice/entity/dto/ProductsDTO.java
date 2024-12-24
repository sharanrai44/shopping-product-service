package com.onlineshop.productservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onlineshop.productservice.entity.util.Attribute;
import com.onlineshop.productservice.entity.util.Currency;
import com.onlineshop.productservice.entity.util.Inventory;

import java.util.List;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
