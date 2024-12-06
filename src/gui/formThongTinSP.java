package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

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

import dao.SanPham_DAO;
import entity.LoaiSanPham_entity;
import entity.SanPham_entity;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.RoundedPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class formThongTinSP extends SimpleForm implements ActionListener {

	private static final long serialVersionUID = 1L;
	public JTextField tf_soLuong;
	public JTextField tf_Gia;
	public JTextField tf_HinhAnh;
	public JTextField tf_Tensp;
	public JTextField tf_KhoiLuong;
	public JTextField tf_NhaCungCap;
	private Border border;
	public JTextField cb_DonViTinh;
	public JDateChooser dcNgaySanXuat;
	public JDateChooser dcNgayHetHan;
	public JTextArea ta_CongDung;
	public JTextField cb_LoaiSP;
	private SanPham sanPham;
	public JTextArea ta_ThanhPhan;
	public JTextField tf_thue;
	private String maSP;
	public SanPham_entity spCapNhat;
	private JTextField tf_giaNhap;
	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	/**
	 * Create the panel.
	 */
	public formThongTinSP() {
		sanPham = new SanPham();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(1150, 800));
		setLayout(new BorderLayout(0, 0));

		JPanel pnContent = new JPanel();
		pnContent.setPreferredSize(new Dimension(1150, 800));
		Color color_pnContent = Color.decode("#f4f6f8");
		pnContent.setBackground(color_pnContent);
		add(pnContent, BorderLayout.CENTER);
		pnContent.setLayout(null);

		
		RoundedPanel pnCenter = new RoundedPanel(20);
		pnCenter.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnCenter.setBounds(50, 95, 716, 235);
		pnCenter.setBackground(new Color(255, 255, 255));
		pnContent.add(pnCenter);
		pnCenter.setLayout(null);

		JLabel lbTensp = new JLabel("Tên Sản Phẩm");
		lbTensp.setForeground(new Color(0, 0, 0));
		lbTensp.setBackground(new Color(0, 0, 0));
		lbTensp.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lbTensp.setBounds(20, 10, 105, 46);
		pnCenter.add(lbTensp);

		tf_soLuong = new JTextField();
		tf_soLuong.setEditable(false);
		tf_soLuong.setBackground(new Color(255, 255, 255));
		tf_soLuong.setForeground(new Color(0, 0, 0));
		tf_soLuong.setColumns(10);
		tf_soLuong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_soLuong.setBounds(20, 166, 261, 34);
		pnCenter.add(tf_soLuong);

		JLabel lb_Gia = new JLabel("Giá Bán");
		lb_Gia.setForeground(new Color(0, 0, 0));
		lb_Gia.setBackground(new Color(0, 0, 0));
		lb_Gia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Gia.setBounds(524, 10, 63, 46);
		pnCenter.add(lb_Gia);

		tf_Gia = new JTextField();
		tf_Gia.setEditable(false);
		tf_Gia.setForeground(new Color(0, 0, 0));
		tf_Gia.setBackground(new Color(255, 255, 255));
		tf_Gia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_Gia.setColumns(10);
		tf_Gia.setBounds(524, 66, 132, 34);
		pnCenter.add(tf_Gia);

		JLabel lb_HinhAnh = new JLabel("Hình Ảnh");
		lb_HinhAnh.setForeground(new Color(0, 0, 0));
		lb_HinhAnh.setBackground(new Color(0, 0, 0));
		lb_HinhAnh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_HinhAnh.setBounds(371, 110, 105, 46);
		pnCenter.add(lb_HinhAnh);

		tf_HinhAnh = new JTextField();
		tf_HinhAnh.setEditable(false);
		tf_HinhAnh.setBackground(new Color(255, 255, 255));
		tf_HinhAnh.setForeground(new Color(0, 0, 0));
		tf_HinhAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_HinhAnh.setColumns(10);
		tf_HinhAnh.setBounds(371, 166, 285, 34);
		pnCenter.add(tf_HinhAnh);

		tf_Tensp = new JTextField();
		tf_Tensp.setEditable(false);
		tf_Tensp.setForeground(new Color(0, 0, 0));
		tf_Tensp.setBackground(new Color(255, 255, 255));
		tf_Tensp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_Tensp.setColumns(10);
		tf_Tensp.setBounds(22, 66, 259, 34);
		pnCenter.add(tf_Tensp);

		JLabel lb_soLuong = new JLabel("Số Lượng");
		lb_soLuong.setForeground(new Color(0, 0, 0));
		lb_soLuong.setBackground(new Color(0, 0, 0));
		lb_soLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_soLuong.setBounds(20, 110, 105, 46);
		pnCenter.add(lb_soLuong);
		
		JLabel lb_Gia_1 = new JLabel("Giá Nhập");
		lb_Gia_1.setForeground(Color.BLACK);
		lb_Gia_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Gia_1.setBackground(Color.BLACK);
		lb_Gia_1.setBounds(371, 10, 63, 46);
		pnCenter.add(lb_Gia_1);
		
		tf_giaNhap = new JTextField();
		tf_giaNhap.setForeground(Color.BLACK);
		tf_giaNhap.setEditable(false);
		tf_giaNhap.setColumns(10);
		tf_giaNhap.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_giaNhap.setBackground(Color.WHITE);
		tf_giaNhap.setBounds(371, 66, 132, 34);
		pnCenter.add(tf_giaNhap);

		JLabel lb_ThemMoiSP = new JLabel("Thông Tin Sản Phẩm");
		lb_ThemMoiSP.setForeground(new Color(0, 0, 0));
		lb_ThemMoiSP.setBackground(new Color(0, 0, 0));
		lb_ThemMoiSP.setFont(new Font("Serif", Font.PLAIN, 24));
		lb_ThemMoiSP.setBounds(50, 23, 269, 54);
		pnContent.add(lb_ThemMoiSP);

		JPanel pn_Ngay = new JPanel();
		pn_Ngay.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pn_Ngay.setBackground(new Color(255, 255, 255));
		pn_Ngay.setBounds(50, 350, 716, 149);
		pnContent.add(pn_Ngay);
		pn_Ngay.setLayout(null);

		dcNgayHetHan = new JDateChooser();
		dcNgayHetHan.setDateFormatString("dd-MM-yyyy");
		dcNgayHetHan.setBackground(new Color(255, 255, 255));
		dcNgayHetHan.setForeground(new Color(0, 0, 0));
		dcNgayHetHan.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		dcNgayHetHan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dcNgayHetHan.setBounds(414, 101, 254, 34);
		// Đặt màu nền và màu chữ cho trường văn bản
        JTextComponent dateTextField1 = (JTextComponent) dcNgayHetHan.getDateEditor().getUiComponent();
        dateTextField1.setBackground(Color.WHITE);
        dateTextField1.setForeground(Color.BLACK);
        dateTextField1.setDisabledTextColor(Color.BLACK);

        // Vô hiệu hóa thành phần chọn ngày nhưng giữ nền sáng
        dcNgayHetHan.getDateEditor().getUiComponent().setEnabled(false);
        dcNgayHetHan.getCalendarButton().setEnabled(false); // Vô hiệu hóa nút chọn lịch
        dcNgayHetHan.setEnabled(false); // Vô hiệu hóa cả thành phần JDateChooser

        // Đảm bảo màu nền không bị tối
        ((JTextComponent) dcNgayHetHan.getDateEditor().getUiComponent()).setDisabledTextColor(Color.BLACK);
		
		pn_Ngay.add(dcNgayHetHan);

		JLabel lbNgayHetHan = new JLabel("Ngày Hết Hạn");
		lbNgayHetHan.setForeground(new Color(0, 0, 0));
		lbNgayHetHan.setBackground(new Color(0, 0, 0));
		lbNgayHetHan.setBounds(414, 45, 105, 46);
		pn_Ngay.add(lbNgayHetHan);
		lbNgayHetHan.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		dcNgaySanXuat = new JDateChooser();
		dcNgaySanXuat.setDateFormatString("dd-MM-yyyy");
		dcNgaySanXuat.setBackground(new Color(255, 255, 255));
		dcNgaySanXuat.setForeground(new Color(0, 0, 0));
		dcNgaySanXuat.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		dcNgaySanXuat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dcNgaySanXuat.setBounds(24, 101, 287, 34);
		 // Đặt màu nền và màu chữ cho trường văn bản
        JTextComponent dateTextField = (JTextComponent) dcNgaySanXuat.getDateEditor().getUiComponent();
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setForeground(Color.BLACK);
        dateTextField.setDisabledTextColor(Color.BLACK);

        // Vô hiệu hóa thành phần chọn ngày nhưng giữ nền sáng
        dcNgaySanXuat.getDateEditor().getUiComponent().setEnabled(false);
        dcNgaySanXuat.getCalendarButton().setEnabled(false); // Vô hiệu hóa nút chọn lịch
        dcNgaySanXuat.setEnabled(false); // Vô hiệu hóa cả thành phần JDateChooser

        // Đảm bảo màu nền không bị tối
        ((JTextComponent) dcNgaySanXuat.getDateEditor().getUiComponent()).setDisabledTextColor(Color.BLACK);
		pn_Ngay.add(dcNgaySanXuat);

		JLabel lbNgaySanXuat = new JLabel("Ngày Sản Xuất");
		lbNgaySanXuat.setForeground(new Color(0, 0, 0));
		lbNgaySanXuat.setBackground(new Color(0, 0, 0));
		lbNgaySanXuat.setBounds(24, 45, 105, 46);
		pn_Ngay.add(lbNgaySanXuat);
		lbNgaySanXuat.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		JLabel lb_ngay = new JLabel("Thời Gian");
		lb_ngay.setForeground(new Color(0, 0, 0));
		lb_ngay.setBackground(new Color(0, 0, 0));
		lb_ngay.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_ngay.setBounds(24, 10, 105, 34);
		pn_Ngay.add(lb_ngay);

		JLabel lbCanhBaoNHH = new JLabel("*");
		lbCanhBaoNHH.setForeground(new Color(255, 0, 0));
		lbCanhBaoNHH.setFont(new Font("Serif", Font.ITALIC, 12));
		lbCanhBaoNHH.setBounds(522, 61, 184, 17);
		pn_Ngay.add(lbCanhBaoNHH);

		JPanel pn_KhoHang = new JPanel();
		pn_KhoHang.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pn_KhoHang.setBackground(new Color(255, 255, 255));
		pn_KhoHang.setBounds(50, 519, 716, 156);
		pnContent.add(pn_KhoHang);
		pn_KhoHang.setLayout(null);

		JLabel lb_khohang = new JLabel("Kho Hàng");
		lb_khohang.setForeground(new Color(0, 0, 0));
		lb_khohang.setBackground(new Color(0, 0, 0));
		lb_khohang.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_khohang.setBounds(24, 10, 98, 43);
		pn_KhoHang.add(lb_khohang);

		JLabel lb_KhoiLuong = new JLabel("Khối Lượng");
		lb_KhoiLuong.setForeground(new Color(0, 0, 0));
		lb_KhoiLuong.setBackground(new Color(0, 0, 0));
		lb_KhoiLuong.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_KhoiLuong.setBounds(24, 48, 105, 46);
		pn_KhoHang.add(lb_KhoiLuong);

		tf_KhoiLuong = new JTextField();
		tf_KhoiLuong.setEditable(false);
		tf_KhoiLuong.setForeground(new Color(0, 0, 0));
		tf_KhoiLuong.setBackground(new Color(255, 255, 255));
		tf_KhoiLuong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_KhoiLuong.setColumns(10);
		tf_KhoiLuong.setBounds(24, 104, 144, 34);
		pn_KhoHang.add(tf_KhoiLuong);

		JLabel lb_DonViTinh = new JLabel("Đơn Vị Tính");
		lb_DonViTinh.setForeground(new Color(0, 0, 0));
		lb_DonViTinh.setBackground(new Color(0, 0, 0));
		lb_DonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_DonViTinh.setBounds(287, 48, 105, 46);
		pn_KhoHang.add(lb_DonViTinh);

		cb_DonViTinh = new JTextField();
		cb_DonViTinh.setEditable(false);
		cb_DonViTinh.setFont(new Font("Arial", Font.PLAIN, 15));
		cb_DonViTinh.setForeground(new Color(0, 0, 0));
		cb_DonViTinh.setBackground(new Color(255, 255, 255));
		cb_DonViTinh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cb_DonViTinh.setBounds(287, 102, 127, 34);
		pn_KhoHang.add(cb_DonViTinh);
		
		JLabel lb_DonViTinh_1 = new JLabel("Thuế");
		lb_DonViTinh_1.setForeground(Color.BLACK);
		lb_DonViTinh_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_DonViTinh_1.setBackground(Color.BLACK);
		lb_DonViTinh_1.setBounds(530, 48, 105, 46);
		pn_KhoHang.add(lb_DonViTinh_1);
		
		

		JPanel pn_PhanLoai = new JPanel();
		pn_PhanLoai.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pn_PhanLoai.setBackground(new Color(255, 255, 255));
		pn_PhanLoai.setBounds(789, 95, 320, 256);
		pnContent.add(pn_PhanLoai);
		pn_PhanLoai.setLayout(null);

		JLabel lb_PhanLoai = new JLabel("Phân Loại");
		lb_PhanLoai.setForeground(new Color(0, 0, 0));
		lb_PhanLoai.setBackground(new Color(0, 0, 0));
		lb_PhanLoai.setFont(new Font("Serif", Font.PLAIN, 20));
		lb_PhanLoai.setBounds(20, 10, 105, 34);
		pn_PhanLoai.add(lb_PhanLoai);

		JLabel lb_Loai = new JLabel("Loại Sản Phẩm");
		lb_Loai.setForeground(new Color(0, 0, 0));
		lb_Loai.setBackground(new Color(0, 0, 0));
		lb_Loai.setBounds(20, 39, 105, 46);
		pn_PhanLoai.add(lb_Loai);
		lb_Loai.setFont(new Font("Times New Roman", Font.PLAIN, 17));

		cb_LoaiSP = new JTextField();
		cb_LoaiSP.setEditable(false);
		cb_LoaiSP.setFont(new Font("Arial", Font.PLAIN, 14));
		cb_LoaiSP.setForeground(new Color(0, 0, 0));
		cb_LoaiSP.setBackground(new Color(255, 255, 255));
		cb_LoaiSP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cb_LoaiSP.setBounds(20, 90, 271, 34);
		pn_PhanLoai.add(cb_LoaiSP);
		
		tf_thue = new JTextField();
		tf_thue.setEditable(false);
		tf_thue.setForeground(Color.BLACK);
		tf_thue.setColumns(10);
		tf_thue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_thue.setBackground(Color.WHITE);
		tf_thue.setBounds(529, 104, 144, 34);
		pn_KhoHang.add(tf_thue);

		JLabel lb_Nhacc = new JLabel("Nhà Cung Cấp");
		lb_Nhacc.setForeground(new Color(0, 0, 0));
		lb_Nhacc.setBackground(new Color(0, 0, 0));
		lb_Nhacc.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lb_Nhacc.setBounds(20, 147, 105, 46);
		pn_PhanLoai.add(lb_Nhacc);

		tf_NhaCungCap = new JTextField();
		tf_NhaCungCap.setEditable(false);
		tf_NhaCungCap.setForeground(new Color(0, 0, 0));
		tf_NhaCungCap.setBackground(new Color(255, 255, 255));
		tf_NhaCungCap.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf_NhaCungCap.setColumns(10);
		tf_NhaCungCap.setBounds(20, 197, 271, 34);
		pn_PhanLoai.add(tf_NhaCungCap);

		JPanel pn_HinhAnh = new JPanel();
		pn_HinhAnh.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pn_HinhAnh.setBackground(new Color(255, 255, 255));
		pn_HinhAnh.setBounds(789, 373, 320, 302);
		pnContent.add(pn_HinhAnh);
		pn_HinhAnh.setLayout(null);

		JLabel lbCongDung = new JLabel("Công Dụng");
		lbCongDung.setForeground(new Color(0, 0, 0));
		lbCongDung.setBackground(new Color(0, 0, 0));
		lbCongDung.setVerticalAlignment(SwingConstants.TOP);
		lbCongDung.setFont(new Font("Serif", Font.PLAIN, 20));
		lbCongDung.setBounds(10, 157, 105, 34);
		pn_HinhAnh.add(lbCongDung);

		ta_CongDung = new JTextArea();
		ta_CongDung.setEditable(false);
		ta_CongDung.setForeground(new Color(0, 0, 0));
		ta_CongDung.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ta_CongDung.setBackground(new Color(255, 255, 255));
		border = BorderFactory.createLineBorder(Color.BLACK, 1); // Độ dày 1 pixel
		ta_CongDung.setBorder(border);
		ta_CongDung.setBounds(10, 190, 300, 102);
		pn_HinhAnh.add(ta_CongDung);

		ta_ThanhPhan = new JTextArea();
		ta_ThanhPhan.setEditable(false);
		ta_ThanhPhan.setForeground(new Color(0, 0, 0));
		ta_ThanhPhan.setBackground(new Color(255, 255, 255));
		ta_ThanhPhan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ta_ThanhPhan.setBorder(border);
		ta_ThanhPhan.setBounds(10, 58, 300, 102);
		pn_HinhAnh.add(ta_ThanhPhan);

		JLabel lbThanhPhan = new JLabel("Thành Phần");
		lbThanhPhan.setForeground(new Color(0, 0, 0));
		lbThanhPhan.setBackground(new Color(0, 0, 0));
		lbThanhPhan.setVerticalAlignment(SwingConstants.TOP);
		lbThanhPhan.setFont(new Font("Serif", Font.PLAIN, 20));
		lbThanhPhan.setBounds(10, 14, 105, 34);
		pn_HinhAnh.add(lbThanhPhan);
		
		
//		cb_LoaiSP.addItemListener(new ItemListener() {
//		    @Override
//		    public void itemStateChanged(ItemEvent e) {
//		        // Kiểm tra nếu sự kiện là sự thay đổi (ItemEvent.SELECTED)
//		        if (e.getStateChange() == ItemEvent.SELECTED) {
//		            String selectedLoaiSP = (String) cb_LoaiSP.getSelectedItem();
//		            
//		            // Cập nhật giá trị thuế tùy thuộc vào lựa chọn
//		            if ("Thuốc".equals(selectedLoaiSP) || "Thiết Bị Y Tế".equals(selectedLoaiSP)) {
//		                tf_thue.setText("5.0");
//		            } else if ("Thực Phẩm Chức Năng".equals(selectedLoaiSP)) {
//		                tf_thue.setText("10.0");
//		            }
//		        }
//		    }
//		});
	}

	public void CN(SanPham_entity sp) {
		maSP = sp.getMaSP();
	    tf_Tensp.setText(sp.getTenSP());
	    tf_soLuong.setText(String.valueOf(sp.getSoLuong()));
	    dcNgaySanXuat.setDate(java.sql.Date.valueOf(sp.getNgaySanXuat()));
	    dcNgayHetHan.setDate(java.sql.Date.valueOf(sp.getNgayHetHan()));
	    tf_KhoiLuong.setText(String.valueOf(sp.getKhoiLuong()));
	    cb_DonViTinh.setText(sp.getDonViTinh());
	    
	    tf_NhaCungCap.setText(sp.getNhaCungCap());
	    tf_Gia.setText(String.valueOf(sp.getGia()));
	    ta_ThanhPhan.setText(sp.getThanhPhan());
	    ta_CongDung.setText(sp.getCongDung());
	    tf_HinhAnh.setText(sp.getHinhAnhSP());
	    tf_giaNhap.setText(String.valueOf(sp.getGiaNhap()));
	    String mlsp = sp.getLoaiSanPham().getMaLoaiSP();
	    if(mlsp.equals("Thuoc")) {
	    	cb_LoaiSP.setText("Thuốc");
	    }
	    if(mlsp.equals("TPCN")) {
	    	cb_LoaiSP.setText("Thực Phẩm Chức Năng");
	    }
	    if(mlsp.equals("TBYT")) {
	    	cb_LoaiSP.setText("Thiết Bị Y Tế");
	    }
	    
	    
	    tf_thue.setText(sp.getThue()+"");
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


	public JTextField getCb_DonViTinh() {
		return cb_DonViTinh;
	}

	public void setCb_DonViTinh(JTextField cb_DonViTinh) {
		this.cb_DonViTinh = cb_DonViTinh;
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

	public JTextField getCb_LoaiSP() {
		return cb_LoaiSP;
	}

	public void setCb_LoaiSP(JTextField cb_LoaiSP) {
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

	public JTextField getTf_thue() {
		return tf_thue;
	}

	public void setTf_thue(JTextField tf_thue) {
		this.tf_thue = tf_thue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
