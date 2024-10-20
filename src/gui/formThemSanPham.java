package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import entity.LoaiSanPham;
import nguyenvu.components.SimpleForm;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class formThemSanPham extends SimpleForm implements ActionListener{

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

	/**
	 * Create the panel.
	 */
	public formThemSanPham(SanPham sanPham) {
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
	            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));

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
		lb_soLuong.setBounds(22, 120, 105, 46);
		pnCenter.add(lb_soLuong);
		
		JLabel lb_ThemMoiSP = new JLabel("Thêm Mới Sản Phẩm");
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
		
		JPanel pn_HinhAnh = new JPanel();
		pn_HinhAnh.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pn_HinhAnh.setBackground(new Color(255, 255, 255));
		pn_HinhAnh.setBounds(789, 389, 320, 286);
		pnContent.add(pn_HinhAnh);
		pn_HinhAnh.setLayout(null);
		
		JLabel lbCongDung = new JLabel("Công Dụng");
		lbCongDung.setVerticalAlignment(SwingConstants.TOP);
		lbCongDung.setFont(new Font("Serif", Font.PLAIN, 20));
		lbCongDung.setBounds(23, 24, 105, 34);
		pn_HinhAnh.add(lbCongDung);
		
		ta_CongDung = new JTextArea();
		border = BorderFactory.createLineBorder(Color.BLACK, 1); // Độ dày 1 pixel
		ta_CongDung.setBorder(border);
		ta_CongDung.setBounds(10, 68, 300, 208);
		pn_HinhAnh.add(ta_CongDung);
		
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
	        try {
	            // Kiểm tra thông tin đầu vào
	            String maSP = "sp1";  // Ví dụ mã sản phẩm tạm thời
	            String tenSP = tf_Tensp.getText();
	            if (maSP.isEmpty() || tenSP.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Mã sản phẩm hoặc tên sản phẩm không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Lấy ngày sản xuất và ngày hết hạn từ JDateChooser
	            java.util.Date ngaySX = dcNgaySanXuat.getDate();
	            LocalDate lcNgaySX = (ngaySX != null) ? ngaySX.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

	            java.util.Date ngayHH = dcNgayHetHan.getDate();
	            LocalDate lcNgayHH = (ngayHH != null) ? ngayHH.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

	            // Lấy các giá trị còn lại
	            double khoiLuong = Double.parseDouble(tf_KhoiLuong.getText());
	            String donViTinh = (String) cb_DonViTinh.getSelectedItem();
	            String nhaCungCap = tf_NhaCungCap.getText();
	            double gia = Double.parseDouble(tf_Gia.getText());
	            String congDung = ta_CongDung.getText();
	            String hinhAnh = tf_HinhAnh.getText();
	            String loaiSanPham = (String) cb_LoaiSP.getSelectedItem();
	            int soLuong = Integer.parseInt(tf_soLuong.getText());

	            // Chuyển đổi loại sản phẩm
	            if (loaiSanPham.equals("Thuốc")) {
	                loaiSanPham = "Thuoc";
	            } else if (loaiSanPham.equals("Thực Phẩm Chức Năng")) {
	                loaiSanPham = "TPCN";
	            } else if (loaiSanPham.equals("Thiết Bị Y Tế")) {
	                loaiSanPham = "TBYT";
	            }

	            // Tạo đối tượng SanPham
	            LoaiSanPham loaiSP = new LoaiSanPham(loaiSanPham);
	            entity.SanPham sp = new entity.SanPham(maSP, tenSP, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, nhaCungCap, gia, congDung, hinhAnh, loaiSP, soLuong);

	            // Thêm sản phẩm vào bảng (cần đối tượng `sanPham` để gọi phương thức `addRowTable`)
	            sanPham.addRowTable(maSP, tenSP, soLuong, lcNgaySX, lcNgayHH, nhaCungCap, gia, congDung, hinhAnh, loaiSanPham);

	            // Hiển thị thông báo thành công
	            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        } catch (Exception ex) {
	            // Hiển thị thông báo lỗi nếu có bất kỳ ngoại lệ nào xảy ra
	            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
}
