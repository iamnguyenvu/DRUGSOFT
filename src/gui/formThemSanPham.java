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
import java.util.Calendar;
import java.util.Random;

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

public class formThemSanPham extends SimpleForm implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField tf_soLuong;
	private JTextField tf_Gia;
	private JTextField tf_HinhAnh;
	private JTextField tf_Tensp;
	private JTextField tf_KhoiLuong;
	private JTextField tf_NhaCungCap;
	private Border border;
	private JButton btnChonHinhAnh;
	private JComboBox cb_DonViTinh;
	private JButton btnXacNhan;
	private JButton btn_Huy;
	private JDateChooser dcNgaySanXuat;
	private JDateChooser dcNgayHetHan;
	private JTextArea ta_CongDung;
	private JComboBox cb_LoaiSP;
	private SanPham sanPham;
	private JTextArea ta_ThanhPhan;
	private JTextField tf_thue;
	private JTextField tf_giaNhap;

	/**
	 * Create the panel.
	 */
	public formThemSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(1150, 800));
		setLayout(new BorderLayout(0, 0));

		JPanel pnContent = new JPanel();
		pnContent.setForeground(new Color(0, 0, 0));
		pnContent.setPreferredSize(new Dimension(1150, 800));
		Color color_pnContent = Color.decode("#f4f6f8");
		pnContent.setBackground(new Color(255, 255, 255));
		add(pnContent, BorderLayout.SOUTH);
		pnContent.setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1); // Viền màu xanh dương, độ dày 2 pixel

		JPanel pnCenter = new JPanel();
		pnCenter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnCenter.setBounds(50, 95, 716, 235);
		pnCenter.setBackground(new Color(255, 255, 255));
		pnContent.add(pnCenter);
		pnCenter.setLayout(null);

		JLabel lbTensp = new JLabel("Tên Sản Phẩm");
		lbTensp.setForeground(new Color(0, 0, 0));
		lbTensp.setBackground(new Color(255, 255, 255));
		lbTensp.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lbTensp.setBounds(20, 10, 105, 46);
		pnCenter.add(lbTensp);

		tf_soLuong = new JTextField();
		tf_soLuong.setForeground(new Color(0, 0, 0));
		tf_soLuong.setBackground(new Color(255, 255, 255));
		tf_soLuong.setColumns(10);
		tf_soLuong.setBorder(border);
		tf_soLuong.setBounds(20, 166, 211, 34);
		pnCenter.add(tf_soLuong);

		JLabel lb_Gia = new JLabel("Giá Nhập");
		lb_Gia.setForeground(new Color(0, 0, 0));
		lb_Gia.setBackground(new Color(255, 255, 255));
		lb_Gia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Gia.setBounds(322, 10, 86, 46);
		pnCenter.add(lb_Gia);

		tf_Gia = new JTextField();
		tf_Gia.setForeground(new Color(0, 0, 0));
		tf_Gia.setBackground(new Color(255, 255, 255));
		tf_Gia.setColumns(10);
		tf_Gia.setBounds(517, 66, 158, 34);
		tf_Gia.setBorder(border);
		pnCenter.add(tf_Gia);

		JLabel lb_HinhAnh = new JLabel("Hình Ảnh");
		lb_HinhAnh.setForeground(new Color(0, 0, 0));
		lb_HinhAnh.setBackground(new Color(255, 255, 255));
		lb_HinhAnh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_HinhAnh.setBounds(322, 110, 105, 46);
		pnCenter.add(lb_HinhAnh);

		tf_HinhAnh = new JTextField();
		tf_HinhAnh.setForeground(new Color(0, 0, 0));
		tf_HinhAnh.setBackground(new Color(255, 255, 255));
		tf_HinhAnh.setColumns(10);
		tf_HinhAnh.setBorder(border);
		tf_HinhAnh.setBounds(322, 166, 274, 34);
		pnCenter.add(tf_HinhAnh);

		btnChonHinhAnh = new JButton("Chọn");
		btnChonHinhAnh.setBorder(border);
		btnChonHinhAnh.setForeground(new Color(0, 0, 0));
		btnChonHinhAnh.setBackground(new Color(255, 255, 255));
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
		btnChonHinhAnh.setBounds(606, 166, 69, 34);
		pnCenter.add(btnChonHinhAnh);

		tf_Tensp = new JTextField();
		tf_Tensp.setForeground(new Color(0, 0, 0));
		tf_Tensp.setBackground(new Color(255, 255, 255));
		tf_Tensp.setColumns(10);
		tf_Tensp.setBorder(border);
		tf_Tensp.setBounds(22, 66, 209, 34);
		pnCenter.add(tf_Tensp);

		JLabel lb_soLuong = new JLabel("Số Lượng");
		lb_soLuong.setForeground(new Color(0, 0, 0));
		lb_soLuong.setBackground(new Color(255, 255, 255));
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
		lblCanhBaoGia.setBounds(392, 28, 29, 13);
		pnCenter.add(lblCanhBaoGia);

		JLabel lbCanhBaoSoLuong = new JLabel("*");
		lbCanhBaoSoLuong.setForeground(new Color(255, 0, 0));
		lbCanhBaoSoLuong.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoSoLuong.setBounds(91, 128, 226, 13);
		pnCenter.add(lbCanhBaoSoLuong);

		JLabel lblCui = new JLabel("* Có Đuôi .png hoặc .svg");
		lblCui.setForeground(new Color(255, 0, 0));
		lblCui.setFont(new Font("Serif", Font.ITALIC, 12));
		lblCui.setBounds(392, 128, 166, 13);
		pnCenter.add(lblCui);
		
		tf_giaNhap = new JTextField();
		tf_giaNhap.setForeground(Color.BLACK);
		tf_giaNhap.setColumns(10);
		tf_giaNhap.setBackground(Color.WHITE);
		tf_giaNhap.setBounds(322, 66, 158, 34);
		tf_giaNhap.setBorder(border);
		pnCenter.add(tf_giaNhap);
		
		JLabel lb_Gia_1 = new JLabel("Giá Bán");
		lb_Gia_1.setForeground(Color.BLACK);
		lb_Gia_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Gia_1.setBackground(Color.WHITE);
		lb_Gia_1.setBounds(517, 10, 86, 46);
		pnCenter.add(lb_Gia_1);
		
		JLabel lblCanhBaoGia_1 = new JLabel("*");
		lblCanhBaoGia_1.setForeground(Color.RED);
		lblCanhBaoGia_1.setFont(new Font("Serif", Font.ITALIC, 12));
		lblCanhBaoGia_1.setBounds(573, 29, 29, 13);
		pnCenter.add(lblCanhBaoGia_1);

		JLabel lb_ThemMoiSP = new JLabel("Thêm Mới Sản Phẩm");
		lb_ThemMoiSP.setForeground(new Color(0, 0, 0));
		lb_ThemMoiSP.setBackground(new Color(255, 255, 255));
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
		dcNgayHetHan.setForeground(new Color(0, 0, 0));
		dcNgayHetHan.setDateFormatString("dd-MM-yyyy");
		dcNgayHetHan.setBackground(new Color(255, 255, 255));
		dcNgayHetHan.setBounds(414, 101, 254, 34);
		dcNgayHetHan.setBackground(new Color(255, 255, 255));
		dcNgayHetHan.getDateEditor().getUiComponent().setBackground(new Color(255, 255, 255));
		dcNgayHetHan.setBorder(border);
		pn_Ngay.add(dcNgayHetHan);

		JLabel lbNgayHetHan = new JLabel("Ngày Hết Hạn");
		lbNgayHetHan.setForeground(new Color(0, 0, 0));
		lbNgayHetHan.setBackground(new Color(255, 255, 255));
		lbNgayHetHan.setBounds(414, 45, 105, 46);
		pn_Ngay.add(lbNgayHetHan);
		lbNgayHetHan.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		dcNgaySanXuat = new JDateChooser();
		dcNgaySanXuat.setBackground(new Color(255, 255, 255));
		dcNgaySanXuat.getDateEditor().getUiComponent().setBackground(new Color(255, 255, 255));
		dcNgaySanXuat.setForeground(new Color(0, 0, 0));
		dcNgaySanXuat.setDateFormatString("dd-MM-yyyy");
		dcNgaySanXuat.setBounds(24, 101, 287, 34);
		dcNgaySanXuat.setBorder(border);
		pn_Ngay.add(dcNgaySanXuat);

		JLabel lbNgaySanXuat = new JLabel("Ngày Sản Xuất");
		lbNgaySanXuat.setForeground(new Color(0, 0, 0));
		lbNgaySanXuat.setBackground(new Color(255, 255, 255));
		lbNgaySanXuat.setBounds(24, 45, 105, 46);
		pn_Ngay.add(lbNgaySanXuat);
		lbNgaySanXuat.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		JLabel lb_ngay = new JLabel("Thời Gian");
		lb_ngay.setForeground(new Color(0, 0, 0));
		lb_ngay.setBackground(new Color(255, 255, 255));
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
		lb_khohang.setForeground(new Color(0, 0, 0));
		lb_khohang.setBackground(new Color(255, 255, 255));
		lb_khohang.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_khohang.setBounds(24, 10, 98, 43);
		pn_KhoHang.add(lb_khohang);

		JLabel lb_KhoiLuong = new JLabel("Khối Lượng");
		lb_KhoiLuong.setForeground(new Color(0, 0, 0));
		lb_KhoiLuong.setBackground(new Color(255, 255, 255));
		lb_KhoiLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_KhoiLuong.setBounds(24, 48, 105, 46);
		pn_KhoHang.add(lb_KhoiLuong);

		tf_KhoiLuong = new JTextField();
		tf_KhoiLuong.setForeground(new Color(0, 0, 0));
		tf_KhoiLuong.setBackground(new Color(255, 255, 255));
		tf_KhoiLuong.setColumns(10);
		tf_KhoiLuong.setBounds(24, 104, 198, 34);
		tf_KhoiLuong.setBorder(border);
		pn_KhoHang.add(tf_KhoiLuong);

		JLabel lb_DonViTinh = new JLabel("Đơn Vị Tính");
		lb_DonViTinh.setForeground(new Color(0, 0, 0));
		lb_DonViTinh.setBackground(new Color(255, 255, 255));
		lb_DonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_DonViTinh.setBounds(279, 48, 105, 46);
		pn_KhoHang.add(lb_DonViTinh);
		

		cb_DonViTinh = new JComboBox();
		cb_DonViTinh.setForeground(new Color(0, 0, 0));
		cb_DonViTinh.setBounds(279, 103, 130, 34);
		cb_DonViTinh.addItem("Vỉ");
		cb_DonViTinh.addItem("Viên");
		cb_DonViTinh.addItem("Chai");
		cb_DonViTinh.addItem("Hộp");
		pn_KhoHang.add(cb_DonViTinh);
		cb_DonViTinh.setBorder(border);
		cb_DonViTinh.setBackground(new Color(255, 255, 255));
		cb_DonViTinh.getEditor().getEditorComponent().setBackground(new Color(255, 255, 255));

		JLabel lbCanhBaoKL = new JLabel("*");
		lbCanhBaoKL.setForeground(new Color(255, 0, 0));
		lbCanhBaoKL.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoKL.setBounds(112, 63, 198, 17);
		pn_KhoHang.add(lbCanhBaoKL);
		
		JLabel lb_DonViTinh_1 = new JLabel("Thuế");
		lb_DonViTinh_1.setForeground(Color.BLACK);
		lb_DonViTinh_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_DonViTinh_1.setBackground(Color.WHITE);
		lb_DonViTinh_1.setBounds(530, 48, 105, 46);
		pn_KhoHang.add(lb_DonViTinh_1);
		
		tf_thue = new JTextField();
		tf_thue.setForeground(Color.BLACK);
		tf_thue.setColumns(10);
		tf_thue.setBackground(Color.WHITE);
		tf_thue.setBounds(530, 104, 135, 34);
		pn_KhoHang.add(tf_thue);

		JPanel pn_PhanLoai = new JPanel();
		pn_PhanLoai.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pn_PhanLoai.setBackground(new Color(255, 255, 255));
		pn_PhanLoai.setBounds(789, 95, 320, 256);
		pnContent.add(pn_PhanLoai);
		pn_PhanLoai.setLayout(null);

		JLabel lb_PhanLoai = new JLabel("Phân Loại");
		lb_PhanLoai.setForeground(new Color(0, 0, 0));
		lb_PhanLoai.setBackground(new Color(255, 255, 255));
		lb_PhanLoai.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_PhanLoai.setBounds(20, 10, 105, 34);
		pn_PhanLoai.add(lb_PhanLoai);

		JLabel lb_Loai = new JLabel("Loại Sản Phẩm");
		lb_Loai.setForeground(new Color(0, 0, 0));
		lb_Loai.setBackground(new Color(255, 255, 255));
		lb_Loai.setBounds(20, 39, 105, 46);
		pn_PhanLoai.add(lb_Loai);
		lb_Loai.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		cb_LoaiSP = new JComboBox();
		cb_LoaiSP.setForeground(new Color(0, 0, 0));
		cb_LoaiSP.setBackground(new Color(255, 255, 255));
		cb_LoaiSP.setBounds(20, 90, 271, 34);
		pn_PhanLoai.add(cb_LoaiSP);
		cb_LoaiSP.addItem("Thuốc");
		cb_LoaiSP.addItem("Thực Phẩm Chức Năng");
		cb_LoaiSP.addItem("Thiết Bị Y Tế");
		cb_LoaiSP.setBorder(border);
		

		JLabel lb_Nhacc = new JLabel("Nhà Cung Cấp");
		lb_Nhacc.setForeground(new Color(0, 0, 0));
		lb_Nhacc.setBackground(new Color(255, 255, 255));
		lb_Nhacc.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Nhacc.setBounds(20, 147, 105, 46);
		pn_PhanLoai.add(lb_Nhacc);

		tf_NhaCungCap = new JTextField();
		tf_NhaCungCap.setForeground(new Color(0, 0, 0));
		tf_NhaCungCap.setBackground(new Color(255, 255, 255));
		tf_NhaCungCap.setColumns(10);
		tf_NhaCungCap.setBounds(20, 197, 271, 34);
		tf_NhaCungCap.setBorder(border);
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
		lbCongDung.setForeground(new Color(0, 0, 0));
		lbCongDung.setBackground(new Color(255, 255, 255));
		lbCongDung.setVerticalAlignment(SwingConstants.TOP);
		lbCongDung.setFont(new Font("Serif", Font.PLAIN, 20));
		lbCongDung.setBounds(10, 157, 105, 34);
		pn_HinhAnh.add(lbCongDung);

		ta_CongDung = new JTextArea();
		ta_CongDung.setForeground(new Color(0, 0, 0));
		ta_CongDung.setBackground(new Color(255, 255, 255));
		border = BorderFactory.createLineBorder(Color.BLACK, 1); // Độ dày 1 pixel
		ta_CongDung.setBorder(border);
		ta_CongDung.setBounds(10, 190, 300, 102);
		ta_CongDung.setBorder(border);
		pn_HinhAnh.add(ta_CongDung);

		ta_ThanhPhan = new JTextArea();
		ta_ThanhPhan.setForeground(new Color(0, 0, 0));
		ta_ThanhPhan.setBackground(new Color(255, 255, 255));
		ta_ThanhPhan.setBorder(border);
		ta_ThanhPhan.setBounds(10, 58, 300, 102);
		ta_ThanhPhan.setBorder(border);
		pn_HinhAnh.add(ta_ThanhPhan);

		JLabel lbThanhPhan = new JLabel("Thành Phần");
		lbThanhPhan.setForeground(new Color(0, 0, 0));
		lbThanhPhan.setBackground(new Color(255, 255, 255));
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
	public static String generateMovieCode(String key) {
	    Calendar now = Calendar.getInstance();
	    int currentYear = now.get(Calendar.YEAR) % 100; // Lấy 2 chữ số cuối của năm
	    int currentMonth = now.get(Calendar.MONTH) + 1;
	    int currentDay = now.get(Calendar.DAY_OF_MONTH);
	    
	    Random random = new Random();
	    int randomDigits = 10000 + random.nextInt(90000); // Tạo 5 chữ số ngẫu nhiên

	    // Tạo mã sản phẩm với định dạng yêu cầu
	    String ma = key.toUpperCase() + String.format("%02d%02d%02d", currentYear, currentMonth, currentDay) + randomDigits;
	    return ma;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXacNhan)) {
			try {
				// Kiểm tra thông tin đầu vào
				String maSP = generateMovieCode("SP");
				String tenSP = tf_Tensp.getText();
				if (tenSP.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Lấy ngày sản xuất và ngày hết hạn từ JDateChooser
				java.util.Date ngaySX = dcNgaySanXuat.getDate();
				LocalDate lcNgaySX = (ngaySX != null) ? ngaySX.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						: null;

				java.util.Date ngayHH = dcNgayHetHan.getDate();
				LocalDate lcNgayHH = (ngayHH != null) ? ngayHH.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						: null;

				LocalDate today = LocalDate.now(); // Lấy ngày hiện tại

				// Kiểm tra điều kiện ngày sản xuất và ngày hết hạn
				if (lcNgaySX == null || lcNgaySX.isAfter(today)) {
					JOptionPane.showMessageDialog(null, "Ngày sản xuất không được sau ngày hiện tại.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (lcNgayHH == null || !lcNgayHH.isAfter(today)) {
					JOptionPane.showMessageDialog(null, "Ngày hết hạn phải lớn hơn ngày hiện tại.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Lấy các giá trị còn lại
				double khoiLuong = Double.parseDouble(tf_KhoiLuong.getText());
				if (khoiLuong <= 0) {
					JOptionPane.showMessageDialog(null, "Khối lượng phải > 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String donViTinh = (String) cb_DonViTinh.getSelectedItem();
				String nhaCungCap = tf_NhaCungCap.getText();
				if (nhaCungCap.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nhà cung cấp không được để trống.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				double gia = Double.parseDouble(tf_Gia.getText());
				if (gia <= 0) {
					JOptionPane.showMessageDialog(null, "Giá lượng phải > 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String congDung = ta_CongDung.getText();
				if (congDung.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Công dụng không được để trống.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String hinhAnh = tf_HinhAnh.getText().trim(); // Loại bỏ khoảng trắng ở đầu và cuối chuỗi

				// Kiểm tra nếu chuỗi rỗng hoặc không có đuôi .png hoặc .svg
				if (hinhAnh.isEmpty() || !(hinhAnh.endsWith(".png") || hinhAnh.endsWith(".svg"))) {
					JOptionPane.showMessageDialog(null,
							"Hình ảnh phải có định dạng .png hoặc .svg và không được để trống.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String loaiSanPham = (String) cb_LoaiSP.getSelectedItem();
				int soLuong = Integer.parseInt(tf_soLuong.getText());
				if (soLuong <= 0) {
					JOptionPane.showMessageDialog(null, "Số lượng phải > 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Chuyển đổi loại sản phẩm
				if (loaiSanPham.equals("Thuốc")) {
					loaiSanPham = "Thuoc";
				} else if (loaiSanPham.equals("Thực Phẩm Chức Năng")) {
					loaiSanPham = "TPCN";
				} else if (loaiSanPham.equals("Thiết Bị Y Tế")) {
					loaiSanPham = "TBYT";
				}
				String thanhPhan = ta_ThanhPhan.getText();
				if (thanhPhan.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Thành phần không được để trống.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				double thue = Double.parseDouble(tf_thue.getText());
				// Tạo đối tượng SanPham
				LoaiSanPham_entity loaiSP = new LoaiSanPham_entity(loaiSanPham);
				double giaNhap = Double.parseDouble(tf_giaNhap.getText());
				SanPham_entity sp = new SanPham_entity(maSP, tenSP, lcNgaySX, lcNgayHH, khoiLuong, donViTinh,
						nhaCungCap, gia, thanhPhan, congDung, hinhAnh, loaiSP, soLuong,thue,giaNhap);

				// Thêm sản phẩm vào bảng (cần đối tượng `sanPham` để gọi phương thức
				// `addRowTable`)
				sanPham.addRowTable(sp);

				// Hiển thị thông báo thành công
				JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				// Hiển thị thông báo lỗi nếu có bất kỳ ngoại lệ nào xảy ra
				JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
