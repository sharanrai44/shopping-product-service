package com.onlineshop.productservice.service;

import com.onlineshop.productservice.entity.Products;
import com.onlineshop.productservice.entity.dto.ProductsDTO;
import com.onlineshop.productservice.entity.dto.UpdateProductDTO;
import com.onlineshop.productservice.entity.util.Currency;
import com.onlineshop.productservice.exception.ProductException;
import com.onlineshop.productservice.repository.ProductRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

    private final MongoTemplate mongoTemplate;

    public ProductService(ModelMapper modelMapper, ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public String addProduct(ProductsDTO productsDTO) {
        if (productRepository.findByName(productsDTO.getName()).isPresent()) {
            throw new ProductException("Element already exist");
        }
        Products product = convertToProduct(productsDTO);
        Products save = productRepository.save(product);
        return save.getId();
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public Products updateProducts(UpdateProductDTO productDTO) {
        Optional<Products> mayBeProduct = productRepository.findById(productDTO.getId());
        return mayBeProduct.map(products -> {
            if (productDTO.getInventory() != null) {
                products.setInventory(productDTO.getInventory());
            }
            if (ObjectUtils.isNotEmpty(productDTO.getNewPrice())) {
                products.setPrice(new Currency(products.getPrice().getCurrencyCode(), productDTO.getNewPrice()));
            }
            return productRepository.save(products);
        }).orElseThrow(() -> new NoSuchElementException(" No Product found with Id : " + productDTO.getId()));
    }


    public List<Products> getProductsByCategory(String category, String sortBy) {
        Query query = new Query();
        if (StringUtils.isNotEmpty(category)) {
            query.addCriteria(Criteria.where("category").is(category));
        }
        if(StringUtils.isEmpty(sortBy)){
            sortBy="inventory.available";
        }

        query.with(Sort.by(Sort.Direction.DESC, sortBy));
        return mongoTemplate.find(query, Products.class);
    }

    public Products convertToProduct(ProductsDTO productsDTO) {
        return modelMapper.map(productsDTO, Products.class);
    }
}

