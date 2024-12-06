/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.FaceDetectorYN;
import org.opencv.videoio.VideoCapture;
import raven.alerts.MessageAlerts;

/**
 *
 * @author HP
 */
public class LiveFaceDetection {
    static {
        System.loadLibrary("opencv_java4100");
    }
    
    public static void startDetectFace() {
        System.loadLibrary("opencv_java4100");
        
        String cascadeFile = "D:/Download/PTUD/DRUGSOFT/library/java/haarcascade_frontalface_default.xml";
        CascadeClassifier faceDetector = new CascadeClassifier(cascadeFile);


        VideoCapture camera = new VideoCapture(0);
        
        if(!camera.isOpened()) {
            System.out.println("Lỗi: Không thể mở camera!");
            MessageAlerts.getInstance().showMessage("Lỗi", "Không thể mở camera!", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        JFrame window = new JFrame("Face Detection");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(640, 480);
        JLabel imageLabel = new JLabel();
        window.add(imageLabel);
        window.setVisible(true);
        
        Mat frame = new Mat();
        
        while(true) {
            if(!camera.read(frame)) {
                MessageAlerts.getInstance().showMessage("Lỗi", "Không thể nhân dữ liệu từ camera!", MessageAlerts.MessageType.ERROR);
                return;
            }
            
            Mat grayFrame = new Mat();
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
            
            MatOfRect faceDetections = new  MatOfRect();
            faceDetector.detectMultiScale(grayFrame, faceDetections, 1.1, 10, 0, new Size(30, 30), new Size());
            
            for(Rect rect: faceDetections.toArray()) {
                Imgproc.rectangle(frame, new org.opencv.core.Point(rect.x, rect.y),
                        new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0));
            }
            
            Imgcodecs.imwrite("detected_face.png", frame);
            
            ImageIcon image = new ImageIcon(convertMatToImage(frame));
            imageLabel.setIcon(image);
            if (!window.isVisible()) break;
        }
        
        camera.release();
        window.dispose();
    }
    
    private static BufferedImage convertMatToImage(Mat mat) {
        int width = mat.width(), height = mat.height(), channels = mat.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        mat.get(0, 0, sourcePixels);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
        return image;
    }
}
