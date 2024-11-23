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

public class ThongKeDoanhThu_GUI extends SimpleForm {

    private static final long serialVersionUID = 1L;
    private JPanel pnContent;
	private JButton btnSoLuongGiaoDich;
	private JButton btnDoanhSoBanHang;
	private JButton btnSLDDTQ;
	private JButton btnDoanhSoBanHangNhanVien;

    /**
     * Create the panel.
     */
    public ThongKeDoanhThu_GUI() {
        setPreferredSize(new Dimension(1500, 800));
        setLayout(new BorderLayout(0, 0));
        
        JPanel pnCenter = new JPanel();
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        
        pnContent = new JPanel();
        pnContent.setBounds(10, 89, 1480, 711);
        pnCenter.add(pnContent);
        pnContent.setLayout(new BorderLayout(0, 0));
        
        
        RoundedPanel panel = new RoundedPanel(50);
        panel.setLayout(null);
        panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panel.setBackground(new Color(11,101,136));
        panel.setBounds(10, 10, 1490, 69);
        pnCenter.add(panel);
        
        RoundedPanel panel_2 = new RoundedPanel(50);
        panel_2.setLayout(null);
        panel_2.setBorder(null);
        panel_2.setBackground(new Color(11,101,136));
        panel_2.setBounds(255, 0, 1212, 69);
        panel.add(panel_2);
        
        btnSoLuongGiaoDich = new JButton("Số Lượng Giao Dịch Nhân Viên");
        btnSoLuongGiaoDich.setForeground(Color.BLACK);
        btnSoLuongGiaoDich.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSoLuongGiaoDich.setBackground(Color.WHITE);
        btnSoLuongGiaoDich.setBounds(20, 10, 282, 49);
        panel_2.add(btnSoLuongGiaoDich);
        
        btnDoanhSoBanHang = new JButton("Doanh Số Bán Hàng Tổng Quan");
        btnDoanhSoBanHang.setForeground(Color.BLACK);
        btnDoanhSoBanHang.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDoanhSoBanHang.setBackground(Color.WHITE);
        btnDoanhSoBanHang.setBounds(325, 10, 282, 49);
        panel_2.add(btnDoanhSoBanHang);
        
        btnDoanhSoBanHangNhanVien = new JButton("Doanh Số Bán Hàng Của Nhân Viên");
        btnDoanhSoBanHangNhanVien.setForeground(Color.BLACK);
        btnDoanhSoBanHangNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDoanhSoBanHangNhanVien.setBackground(Color.WHITE);
        btnDoanhSoBanHangNhanVien.setBounds(628, 10, 282, 49);
        panel_2.add(btnDoanhSoBanHangNhanVien);
        
        btnSLDDTQ = new JButton("Số Lượng Giao Dịch Tổng Quan");
        btnSLDDTQ.setForeground(Color.BLACK);
        btnSLDDTQ.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSLDDTQ.setBackground(Color.WHITE);
        btnSLDDTQ.setBounds(930, 10, 282, 49);
        panel_2.add(btnSLDDTQ);
        
        JLabel lblNewLabel = new JLabel("Thống Kê Doanh Thu");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setBounds(10, 0, 215, 69);
        panel.add(lblNewLabel);
        
        
        // Khởi tạo barChar_ThongKe mặc định khi giao diện bắt đầu
        ThongKeSoLuongGiaoDichCuaNhanVien tksldd = new ThongKeSoLuongGiaoDichCuaNhanVien(); // Giả định rằng đây là một JPanel
        pnContent.add(tksldd, BorderLayout.CENTER);
        
        btnSoLuongGiaoDich.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll(); // Xóa toàn bộ nội dung hiện tại
                ThongKeSoLuongGiaoDichCuaNhanVien tksldd = new ThongKeSoLuongGiaoDichCuaNhanVien();
                pnContent.add(tksldd, BorderLayout.CENTER);
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        btnDoanhSoBanHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll(); // Xóa toàn bộ nội dung hiện tại
                DoanhSoBanHang sdbh = new DoanhSoBanHang();
                pnContent.add(sdbh, BorderLayout.CENTER);
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        btnSLDDTQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll(); // Xóa toàn bộ nội dung hiện tại
                ThongKeSoLuongGiaoDichTongQuan slddtq = new ThongKeSoLuongGiaoDichTongQuan();
                pnContent.add(slddtq, BorderLayout.CENTER);
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
        btnDoanhSoBanHangNhanVien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnContent.removeAll(); // Xóa toàn bộ nội dung hiện tại
                ThongKeDoanhSoBanHangCuaNhanVien dsbhnv = new ThongKeDoanhSoBanHangCuaNhanVien();
                pnContent.add(dsbhnv, BorderLayout.CENTER);
                pnContent.revalidate();
                pnContent.repaint();
            }
        });
    }
}
