package com.product_stock.controller;

import com.product_stock.payload.ProductDTO;
import com.product_stock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    //http://localhost:8080/api/products
    /*
    {
    "name": "table",
    "description": "This is a sample product.",
    "quantity": 100,
    "price": 19.99
    }
    */
    @PostMapping
    public ResponseEntity<ProductDTO> createOrUpdateProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createOrUpdateProduct(productDTO), HttpStatus.CREATED);
    }

    //http://localhost:8080/api/products/stock?barcode=D%3A%5Cbarcode%5Cbarcodetable.png&quantity=50
    @PutMapping("/stock")
    public ResponseEntity<ProductDTO> addStock(@RequestParam ("barcode")String barcode, @RequestParam ("quantity")int quantity) {
        return new ResponseEntity<>(productService.addStock(barcode, quantity), HttpStatus.OK);
    }
}
