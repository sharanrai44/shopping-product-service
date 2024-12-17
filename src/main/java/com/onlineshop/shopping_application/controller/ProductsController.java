package com.onlineshop.shopping_application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @GetMapping("my-products")
    public ResponseEntity<String> products() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}