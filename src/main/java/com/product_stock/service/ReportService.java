package com.product_stock.service;

import com.product_stock.payload.ProductDTO;

import java.util.List;

public interface ReportService {
    List<ProductDTO> generateStockReport();
}

