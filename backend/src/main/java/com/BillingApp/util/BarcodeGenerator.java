package com.BillingApp.util;


import java.io.File;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class BarcodeGenerator {

    public static void generateBarcode(String code, String outputPath) throws Exception {
        com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter()
                .encode(code, BarcodeFormat.CODE_128, 300, 100);
        Path path = new File(outputPath).toPath();
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }
}
