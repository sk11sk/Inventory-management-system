package com.product_stock.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id; // This can be null when creating a new product
    private String name;
    private String description;
    private int quantity;
    private String barcode; // This can be null initially and will be generated later
    private double price;


}
