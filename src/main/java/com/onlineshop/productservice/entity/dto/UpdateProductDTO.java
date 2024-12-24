package com.onlineshop.productservice.entity.dto;

import com.onlineshop.productservice.entity.util.Inventory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProductDTO {

    @NotNull
    private String id;

    private Double newPrice;

    private Inventory inventory;
}
