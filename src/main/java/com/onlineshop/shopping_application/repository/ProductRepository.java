package com.onlineshop.shopping_application.repository;

import com.onlineshop.shopping_application.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Products, String> {

    Optional<Products> findByName(String name);
}
