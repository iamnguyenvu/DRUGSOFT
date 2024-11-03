package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class testThem {

    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1056, 768);
        frame.setLocationRelativeTo(null);

        // Tạo JPanel khác làm container để chứa biểu đồ
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout()); // Đặt layout cho panel này

        // Khởi tạo đối tượng barChar_ThongKe
        barChar_ThongKe barChart = new barChar_ThongKe();

        // Thêm barChar_ThongKe vào containerPanel
        containerPanel.add(barChart, BorderLayout.CENTER);

        // Thêm containerPanel vào frame và hiển thị
        frame.add(containerPanel);
        frame.setVisible(true);

        // Cập nhật giao diện containerPanel để hiển thị ngay lập tức
        containerPanel.revalidate();
        containerPanel.repaint();
    }
}
