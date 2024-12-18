package com.onlineshop.shopping_application.mapper;

import com.onlineshop.shopping_application.entity.dto.ProductsDTO;
import com.onlineshop.shopping_application.entity.Products;

public class ProductsMapper {
    public static Products toProducts(ProductsDTO productsDTO) {
        Products products = new Products();
        products.setCategory(productsDTO.getCategory());
        products.setBrand(productsDTO.getBrand());
        products.setName(productsDTO.getName());
        products.setDescription(productsDTO.getDescription());
        products.setPrice(productsDTO.getPrice());
        products.setInventory(productsDTO.getInventory());
        products.setAttributes(productsDTO.getAttributes());
        return products;
    }
}
