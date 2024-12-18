package com.onlineshop.shopping_application.service;

import com.onlineshop.shopping_application.entity.Products;
import com.onlineshop.shopping_application.entity.dto.ProductsDTO;
import com.onlineshop.shopping_application.entity.dto.UpdateProductDTO;
import com.onlineshop.shopping_application.entity.util.Currency;
import com.onlineshop.shopping_application.entity.util.Inventory;
import com.onlineshop.shopping_application.exception.ProductException;
import com.onlineshop.shopping_application.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        Inventory inventory = new Inventory(10, 8, 2);
        Products product1 = new Products("1", "Laptop", "Dell inspiron",
                "Dell", "Hi performance", new Currency("INR", 11111.00),
                inventory, new ArrayList<>());
        ProductsDTO productDTO = createProductDTO();
        when(productRepository.findByName("Dell inspiron")).thenReturn(Optional.ofNullable(null));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        String s = productService.addProduct(productDTO);
        assertNotNull("Id should not be null", s);
    }

    @Test
    public void testAddProductWithException() {
        Inventory inventory = new Inventory(10, 8, 2);
        Products product1 = new Products("1", "Laptop", "Dell inspiron",
                "Dell", "Hi performance", new Currency("INR", 11111.00),
                inventory, new ArrayList<>());
        ProductsDTO productDTO = createProductDTO();

        when(productRepository.findByName("Dell inspiron")).thenThrow(new ProductException("Element already exist"));
        Exception exception = assertThrows(
                Exception.class,
                () -> productService.addProduct(productDTO)
        );
        assertEquals("Element already exist", exception.getMessage());
    }

    @Test
    public void testDeleteProduct() {
        String productId = "12345";
        productService.deleteProduct(productId);
        verify(productRepository).deleteById(productId);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testUpdateProductsPrice() {
        Inventory inventory = new Inventory(10, 8, 2);
        Products product1 = new Products("1", "Laptop", "Dell inspiron", "Dell", "Hi performance",
                new Currency("INR", 11111.00),
                inventory, new ArrayList<>());

        Optional<Products> product2 = Optional.of(product1);
        when(productRepository.findById("1")).thenReturn(product2);
        when(productRepository.save(any(Products.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateProductDTO productDTO = new UpdateProductDTO();
        productDTO.setId("1");
        productDTO.setNewPrice(22222.00);
        Products products = productService.updateProducts(productDTO);

        assertNotNull(products);
        assertEquals(22222.00, products.getPrice().getAmount());
    }

    @Test
    void testUpdateProductsInventory() {
        Inventory inventory = new Inventory(10, 8, 2);
        Products product1 = new Products("1", "Laptop", "Dell inspiron", "Dell", "Hi performance",
                new Currency("INR", 11111.00),
                inventory, new ArrayList<>());

        Optional<Products> product2 = Optional.of(product1);
        when(productRepository.findById("1")).thenReturn(product2);
        when(productRepository.save(any(Products.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateProductDTO productDTO = new UpdateProductDTO();
        productDTO.setId("1");
        productDTO.setInventory(new Inventory(20, 10, 2));
        Products products = productService.updateProducts(productDTO);

        assertNotNull(products);
        assertEquals(10, products.getInventory().getAvailable());
    }

    @Test
    void testGetProductsByCategorySortByPrice() {
        String category = "Laptop";
        String sortBy = "price.amount";

        Inventory inventory = new Inventory(10, 8, 2);
        Products product1 = new Products("1", "Laptop", "Dell inspiron", "Dell", "Hi performance",
                new Currency("INR", 11111.00),
                inventory, new ArrayList<>());

        Products product2 = new Products("2", "Laptop", "hp inspiron", "hp", "light weight",
                new Currency("INR", 99999.00),
                inventory, new ArrayList<>());

        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Arrays.asList(product2, product1));
        List<Products> products = productService.getProductsByCategory(category, sortBy);

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getCategory());
    }

    @Test
    void testGetProductsByCategorySortByInventoryAvailable() {
        String category = "Smartphone";
        String sortBy = "inventory.available";

        Inventory inventory = new Inventory(100, 8, 2);
        Products product1 = new Products("1", "Laptop", "Dell inspiron", "Dell", "Hi performance",
                new Currency("INR", 11111.00),
                inventory, new ArrayList<>());

        inventory.setTotal(200);
        Products product2 = new Products("2", "Smartphone", "iphone 16 max pro", "apple", "light weight",
                new Currency("INR", 170000.00),
                inventory, new ArrayList<>());

        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Arrays.asList(product2));
        List<Products> products = productService.getProductsByCategory(category, sortBy);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Smartphone", products.get(0).getCategory());
    }

    public static ProductsDTO createProductDTO() {
        ProductsDTO products = new ProductsDTO();
        products.setCategory("Laptop");
        products.setBrand("Dell");
        products.setName("Dell inspiron");
        products.setDescription("Hi performance");
        products.setPrice(new Currency("INR", 11111.00));
        products.setInventory(new Inventory(100, 8, 2));
        products.setAttributes(new ArrayList<>());
        return products;
    }

}

