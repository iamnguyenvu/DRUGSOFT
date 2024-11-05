/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 *
 * @author HP
 */
public class BarcodeScannerWithCamera {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Load thư viện OpenCV
    }

    // Hàm chuyển đổi Mat của OpenCV sang BufferedImage
    private static BufferedImage matToBufferedImage(Mat mat) throws IOException {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }

    // Hàm quét mã barcode từ BufferedImage sử dụng ZXing
    private static String decodeBarcode(BufferedImage bufferedImage) {
        try {
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText(); // Trả về mã sản phẩm từ barcode
        } catch (NotFoundException e) {
            // Không tìm thấy mã barcode trong khung hình
            return null;
        }
    }
}
