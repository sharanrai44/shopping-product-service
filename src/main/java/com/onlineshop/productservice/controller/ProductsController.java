package com.onlineshop.productservice.controller;

import com.onlineshop.productservice.entity.Products;
import com.onlineshop.productservice.entity.dto.ProductsDTO;
import com.onlineshop.productservice.entity.dto.UpdateProductDTO;
import com.onlineshop.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    private Logger logger= LoggerFactory.getLogger(ProductsController.class);

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Products> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy
    ) {
        logger.info("getProducts is called");
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
