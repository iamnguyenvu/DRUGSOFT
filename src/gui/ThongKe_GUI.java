package gui;

import javax.swing.JPanel;

import nguyenvu.components.SimpleForm;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

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
		
		JPanel panel =  new JPanel();
		panel.setBounds(94, 114, 326, 246);
		pnCenter.add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(22, 390, 1468, 400);
		pnCenter.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(594, 114, 326, 246);
		pnCenter.add(panel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 10, 1480, 94);
		pnCenter.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thống Kê");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(30, 10, 187, 74);
		panel_4.add(lblNewLabel);
		
		JDateChooser dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.setToolTipText("Từ Ngày");
		dcNgayBatDau.setBounds(423, 30, 253, 31);
		panel_4.add(dcNgayBatDau);
		
		JDateChooser dcNgayBatDau_1 = new JDateChooser();
		dcNgayBatDau_1.setBounds(974, 30, 253, 31);
		panel_4.add(dcNgayBatDau_1);
		
		JButton btnXacNhan = new JButton("Xác Nhận");
		btnXacNhan.setFont(new Font("Arial", Font.BOLD, 12));
		btnXacNhan.setBounds(1278, 30, 93, 31);
		panel_4.add(btnXacNhan);
		
		JLabel lblNewLabel_1 = new JLabel("Từ Ngày");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(333, 30, 87, 31);
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Đến Ngày");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(877, 30, 87, 31);
		panel_4.add(lblNewLabel_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(1095, 114, 326, 246);
		pnCenter.add(panel_1_1);

	}
}
