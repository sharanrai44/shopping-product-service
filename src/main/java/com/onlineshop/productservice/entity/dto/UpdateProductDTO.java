package com.onlineshop.productservice.entity.dto;

import com.onlineshop.productservice.entity.util.Inventory;
import jakarta.validation.constraints.NotNull;

public class UpdateProductDTO {

    @NotNull
    private String id;

    private Double newPrice;

    private Inventory inventory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
