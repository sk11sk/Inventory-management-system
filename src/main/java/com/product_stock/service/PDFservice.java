package com.product_stock.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.product_stock.entity.Product;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PDFservice {

    public byte[] generatePdf(List<Product> productList ) throws DocumentException {
        Document document = new Document(PageSize.A4);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        PdfPTable table = new PdfPTable(6); // Adjust the number of columns based on your User entity
        addTableHeader(table);
        addUserRows(table, productList);

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell("ID");
        table.addCell("name");
        table.addCell("barcode");
        table.addCell("Description");
        table.addCell("Price");
        table.addCell("Quantity");
    }

    private void addUserRows(PdfPTable table, List<Product> productList) {
        for (Product product : productList) {
            table.addCell(String.valueOf(product.getId()));
            table.addCell(product.getName());
            table.addCell(product.getBarcode());
            table.addCell(product.getDescription());
            table.addCell(String.valueOf(product.getPrice()));
            table.addCell(String.valueOf(product.getQuantity()));
        }
    }
}