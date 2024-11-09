/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import dao.BanHang_DAO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import raven.alerts.MessageAlerts;

/**
 *
 * @author nguyenvu
 */
public class GenerateCode {

    public static String generateBillCode() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyMMdd");
        Random random = new Random();
        int rdDigit = 1000 + random.nextInt(90000);
        String billCode = "HD" + sdf.format(new java.util.Date()) + rdDigit;
        return billCode;
    }

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

    public static BufferedImage matToBufferedImage(Mat mat) throws Exception {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }

    public static String decodeBarcode(BufferedImage bufferedImage) {
        try {
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            return null;
        }
    }
    

    public static BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static void playSound(String filePath) throws IOException {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static InputStream generateQrcode(String code) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMat = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, 60, 60);
        BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMat);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(img, "png", output);
        return new ByteArrayInputStream(output.toByteArray());
    }

    private static String decodeQrCode(BufferedImage bufferedImage) throws IOException, NotFoundException, ChecksumException, FormatException {
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return result.getText();
    }

    public static String startQrcodeScanner() {
        System.loadLibrary("opencv_java4100");
        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Không thể mở camera!", MessageAlerts.MessageType.ERROR);
            return null;
        }

        Mat frame = new Mat();
        JFrame window = new JFrame("Quét mã QR");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel();
        window.add(label);
        window.setSize(800, 700);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        String scannedCode = null;

        while (scannedCode == null && window.isVisible()) {
            if (camera.read(frame)) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = matToBufferedImageQrcode(frame);
                } catch (IOException ex) {
                    Logger.getLogger(GenerateCode.class.getName()).log(Level.SEVERE, null, ex);
                }
                label.setIcon(new ImageIcon(bufferedImage));

                try {
                    String qrCodeText = null;
                    try {
                        qrCodeText = decodeQrCode(bufferedImage);
                    } catch (IOException | ChecksumException | FormatException ex) {
                        Logger.getLogger(GenerateCode.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (qrCodeText != null && !qrCodeText.isEmpty()) {
                        scannedCode = qrCodeText;
                        try {
                            playSound("barcodeSP/barcodeSound.wav");
                        } catch (IOException ex) {
                            Logger.getLogger(GenerateCode.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("QR Code detected: " + qrCodeText);
                    }
                } catch (NotFoundException e) {
                    
                }
            }
        }

        camera.release();
        window.dispose();

        return scannedCode;
    }

    private static BufferedImage matToBufferedImageQrcode(Mat mat) throws IOException {
        int width = mat.cols();
        int height = mat.rows();
        int channels = mat.channels();

        byte[] source = new byte[width * height * channels];
        mat.get(0, 0, source);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(source, 0, targetPixels, 0, source.length);

        return image;
    }

    public static void startScannerFace() {
        System.loadLibrary("opencv_java4100");
        VideoCapture camera = new VideoCapture(0);

    }
}
