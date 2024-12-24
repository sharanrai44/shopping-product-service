package com.onlineshop.productservice.repository;

import com.onlineshop.productservice.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Products, String> {

    Optional<Products> findByName(String name);
}
