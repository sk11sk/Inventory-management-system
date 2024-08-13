package com.product_stock.controller;



import com.itextpdf.text.DocumentException;

import com.product_stock.entity.Product;
import com.product_stock.service.PDFservice;
import com.product_stock.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class PDFcontroller {// this is the get the pdf in table form

    @Autowired
    private PDFservice pdfService;

    @Autowired
    private ProductRepository productRepository; // Assuming you have a UserRepository for accessing User data

    //http://localhost:8080/api/pdf/download
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf() throws IOException, DocumentException {
        List<Product> productList = productRepository.findAll(); // Fetch your user data from the database

        byte[] pdfBytes = pdfService.generatePdf(productList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "product_list.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}