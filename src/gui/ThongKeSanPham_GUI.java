package gui;

import javax.swing.JPanel;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.RoundedPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThongKeSanPham_GUI extends SimpleForm {

    private static final long serialVersionUID = 1L;
    private JPanel pnContent;
    private JPanel barChar_ThongKe; // Giả định đây là biểu đồ sản phẩm bán chạy/bán chậm

    /**
     * Create the panel.
     */
    public ThongKeSanPham_GUI() {
        setPreferredSize(new Dimension(1500, 800));
        setLayout(new BorderLayout(0, 0));
        
        JPanel pnCenter = new JPanel();
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        
        RoundedPanel panel = new RoundedPanel(50);
        panel.setLayout(null);
        panel.setBackground(new Color(11,101,136));
        panel.setBounds(20, 10, 1480, 80);
        pnCenter.add(panel);
        
        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(null);
        panel_2.setBackground(new Color(11,101,136));
        panel_2.setBounds(550, 7, 900, 73);
        panel.add(panel_2);
        
        JButton btnDoanhSoNV = new JButton("Sản Phẩm Bán Chạy,Bán Chậm");
        btnDoanhSoNV.setForeground(Color.BLACK);
        btnDoanhSoNV.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDoanhSoNV.setBackground(Color.WHITE);
        btnDoanhSoNV.setBounds(35, 11, 248, 46);
        panel_2.add(btnDoanhSoNV);
        
        JButton btnNhanVien = new JButton("Sản Phẩm Hết Hạn");
        btnNhanVien.setForeground(Color.BLACK);
        btnNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNhanVien.setBackground(Color.WHITE);
        btnNhanVien.setBounds(343, 11, 248, 45);
        panel_2.add(btnNhanVien);
        
        JButton btnSPHetHang = new JButton("Sản Phẩm Hết Hàng");
        btnSPHetHang.setForeground(Color.BLACK);
        btnSPHetHang.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSPHetHang.setBackground(Color.WHITE);
        btnSPHetHang.setBounds(642, 11, 248, 45);
        panel_2.add(btnSPHetHang);
        
        JLabel lblNewLabel = new JLabel("Thống Kê Sản Phẩm");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setBounds(10, 10, 210, 56);
        panel.add(lblNewLabel);
        
        pnContent = new JPanel();
        pnContent.setBounds(10, 100, 1480, 700);
        pnCenter.add(pnContent);
        pnContent.setLayout(new BorderLayout(0, 0));
        
        // Khởi tạo barChar_ThongKe mặc định khi giao diện bắt đầu
        barChar_ThongKe = new barChar_ThongKe(); // Giả định rằng đây là một JPanel
        pnContent.add(barChar_ThongKe, BorderLayout.CENTER);
        
        
        // Xử lý sự kiện khi nhấn nút btnSanPham
        btnDoanhSoNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll(); // Xóa toàn bộ nội dung hiện tại
                barChar_ThongKe sptk = new barChar_ThongKe();
                pnContent.add(sptk, BorderLayout.CENTER); // Thêm barChar_ThongKe
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        
        // Xử lý sự kiện khi nhấn nút btnNhanVien
        btnNhanVien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll(); 
                pnSanPhamHetHan pnHH = new pnSanPhamHetHan();
                
                pnContent.add(pnHH, BorderLayout.CENTER); // Thêm jp2
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        btnSPHetHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll(); 
                pnSanPhamHetHang pnHH = new pnSanPhamHetHang();
                
                pnContent.add(pnHH, BorderLayout.CENTER); // Thêm jp2
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        
    }
}
