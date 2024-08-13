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


    //http://localhost:8080/api/reports?barcode=D%3A%5Cbarcode%5C921b645e-e3c8-34d9-982d-6e564ff58036.png
    //http://localhost:8080/api/reports?barcode=D%3A%5Cbarcode%5C[add the barcode.png]
    @GetMapping
    public ResponseEntity<ProductDTO> generateParticularStockReport(@RequestParam  ("barcode") String barcode) {
        return new ResponseEntity<>(productService.generateParticularStockReport(barcode), HttpStatus.OK);
    }
}
