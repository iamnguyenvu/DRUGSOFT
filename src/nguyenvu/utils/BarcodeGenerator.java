/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author HP
 */
public class BarcodeGenerator {
     public static void createBarcode(String maSP) throws Exception {
        String barcodeText = maSP;
        int width = 300;
        int height = 100;
        String fileType = "png";

        BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, width, height);

        java.nio.file.Path path = Paths.get("barcodeSP/" + barcodeText + ".png");
        File directory = new File("barcodeSP");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        MatrixToImageWriter.writeToPath(bitMatrix, fileType, path);
//        System.out.println("Barcode created for " + maSP + " at: " + path.toString());
    }
}
