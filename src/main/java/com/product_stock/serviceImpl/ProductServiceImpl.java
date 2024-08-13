package com.product_stock.serviceImpl;

import com.product_stock.entity.Product;
import com.product_stock.payload.ProductDTO;
import com.product_stock.repository.ProductRepository;
import com.product_stock.service.BarcodeService;
import com.product_stock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BarcodeService barcodeService;

    @Override
    public ProductDTO createOrUpdateProduct(ProductDTO productDTO) {
        Product product = new Product();
        if (productDTO.getId() != null) {
            product = productRepository.findById(productDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        }
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());

        // Save the product to generate an ID if it's new
        product = productRepository.save(product);

        // Generate barcode if it's a new product or if it doesn't have one
        if (product.getBarcode() == null) {
            String barcode = barcodeService.generateBarcode(productDTO.getName()); //+ product.getId()
            product.setBarcode(barcode);
            product = productRepository.save(product);
        }

        return convertToProductDTO(product);
    }

    @Override
    public ProductDTO addStock(String barcode, int additionalQuantity) {
        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() + additionalQuantity);
        product = productRepository.save(product);
        return convertToProductDTO(product);
    }

    @Override
    public List<ProductDTO> generateStockReport() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setQuantity(product.getQuantity());
        dto.setBarcode(product.getBarcode());
        dto.setPrice(product.getPrice());
        return dto;
    }
}

