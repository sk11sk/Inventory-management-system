package com.product_stock.service;

import com.product_stock.payload.ProductDTO;

public interface BarcodeService {
    String generateBarcode(String barcodeText );
}
