package com.product_stock.serviceImpl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.product_stock.service.BarcodeService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BarcodeServiceImpl implements BarcodeService {

    @Override
    public String generateBarcode(String barcodeText) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, 200, 100);
            Path path = Paths.get("D:\\barcode\\barcode" + barcodeText + ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return path.toString();
        } catch (Exception e) {
            throw new RuntimeException("Barcode generation failed", e);
        }
    }
}
