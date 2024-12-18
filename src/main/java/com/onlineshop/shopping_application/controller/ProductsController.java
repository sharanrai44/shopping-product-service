package com.onlineshop.shopping_application.controller;

import com.onlineshop.shopping_application.entity.Products;
import com.onlineshop.shopping_application.entity.dto.ProductsDTO;
import com.onlineshop.shopping_application.entity.dto.UpdateProductDTO;
import com.onlineshop.shopping_application.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Products> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "inventory.available") String sortBy
    ) {
        return productService.getProductsByCategory(category, sortBy);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductsDTO productsDTO) {
        String id = productService.addProduct(productsDTO);
        return new ResponseEntity<>("Added a Product with id : " + id, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Products> updateProduct(@RequestBody UpdateProductDTO productDTO) {
        Products product = productService.updateProducts(productDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> addProduct(@RequestParam String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Record Deleted !!" + id, HttpStatus.OK);
    }

}
