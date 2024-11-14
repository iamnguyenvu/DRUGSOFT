package gui;

import javax.swing.JPanel;

import nguyenvu.components.SimpleForm;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JRadioButton;

public class ThongKe_GUI extends SimpleForm {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ThongKe_GUI() {
		setPreferredSize(new Dimension(1500, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnCenter = new JPanel();
		add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thống Kê Và Báo Cáo");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 10, 290, 44);
		pnCenter.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 53, 1480, 61);
		pnCenter.add(panel);
		panel.setLayout(null);
		
		JButton btnSanPham = new JButton("Sản Phẩm");
		btnSanPham.setBounds(10, 10, 85, 37);
		panel.add(btnSanPham);
		
		JButton btnNhanVien = new JButton("Nhân Viên");
		btnNhanVien.setBounds(122, 10, 85, 37);
		panel.add(btnNhanVien);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(10, 133, 852, 137);
		pnCenter.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thời Gian");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 10, 196, 34);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày Bắt Đầu");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 72, 100, 34);
		panel_1.add(lblNewLabel_2);
		
		JDateChooser dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setDateFormatString("dd-MM-yyyy");
		dcNgayBatDau.setForeground(new Color(0, 0, 0));
		dcNgayBatDau.setBackground(new Color(255, 255, 255));
		dcNgayBatDau.setBounds(120, 72, 196, 34);
		panel_1.add(dcNgayBatDau);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ngày Kết Thúc");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(386, 72, 100, 34);
		panel_1.add(lblNewLabel_2_1);
		
		JDateChooser dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.setDateFormatString("dd-MM-yyyy");
		dcNgayKetThuc.setBackground(new Color(255, 255, 255));
		dcNgayKetThuc.setBounds(505, 72, 196, 34);
		panel_1.add(dcNgayKetThuc);
		
		JButton btnTruyVan = new JButton("Truy Vấn");
		btnTruyVan.setFont(new Font("Arial", Font.PLAIN, 15));
		btnTruyVan.setBounds(735, 72, 105, 34);
		panel_1.add(btnTruyVan);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 297, 1480, 493);
		pnCenter.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(883, 133, 607, 137);
		pnCenter.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Sản Phẩm");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 10, 236, 34);
		panel_3.add(lblNewLabel_3);
		
		JRadioButton rdo_spBanChay = new JRadioButton("Sản Phẩm Bán Chạy");
		rdo_spBanChay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdo_spBanChay.setBounds(71, 69, 198, 44);
		panel_3.add(rdo_spBanChay);
		
		JRadioButton rdo_spBanCham = new JRadioButton("Sản Phẩm Bán Chậm");
		rdo_spBanCham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdo_spBanCham.setBounds(301, 69, 198, 44);
		panel_3.add(rdo_spBanCham);

	}
}
