package com.product_stock.service;

import com.product_stock.payload.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createOrUpdateProduct(ProductDTO productDTO);
    ProductDTO addStock(String barcode, int additionalQuantity);
    List<ProductDTO> generateStockReport();
}
