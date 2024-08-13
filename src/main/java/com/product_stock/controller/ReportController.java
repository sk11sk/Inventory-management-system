package com.product_stock.controller;


import com.product_stock.payload.ProductDTO;
import com.product_stock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ProductService productService;


    //http://localhost:8080/api/reports/stock
    @GetMapping("/stock")
    public ResponseEntity<List<ProductDTO>> generateStockReport() {
        return new ResponseEntity<>(productService.generateStockReport(), HttpStatus.OK);
    }
}
