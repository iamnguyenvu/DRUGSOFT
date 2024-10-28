package gui;

import javax.swing.JPanel;

import nguyenvu.components.SimpleForm;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import dao.HoaDon_DAO;
import entity.HoaDon_entity;
import entity.SanPham_entity;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class HoaDon extends SimpleForm implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField tf_TimKiem;
	private JTable tb_HoaDon;
	private DefaultTableModel df_HoaDon;
	private JButton btnTimKiem;
	private JScrollPane sp_HoaDon;
	private JComboBox cb_HTTT;
	private JComboBox cb_TrangThai;
	private JRadioButton rdo_TTTD;
	private JRadioButton rdo_TTGD;
	private HoaDon_DAO hd_Dao;

	/**
	 * Create the panel.
	 */
	public HoaDon() {
		hd_Dao = new HoaDon_DAO();
		setPreferredSize(new Dimension(1500, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnContent = new JPanel();
		add(pnContent, BorderLayout.CENTER);
		pnContent.setLayout(null);
		
		JPanel pnHeader = new JPanel();
		pnHeader.setBounds(0, 0, 1500, 70);
		pnHeader.setBackground(new Color(255, 255, 255));
		pnHeader.setPreferredSize(new Dimension(1500, 70));
		pnContent.add(pnHeader);
		pnHeader.setLayout(null);
		
		JLabel lbmainTiTle = new JLabel("Hóa Đơn");
		lbmainTiTle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbmainTiTle.setBounds(10, 10, 160, 50);
		pnHeader.add(lbmainTiTle);
		
		tf_TimKiem = new JTextField();
		tf_TimKiem.setBounds(236, 10, 558, 37);
		pnHeader.add(tf_TimKiem);
		tf_TimKiem.setColumns(10);
		
		// Tạo đường viền chỉ có phía dưới
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        tf_TimKiem.setBorder(bottomBorder);
        
        // Placeholder behavior khi JTextField mất hoặc có focus
        tf_TimKiem.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf_TimKiem.getText().equals("Nhập Mã Hóa Đơn")) {
                	tf_TimKiem.setText("");
                	tf_TimKiem.setForeground(Color.GRAY); // Đặt lại màu văn bản xám cho placeholder
                } else {
                	tf_TimKiem.setForeground(Color.BLACK); // Đặt màu văn bản bình thường nếu có nội dung
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf_TimKiem.getText().equals("")) {
                	tf_TimKiem.setForeground(Color.GRAY);
                	tf_TimKiem.setText("Nhập Mã Hóa Đơn"); // Nếu không có nội dung thì để trống
                }
            }
        });

        // Khởi tạo với placeholder
        tf_TimKiem.setText("Nhập Mã Hóa Đơn"); // Hiện placeholder
        tf_TimKiem.setForeground(Color.GRAY); // Đặt màu xám cho văn bản placeholder
		
		btnTimKiem = new JButton(new FlatSVGIcon("gui/icon/search_123.svg",0.6f));
		btnTimKiem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchText = tf_TimKiem.getText().trim();
		        if(searchText.equals("") || searchText.equals("Nhập Mã Hóa Đơn")) {
		        	docDuLieuVaoTable();
		        }
		        else {
		        	df_HoaDon.setRowCount(0); // Clear the current table model
		        List<HoaDon_entity> filter = hd_Dao.timKiem(searchText); // Assume this method exists
		        for (HoaDon_entity hd : filter) {
		            df_HoaDon.addRow(new Object[]{
		                hd.getMaHD(),hd.getNgayLapHD(),hd.getTongTien(),hd.getTienGiam(),hd.getHinhThucThanhToan(),hd.isTrangThai() ? "Hoàn Thành" : "Chưa Hoàn Thành",hd.getKhachHang().getSdtKH(),hd.getNhanVien().getMaNV(),hd.getLoaiHoaDon().getMaLoaiHD()
		                // Add actions for Update and Delete as necessary
		            });
		        }
		        }
		        
		    }
		});
		btnTimKiem.setBounds(804, 10, 41, 38);
		pnHeader.add(btnTimKiem);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		pnCenter.setBounds(0, 73, 1500, 727);
		pnContent.add(pnCenter);
		pnCenter.setLayout(null);
		
		String []collName = {"Mã Hóa Đơn","Ngày Lập","Tổng Tiền","Tiền Giảm","Hình Thức Thanh Toán","Trạng Thái","Mã Khách Hàng","Mã Nhân Viên","Mã Loại HD"};
				
		df_HoaDon = new DefaultTableModel(collName, 0);
		tb_HoaDon = new JTable(df_HoaDon);
		tb_HoaDon.setBounds(33, 29, 1186, 669);
		
		sp_HoaDon = new JScrollPane(tb_HoaDon);
		sp_HoaDon.setBounds(23, 22, 1094, 678);
		pnCenter.add(sp_HoaDon);
		
		docDuLieuVaoTable();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "L\u1ECDc H\u00F3a \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(1140, 24, 333, 453);
		pnCenter.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hình Thức Thanh Toán");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 46, 146, 49);
		panel.add(lblNewLabel);
		
		cb_HTTT = new JComboBox();
		cb_HTTT.setBounds(166, 46, 146, 37);
		panel.add(cb_HTTT);
		cb_HTTT.addItem("Tất Cả");
		cb_HTTT.addItem("TienMat");
		cb_HTTT.addItem("ChuyenKhoan");
		cb_HTTT.addItem("TheTinDung");
		
		
		JLabel lblTrngThi = new JLabel("Trạng Thái");
		lblTrngThi.setFont(new Font("Serif", Font.PLAIN, 14));
		lblTrngThi.setBounds(10, 152, 146, 49);
		panel.add(lblTrngThi);
		
		cb_TrangThai = new JComboBox();
		cb_TrangThai.setBounds(166, 152, 146, 37);
		panel.add(cb_TrangThai);
		cb_TrangThai.addItem("Hoàn Thành");
		cb_TrangThai.addItem("Chưa Hoàn Thành");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "T\u1ED5ng Ti\u1EC1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(22, 252, 290, 175);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		rdo_TTTD = new JRadioButton("Tăng Dần");
		rdo_TTTD.setBackground(new Color(255, 255, 255));
		rdo_TTTD.setFont(new Font("Serif", Font.PLAIN, 12));
		rdo_TTTD.setBounds(25, 47, 219, 21);
		panel_1.add(rdo_TTTD);
		
		rdo_TTGD = new JRadioButton("Giảm Dần");
		rdo_TTGD.setFont(new Font("Serif", Font.PLAIN, 12));
		rdo_TTGD.setBackground(Color.WHITE);
		rdo_TTGD.setBounds(25, 95, 219, 21);
		panel_1.add(rdo_TTGD);
		
		ButtonGroup btngp = new ButtonGroup();
		btngp.add(rdo_TTTD);
		btngp.add(rdo_TTGD);
		rdo_TTTD.addActionListener(e -> locHoaDon());
		rdo_TTGD.addActionListener(e -> locHoaDon());
		
		cb_HTTT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				locHoaDon();
				
			}
		});
		cb_TrangThai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				locHoaDon();
				
			}
		});

	}
	 
	//Phương thức thêm tất cả dữ liệu vào bảng
	private void docDuLieuVaoTable() {
		List<HoaDon_entity> dshd = hd_Dao.getALLHoaDon();
		for (HoaDon_entity hd : dshd) {
			df_HoaDon.addRow(new Object[] {hd.getMaHD(),hd.getNgayLapHD(),hd.getTongTien(),hd.getTienGiam(),hd.getHinhThucThanhToan(),hd.isTrangThai() ? "Hoàn Thành" : "Chưa Hoàn Thành",hd.getKhachHang().getSdtKH(),hd.getNhanVien().getMaNV(),hd.getLoaiHoaDon().getMaLoaiHD()});
		}
	}
	private void locHoaDon() {
	    df_HoaDon.setRowCount(0); // Xóa sạch dữ liệu cũ trong bảng

	    String httt = cb_HTTT.getSelectedItem().toString(); // Lấy giá trị của hình thức thanh toán
	    String trangThai = cb_TrangThai.getSelectedItem().toString(); // Lấy giá trị của trạng thái
	    boolean sapXepTangDan = rdo_TTTD.isSelected(); // Kiểm tra xem có chọn sắp xếp tăng dần không
	    boolean tt;
	    if(trangThai.equals("Hoàn Thành")) {
	    	tt = true;
	    }
	    else {
	    	tt = false;
	    }

	    List<HoaDon_entity> danhSachHoaDon = hd_Dao.getALLHoaDon();

	    // Lọc theo hình thức thanh toán
	    if (!httt.equals("Tất Cả")) {
	        danhSachHoaDon.removeIf(hd -> !hd.getHinhThucThanhToan().equals(httt));
	    }

	    // Lọc theo trạng thái (Hoàn Thành là true, Chưa Hoàn Thành là false)
	    if (tt == true) {
	        danhSachHoaDon.removeIf(hd -> !hd.isTrangThai()); // Loại bỏ những hóa đơn chưa hoàn thành (false)
	    } else if (tt == false) {
	        danhSachHoaDon.removeIf(hd -> hd.isTrangThai()); // Loại bỏ những hóa đơn đã hoàn thành (true)
	    }

	    // Sắp xếp danh sách hóa đơn theo tổng tiền
	    danhSachHoaDon.sort((hd1, hd2) -> {
	        if (sapXepTangDan) {
	            return Double.compare(hd1.getTongTien(), hd2.getTongTien());
	        } else {
	            return Double.compare(hd2.getTongTien(), hd1.getTongTien());
	        }
	    });

	    // Đưa dữ liệu đã lọc và sắp xếp vào bảng
	    for (HoaDon_entity hd : danhSachHoaDon) {
	        df_HoaDon.addRow(new Object[]{
	            hd.getMaHD(),
	            hd.getNgayLapHD(),
	            hd.getTongTien(),
	            hd.getTienGiam(),
	            hd.getHinhThucThanhToan(),
	            hd.isTrangThai() ? "Hoàn Thành" : "Chưa Hoàn Thành", // Hiển thị trạng thái bằng chuỗi
	            hd.getKhachHang().getSdtKH(),
	            hd.getNhanVien().getMaNV(),
	            hd.getLoaiHoaDon().getMaLoaiHD()
	        });
	    }
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
