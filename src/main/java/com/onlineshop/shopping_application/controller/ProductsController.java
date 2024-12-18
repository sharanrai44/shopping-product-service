package com.onlineshop.shopping_application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/my-products")
    public ResponseEntity<String> products() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/add")
    public ResponseEntity<String> addProduct() {
        return new ResponseEntity<>("Added a Product", HttpStatus.OK);
    }

}
