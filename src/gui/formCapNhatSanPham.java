package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import entity.LoaiSanPham_entity;
import entity.SanPham_entity;
import nguyenvu.components.SimpleForm;

public class formCapNhatSanPham extends SimpleForm implements ActionListener {

	private static final long serialVersionUID = 1L;
	public JTextField tf_soLuong;
	public JTextField tf_Gia;
	public JTextField tf_HinhAnh;
	public JTextField tf_Tensp;
	public JTextField tf_KhoiLuong;
	public JTextField tf_NhaCungCap;
	private Border border;
	public JButton btnChonHinhAnh;
	public JComboBox cb_DonViTinh;
	public JButton btnXacNhan;
	public JButton btn_Huy;
	public JDateChooser dcNgaySanXuat;
	public JDateChooser dcNgayHetHan;
	public JTextArea ta_CongDung;
	public JComboBox cb_LoaiSP;
	private SanPham sanPham;
	public JTextArea ta_ThanhPhan;

	/**
	 * Create the panel.
	 */
	public formCapNhatSanPham() {
		this.sanPham = sanPham;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(1150, 800));
		setLayout(new BorderLayout(0, 0));

		JPanel pnContent = new JPanel();
		pnContent.setPreferredSize(new Dimension(1150, 800));
		Color color_pnContent = Color.decode("#f4f6f8");
		pnContent.setBackground(color_pnContent);
		add(pnContent, BorderLayout.SOUTH);
		pnContent.setLayout(null);

		JPanel pnCenter = new JPanel();
		pnCenter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnCenter.setBounds(50, 95, 716, 235);
		pnCenter.setBackground(new Color(255, 255, 255));
		pnContent.add(pnCenter);
		pnCenter.setLayout(null);

		JLabel lbTensp = new JLabel("Tên Sản Phẩm");
		lbTensp.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lbTensp.setBounds(20, 10, 105, 46);
		pnCenter.add(lbTensp);

		tf_soLuong = new JTextField();
		tf_soLuong.setColumns(10);
		tf_soLuong.setBounds(20, 166, 299, 34);
		pnCenter.add(tf_soLuong);

		JLabel lb_Gia = new JLabel("Giá");
		lb_Gia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Gia.setBounds(405, 10, 46, 46);
		pnCenter.add(lb_Gia);

		tf_Gia = new JTextField();
		tf_Gia.setColumns(10);
		tf_Gia.setBounds(405, 66, 251, 34);
		pnCenter.add(tf_Gia);

		JLabel lb_HinhAnh = new JLabel("Hình Ảnh");
		lb_HinhAnh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_HinhAnh.setBounds(405, 110, 105, 46);
		pnCenter.add(lb_HinhAnh);

		tf_HinhAnh = new JTextField();
		tf_HinhAnh.setColumns(10);
		tf_HinhAnh.setBounds(405, 166, 173, 34);
		pnCenter.add(tf_HinhAnh);

		btnChonHinhAnh = new JButton("Chọn");
		btnChonHinhAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Chọn hình ảnh");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				// Chỉ cho phép chọn các tệp hình ảnh
				fileChooser.setFileFilter(
						new javax.swing.filechooser.FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));

				// Hiển thị hộp thoại chọn tệp
				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					// Hiển thị tên tệp trong JTextField
					tf_HinhAnh.setText(selectedFile.getName());
				}
			}
		});
		btnChonHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChonHinhAnh.setBounds(581, 162, 75, 36);
		pnCenter.add(btnChonHinhAnh);

		tf_Tensp = new JTextField();
		tf_Tensp.setColumns(10);
		tf_Tensp.setBounds(22, 66, 297, 34);
		pnCenter.add(tf_Tensp);

		JLabel lb_soLuong = new JLabel("Số Lượng");
		lb_soLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_soLuong.setBounds(20, 110, 105, 46);
		pnCenter.add(lb_soLuong);

		JLabel lbCanhBaoTen = new JLabel("*");
		lbCanhBaoTen.setForeground(new Color(255, 0, 0));
		lbCanhBaoTen.setBackground(new Color(255, 0, 0));
		lbCanhBaoTen.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoTen.setBounds(118, 26, 201, 17);

		pnCenter.add(lbCanhBaoTen);

		JLabel lblCanhBaoGia = new JLabel("*");
		lblCanhBaoGia.setForeground(new Color(255, 0, 0));
		lblCanhBaoGia.setFont(new Font("Serif", Font.ITALIC, 12));
		lblCanhBaoGia.setBounds(437, 28, 166, 13);
		pnCenter.add(lblCanhBaoGia);

		JLabel lbCanhBaoSoLuong = new JLabel("*");
		lbCanhBaoSoLuong.setForeground(new Color(255, 0, 0));
		lbCanhBaoSoLuong.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoSoLuong.setBounds(91, 128, 226, 13);
		pnCenter.add(lbCanhBaoSoLuong);

		JLabel lblCui = new JLabel("* Có Đuôi .png hoặc .svg");
		lblCui.setForeground(new Color(255, 0, 0));
		lblCui.setFont(new Font("Serif", Font.ITALIC, 12));
		lblCui.setBounds(484, 128, 166, 13);
		pnCenter.add(lblCui);

		JLabel lb_ThemMoiSP = new JLabel("Cập Nhật Sản Phẩm");
		lb_ThemMoiSP.setFont(new Font("Serif", Font.PLAIN, 24));
		lb_ThemMoiSP.setBounds(50, 23, 269, 54);
		pnContent.add(lb_ThemMoiSP);

		JPanel pn_Ngay = new JPanel();
		pn_Ngay.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pn_Ngay.setBackground(new Color(255, 255, 255));
		pn_Ngay.setBounds(50, 350, 716, 149);
		pnContent.add(pn_Ngay);
		pn_Ngay.setLayout(null);

		dcNgayHetHan = new JDateChooser();
		dcNgayHetHan.setBounds(414, 101, 254, 34);
		pn_Ngay.add(dcNgayHetHan);

		JLabel lbNgayHetHan = new JLabel("Ngày Hết Hạn");
		lbNgayHetHan.setBounds(414, 45, 105, 46);
		pn_Ngay.add(lbNgayHetHan);
		lbNgayHetHan.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		dcNgaySanXuat = new JDateChooser();
		dcNgaySanXuat.setBounds(24, 101, 287, 34);
		pn_Ngay.add(dcNgaySanXuat);

		JLabel lbNgaySanXuat = new JLabel("Ngày Sản Xuất");
		lbNgaySanXuat.setBounds(24, 45, 105, 46);
		pn_Ngay.add(lbNgaySanXuat);
		lbNgaySanXuat.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		JLabel lb_ngay = new JLabel("Thời Gian");
		lb_ngay.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_ngay.setBounds(24, 10, 105, 34);
		pn_Ngay.add(lb_ngay);

		JLabel lbCanhBaoNSX = new JLabel("*");
		lbCanhBaoNSX.setForeground(new Color(255, 0, 0));
		lbCanhBaoNSX.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoNSX.setBounds(127, 61, 184, 17);
		pn_Ngay.add(lbCanhBaoNSX);

		JLabel lbCanhBaoNHH = new JLabel("*");
		lbCanhBaoNHH.setForeground(new Color(255, 0, 0));
		lbCanhBaoNHH.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoNHH.setBounds(522, 61, 184, 17);
		pn_Ngay.add(lbCanhBaoNHH);

		JPanel pn_KhoHang = new JPanel();
		pn_KhoHang.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pn_KhoHang.setBackground(new Color(255, 255, 255));
		pn_KhoHang.setBounds(50, 519, 716, 156);
		pnContent.add(pn_KhoHang);
		pn_KhoHang.setLayout(null);

		JLabel lb_khohang = new JLabel("Kho Hàng");
		lb_khohang.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_khohang.setBounds(24, 10, 98, 43);
		pn_KhoHang.add(lb_khohang);

		JLabel lb_KhoiLuong = new JLabel("Khối Lượng");
		lb_KhoiLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_KhoiLuong.setBounds(24, 48, 105, 46);
		pn_KhoHang.add(lb_KhoiLuong);

		tf_KhoiLuong = new JTextField();
		tf_KhoiLuong.setColumns(10);
		tf_KhoiLuong.setBounds(24, 104, 286, 34);
		pn_KhoHang.add(tf_KhoiLuong);

		JLabel lb_DonViTinh = new JLabel("Đơn Vị Tính");
		lb_DonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_DonViTinh.setBounds(421, 48, 105, 46);
		pn_KhoHang.add(lb_DonViTinh);

		cb_DonViTinh = new JComboBox();
		cb_DonViTinh.setBounds(419, 103, 249, 34);
		cb_DonViTinh.addItem("Vĩ");
		cb_DonViTinh.addItem("Viên");
		cb_DonViTinh.addItem("Chai");

		cb_DonViTinh.addItem("Hộp");
		pn_KhoHang.add(cb_DonViTinh);

		JLabel lbCanhBaoKL = new JLabel("*");
		lbCanhBaoKL.setForeground(new Color(255, 0, 0));
		lbCanhBaoKL.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoKL.setBounds(112, 63, 198, 17);
		pn_KhoHang.add(lbCanhBaoKL);

		JPanel pn_PhanLoai = new JPanel();
		pn_PhanLoai.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pn_PhanLoai.setBackground(new Color(255, 255, 255));
		pn_PhanLoai.setBounds(789, 95, 320, 256);
		pnContent.add(pn_PhanLoai);
		pn_PhanLoai.setLayout(null);

		JLabel lb_PhanLoai = new JLabel("Phân Loại");
		lb_PhanLoai.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_PhanLoai.setBounds(20, 10, 105, 34);
		pn_PhanLoai.add(lb_PhanLoai);

		JLabel lb_Loai = new JLabel("Loại Sản Phẩm");
		lb_Loai.setBounds(20, 39, 105, 46);
		pn_PhanLoai.add(lb_Loai);
		lb_Loai.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		cb_LoaiSP = new JComboBox();
		cb_LoaiSP.setBounds(20, 90, 271, 34);
		pn_PhanLoai.add(cb_LoaiSP);
		cb_LoaiSP.addItem("Thuốc");
		cb_LoaiSP.addItem("Thực Phẩm Chức Năng");
		cb_LoaiSP.addItem("Thiết Bị Y Tế");

		JLabel lb_Nhacc = new JLabel("Nhà Cung Cấp");
		lb_Nhacc.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Nhacc.setBounds(20, 147, 105, 46);
		pn_PhanLoai.add(lb_Nhacc);

		tf_NhaCungCap = new JTextField();
		tf_NhaCungCap.setColumns(10);
		tf_NhaCungCap.setBounds(20, 197, 271, 34);
		pn_PhanLoai.add(tf_NhaCungCap);

		JLabel lbNhacc = new JLabel("*");
		lbNhacc.setForeground(new Color(255, 0, 0));
		lbNhacc.setFont(new Font("Serif", Font.ITALIC, 12));
		lbNhacc.setBounds(122, 163, 169, 17);
		pn_PhanLoai.add(lbNhacc);

		JPanel pn_HinhAnh = new JPanel();
		pn_HinhAnh.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pn_HinhAnh.setBackground(new Color(255, 255, 255));
		pn_HinhAnh.setBounds(789, 373, 320, 302);
		pnContent.add(pn_HinhAnh);
		pn_HinhAnh.setLayout(null);

		JLabel lbCongDung = new JLabel("Công Dụng");
		lbCongDung.setVerticalAlignment(SwingConstants.TOP);
		lbCongDung.setFont(new Font("Serif", Font.PLAIN, 20));
		lbCongDung.setBounds(10, 157, 105, 34);
		pn_HinhAnh.add(lbCongDung);

		ta_CongDung = new JTextArea();
		border = BorderFactory.createLineBorder(Color.BLACK, 1); // Độ dày 1 pixel
		ta_CongDung.setBorder(border);
		ta_CongDung.setBounds(10, 190, 300, 102);
		pn_HinhAnh.add(ta_CongDung);

		ta_ThanhPhan = new JTextArea();
		ta_ThanhPhan.setBorder(border);
		ta_ThanhPhan.setBounds(10, 58, 300, 102);
		pn_HinhAnh.add(ta_ThanhPhan);

		JLabel lbThanhPhan = new JLabel("Thành Phần");
		lbThanhPhan.setVerticalAlignment(SwingConstants.TOP);
		lbThanhPhan.setFont(new Font("Serif", Font.PLAIN, 20));
		lbThanhPhan.setBounds(10, 14, 105, 34);
		pn_HinhAnh.add(lbThanhPhan);

		JLabel lbTP = new JLabel("*");
		lbTP.setForeground(new Color(255, 0, 0));
		lbTP.setFont(new Font("Serif", Font.ITALIC, 12));
		lbTP.setBounds(106, 14, 169, 22);
		pn_HinhAnh.add(lbTP);

		btn_Huy = new JButton("Hủy");
		btn_Huy.setFont(new Font("Serif", Font.PLAIN, 20));
		btn_Huy.setBounds(888, 703, 93, 42);
		btn_Huy.addActionListener(this);
		pnContent.add(btn_Huy);

		btnXacNhan = new JButton("Xác Nhận");
		btnXacNhan.setFont(new Font("Serif", Font.PLAIN, 20));
		btnXacNhan.setBounds(994, 703, 115, 42);
		pnContent.add(btnXacNhan);
		btnXacNhan.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXacNhan)) {
			
		}
	}

	public JTextField getTf_soLuong() {
		return tf_soLuong;
	}

	public void setTf_soLuong(JTextField tf_soLuong) {
		this.tf_soLuong = tf_soLuong;
	}

	public JTextField getTf_Gia() {
		return tf_Gia;
	}

	public void setTf_Gia(JTextField tf_Gia) {
		this.tf_Gia = tf_Gia;
	}

	public JTextField getTf_HinhAnh() {
		return tf_HinhAnh;
	}

	public void setTf_HinhAnh(JTextField tf_HinhAnh) {
		this.tf_HinhAnh = tf_HinhAnh;
	}

	public JTextField getTf_Tensp() {
		return tf_Tensp;
	}

	public void setTf_Tensp(JTextField tf_Tensp) {
		this.tf_Tensp = tf_Tensp;
	}

	public JTextField getTf_KhoiLuong() {
		return tf_KhoiLuong;
	}

	public void setTf_KhoiLuong(JTextField tf_KhoiLuong) {
		this.tf_KhoiLuong = tf_KhoiLuong;
	}

	public JTextField getTf_NhaCungCap() {
		return tf_NhaCungCap;
	}

	public void setTf_NhaCungCap(JTextField tf_NhaCungCap) {
		this.tf_NhaCungCap = tf_NhaCungCap;
	}

	public Border getBorder() {
		return border;
	}

	public void setBorder(Border border) {
		this.border = border;
	}

	public JButton getBtnChonHinhAnh() {
		return btnChonHinhAnh;
	}

	public void setBtnChonHinhAnh(JButton btnChonHinhAnh) {
		this.btnChonHinhAnh = btnChonHinhAnh;
	}

	public JComboBox getCb_DonViTinh() {
		return cb_DonViTinh;
	}

	public void setCb_DonViTinh(JComboBox cb_DonViTinh) {
		this.cb_DonViTinh = cb_DonViTinh;
	}

	public JButton getBtnXacNhan() {
		return btnXacNhan;
	}

	public void setBtnXacNhan(JButton btnXacNhan) {
		this.btnXacNhan = btnXacNhan;
	}

	public JButton getBtn_Huy() {
		return btn_Huy;
	}

	public void setBtn_Huy(JButton btn_Huy) {
		this.btn_Huy = btn_Huy;
	}

	public JDateChooser getDcNgaySanXuat() {
		return dcNgaySanXuat;
	}

	public void setDcNgaySanXuat(JDateChooser dcNgaySanXuat) {
		this.dcNgaySanXuat = dcNgaySanXuat;
	}

	public JDateChooser getDcNgayHetHan() {
		return dcNgayHetHan;
	}

	public void setDcNgayHetHan(JDateChooser dcNgayHetHan) {
		this.dcNgayHetHan = dcNgayHetHan;
	}

	public JTextArea getTa_CongDung() {
		return ta_CongDung;
	}

	public void setTa_CongDung(JTextArea ta_CongDung) {
		this.ta_CongDung = ta_CongDung;
	}

	public JComboBox getCb_LoaiSP() {
		return cb_LoaiSP;
	}

	public void setCb_LoaiSP(JComboBox cb_LoaiSP) {
		this.cb_LoaiSP = cb_LoaiSP;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public JTextArea getTa_ThanhPhan() {
		return ta_ThanhPhan;
	}

	public void setTa_ThanhPhan(JTextArea ta_ThanhPhan) {
		this.ta_ThanhPhan = ta_ThanhPhan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
