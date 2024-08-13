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
            String input = barcodeText;
            int i = input.hashCode();
            String stringbar = String.valueOf(i);
           // UUID uuid = UUID.nameUUIDFromBytes(input.getBytes(StandardCharsets.UTF_8));
           // String stringbar = uuid.toString();
            Path path = Paths.get("D:\\barcode\\" + stringbar + ".png");//

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return path.toString();
        } catch (Exception e) {
            throw new RuntimeException("Barcode generation failed", e);
        }
    }
}
