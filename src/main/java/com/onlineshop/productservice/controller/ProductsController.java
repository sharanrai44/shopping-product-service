package com.onlineshop.productservice.controller;

import com.onlineshop.productservice.dto.ApiResponse;
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

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";


    private final ProductService productService;

    private Logger logger = LoggerFactory.getLogger(ProductsController.class);

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Products>>> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy
    ) {
        logger.info("getProducts is called");

        List<Products> productsByCategory = productService.getProductsByCategory(category, sortBy);
        ApiResponse<List<Products>> listApiResponse = new ApiResponse<>(SUCCESS, productsByCategory, null);
        return new ResponseEntity<>(listApiResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addProduct(@RequestBody ProductsDTO productsDTO) {
        String id = productService.addProduct(productsDTO);
        ApiResponse<String> response = new ApiResponse<>(SUCCESS, id, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Products>> updateProduct(@RequestBody UpdateProductDTO productDTO) {
        Products product = productService.updateProducts(productDTO);
        ApiResponse<Products> response = new ApiResponse<>(SUCCESS, product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> addProduct(@RequestParam String id) {
        productService.deleteProduct(id);
        String msg = "Record Deleted !!" + id;
        ApiResponse<String> response = new ApiResponse<>(SUCCESS, msg);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
