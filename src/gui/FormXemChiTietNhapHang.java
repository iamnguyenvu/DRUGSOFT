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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietNhapHang_DAO;
import dao.SanPham_DAO;
import entity.ChiTietNhapHang_entity;
import entity.LoaiSanPham_entity;
import entity.NhapHang_entity;
import entity.SanPham_entity;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.RoundedPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class FormXemChiTietNhapHang extends SimpleForm implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Border border;
	private SanPham sanPham;
	private String maSP;
	public SanPham_entity spCapNhat;
	private DefaultTableModel dftb;
	private JTable table;
	private JLabel lblMaNH;
	private JLabel lblThoiGian;
	private JLabel lblTrangThai;
	private JLabel lblPTTT;
	private JLabel lblTongTien;
	private JTextArea taGhiChu;
	private ChiTietNhapHang_DAO ctnh_DAO;
	private JLabel lblTongSoLuong;

	public FormXemChiTietNhapHang() {
		ctnh_DAO = new ChiTietNhapHang_DAO();
		sanPham = new SanPham();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(1193, 642));

		JPanel pnContent = new JPanel();
		pnContent.setBounds(1, 1, 1188, 640);
		pnContent.setPreferredSize(new Dimension(1150, 800));
		Color color_pnContent = Color.decode("#f4f6f8");
		setLayout(null);
		pnContent.setBackground(color_pnContent);
		add(pnContent);
		pnContent.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(27, 77, 1118, 528);
		pnContent.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Nhập Hàng");
		lblNewLabel.setBounds(10, 28, 132, 13);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		
		lblMaNH = new JLabel("New label");
		lblMaNH.setBounds(159, 28, 116, 13);
		panel.add(lblMaNH);
		lblMaNH.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Thời Gian:");
		lblNewLabel_1.setBounds(10, 82, 90, 13);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 15));
		
		lblThoiGian = new JLabel("New label");
		lblThoiGian.setBounds(157, 82, 118, 13);
		panel.add(lblThoiGian);
		lblThoiGian.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Trạng Thái:");
		lblNewLabel_3.setBounds(393, 28, 90, 13);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Serif", Font.PLAIN, 15));
		
		lblTrangThai = new JLabel("New label");
		lblTrangThai.setBounds(590, 28, 116, 13);
		panel.add(lblTrangThai);
		lblTrangThai.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4_1 = new JLabel("Phương Thức Thanh Toán");
		lblNewLabel_4_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(393, 82, 187, 13);
		panel.add(lblNewLabel_4_1);
		
		lblPTTT = new JLabel("New label");
		lblPTTT.setFont(new Font("Serif", Font.PLAIN, 15));
		lblPTTT.setBounds(590, 82, 116, 13);
		panel.add(lblPTTT);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new TitledBorder(null, "Ghi Ch\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(752, 10, 356, 130);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		taGhiChu = new JTextArea();
		taGhiChu.setBounds(10, 33, 336, 87);
		panel_1.add(taGhiChu);

		
		String[] columnNames = { "Mã Nhập Hàng", "Mã Sản Phẩm", "Ngày Sản Xuất", "Ngày Hết Hạn", "Số Lượng", "Thành Tiền"};

		dftb = new DefaultTableModel(columnNames, 0); // columnNames là mảng chứa tên cột
		
		table = new JTable(dftb);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setModel(dftb);
		table.setBackground(Color.WHITE);
		table.setRowHeight(60);
		table.getTableHeader().setReorderingAllowed(false);
		
				// Tùy chỉnh tiêu đề bảng
				table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
				table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
				table.getTableHeader().setBackground(new Color(11, 101, 136));
				table.getTableHeader().setForeground(Color.WHITE);
				
						// Định dạng các cột đặc biệt
						table.getColumnModel().getColumn(0).setPreferredWidth(60); // Mã Sản Phẩm
						table.getColumnModel().getColumn(1).setPreferredWidth(100); // Tên Sản Phẩm
						table.getColumnModel().getColumn(2).setPreferredWidth(80); // Giá Bán
						table.getColumnModel().getColumn(5).setPreferredWidth(40); // Cập Nhật
						
						JScrollPane scp = new JScrollPane(table);
						scp.setBounds(10, 179, 735, 297);
						panel.add(scp);
						scp.setBackground(new Color(240, 240, 240,0));
						
						JPanel panel_2 = new JPanel();
						panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_2.setBackground(new Color(255, 255, 255));
						panel_2.setBounds(752, 179, 356, 130);
						panel.add(panel_2);
						panel_2.setLayout(null);
						
						JLabel lblNewLabel_4 = new JLabel("Tổng Tiền:");
						lblNewLabel_4.setBounds(10, 88, 107, 13);
						panel_2.add(lblNewLabel_4);
						lblNewLabel_4.setFont(new Font("Serif", Font.PLAIN, 15));
						
						lblTongTien = new JLabel("New label");
						lblTongTien.setBounds(212, 88, 90, 13);
						panel_2.add(lblTongTien);
						lblTongTien.setFont(new Font("Serif", Font.PLAIN, 15));
						
						JLabel lblNewLabel_4_3 = new JLabel("Tổng Số Lượng:");
						lblNewLabel_4_3.setFont(new Font("Serif", Font.PLAIN, 15));
						lblNewLabel_4_3.setBounds(10, 35, 107, 13);
						panel_2.add(lblNewLabel_4_3);
						
						lblTongSoLuong = new JLabel("New label");
						lblTongSoLuong.setFont(new Font("Serif", Font.PLAIN, 15));
						lblTongSoLuong.setBounds(212, 35, 90, 13);
						panel_2.add(lblTongSoLuong);
						
						JPanel panel_3 = new JPanel();
						panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_3.setBounds(10, 138, 735, 2);
						panel.add(panel_3);
						
						JPanel panel_4 = new JPanel();
						panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_4.setBounds(306, 10, 1, 118);
						panel.add(panel_4);
						
						JPanel pnHeading = new JPanel();
						pnHeading.setLayout(null);
						pnHeading.setBackground(new Color(11, 101, 136));
						pnHeading.setBounds(0, 0, 1188, 45);
						pnContent.add(pnHeading);
						
								JLabel lb_ThemMoiSP = new JLabel("Thông Tin Nhập Hàng");
								lb_ThemMoiSP.setBounds(10, 0, 269, 45);
								pnHeading.add(lb_ThemMoiSP);
								lb_ThemMoiSP.setForeground(new Color(255, 255, 255));
								lb_ThemMoiSP.setBackground(new Color(0, 0, 0));
								lb_ThemMoiSP.setFont(new Font("Serif", Font.PLAIN, 24));
		border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		// Căn giữa nội dung các ô
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void AddDataToLable(NhapHang_entity nh) {
		lblMaNH.setText(nh.getMaNhapHang());
		lblThoiGian.setText(nh.getNgayNhapHang()+"");
		lblTrangThai.setText(nh.getTrangThai());
		lblPTTT.setText(nh.getHinhThucThanhToan());
		lblTongTien.setText(nh.getTongTien()+"");
		taGhiChu.setText(nh.getGhiChu());
		lblTongSoLuong.setText(ctnh_DAO.TongSoLuongNhapHang(nh.getMaNhapHang())+"");
		loadDataToTable(nh.getMaNhapHang());
	}
    private void loadDataToTable(String key) {
        // Lấy mô hình của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<ChiTietNhapHang_entity> list = ctnh_DAO.searchChiTietNhapHang(key);
        // Xóa dữ liệu cũ
        model.setRowCount(0);

        // Duyệt qua danh sách và thêm từng dòng vào bảng
//        "Mã Nhập Hàng", "Mã Sản Phẩm", "Ngày Sản Xuất", "Ngày Hết Hạn", "Số Lượng", "Thành Tiền
        for (ChiTietNhapHang_entity data : list) {
            model.addRow(new Object[] {data.getNhapHang().getMaNhapHang(),data.getSanPham().getTenSP(),data.getNgaySanXuat(),data.getNgayHetHan(),data.getSoLuong(),data.getThanhTien()});
        }
    }

	
}