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
import dao.HoaDonDoiTra_DAO;
import dao.SanPham_DAO;
import entity.ChiTietNhapHang_entity;
import entity.HoaDonDoiTra_entity;
import entity.LoaiSanPham_entity;
import entity.NhapHang_entity;
import entity.SanPhamDoiTra;
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

public class FormXemChiTietHoaDonDoiTra extends SimpleForm implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Border border;
	private SanPham sanPham;
	private String maSP;
	public SanPham_entity spCapNhat;
	private DefaultTableModel dftb;
	private JTable table;
	private JLabel lblMaDT;
	private JLabel lblMaHD;
	private JLabel lblNgayDoiTra;
	private JLabel lblTienTraLai;
	private HoaDonDoiTra_DAO hddt_DAO;
	private JLabel lblTongSoLuongDoi;
	private JLabel lblTienKhachTraThem;
	private JLabel lblTongSoLuongTra;

	public FormXemChiTietHoaDonDoiTra() {
		hddt_DAO = new HoaDonDoiTra_DAO();
		sanPham = new SanPham();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(1193, 704));

		JPanel pnContent = new JPanel();
		pnContent.setBounds(1, 1, 1188, 693);
		pnContent.setPreferredSize(new Dimension(1150, 800));
		Color color_pnContent = Color.decode("#f4f6f8");
		setLayout(null);
		pnContent.setBackground(color_pnContent);
		add(pnContent);
		pnContent.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(27, 75, 1118, 586);
		pnContent.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Đổi Trả:");
		lblNewLabel.setBounds(10, 28, 132, 13);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		
		lblMaDT = new JLabel("New label");
		lblMaDT.setBounds(159, 28, 116, 13);
		panel.add(lblMaDT);
		lblMaDT.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Mã Hóa Đơn:");
		lblNewLabel_1.setBounds(10, 82, 90, 13);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 15));
		
		lblMaHD = new JLabel("New label");
		lblMaHD.setBounds(157, 82, 118, 13);
		panel.add(lblMaHD);
		lblMaHD.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNew = new JLabel("Ngày Đổi Trả:");
		lblNew.setBounds(10, 128, 90, 13);
		panel.add(lblNew);
		lblNew.setFont(new Font("Serif", Font.PLAIN, 15));
		
		lblNgayDoiTra = new JLabel("New label");
		lblNgayDoiTra.setBounds(159, 128, 116, 13);
		panel.add(lblNgayDoiTra);
		lblNgayDoiTra.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4_1 = new JLabel("Tiền Trả Lại:");
		lblNewLabel_4_1.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(377, 27, 126, 13);
		panel.add(lblNewLabel_4_1);
		
		lblTienTraLai = new JLabel("New label");
		lblTienTraLai.setFont(new Font("Serif", Font.PLAIN, 15));
		lblTienTraLai.setBounds(531, 26, 116, 13);
		panel.add(lblTienTraLai);

		
		String[] columnNames = { "Mã Đổi Trả", "Tên Sản Phẩm", "Số Lượng", "Chiết Khấu", "Thành Tiền", "Loại Đổi Trả","Trạng Thái"};

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
						scp.setBounds(10, 221, 1083, 328);
						panel.add(scp);
						scp.setBackground(new Color(240, 240, 240,0));
						
						JPanel panel_2 = new JPanel();
						panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_2.setBackground(new Color(255, 255, 255));
						panel_2.setBounds(737, 11, 356, 130);
						panel.add(panel_2);
						panel_2.setLayout(null);
						
						JLabel lblNewLabel_4_3 = new JLabel("Tổng Số Lượng Đổi:");
						lblNewLabel_4_3.setFont(new Font("Serif", Font.PLAIN, 15));
						lblNewLabel_4_3.setBounds(10, 24, 147, 13);
						panel_2.add(lblNewLabel_4_3);
						
						lblTongSoLuongDoi = new JLabel("New label");
						lblTongSoLuongDoi.setFont(new Font("Serif", Font.PLAIN, 15));
						lblTongSoLuongDoi.setBounds(212, 24, 90, 13);
						panel_2.add(lblTongSoLuongDoi);
						
						JLabel lblNewLabel_4_3_1 = new JLabel("Tổng Số Lượng Trả");
						lblNewLabel_4_3_1.setFont(new Font("Serif", Font.PLAIN, 15));
						lblNewLabel_4_3_1.setBounds(10, 81, 147, 13);
						panel_2.add(lblNewLabel_4_3_1);
						
						lblTongSoLuongTra = new JLabel("New label");
						lblTongSoLuongTra.setFont(new Font("Serif", Font.PLAIN, 15));
						lblTongSoLuongTra.setBounds(212, 81, 90, 13);
						panel_2.add(lblTongSoLuongTra);
						
						JPanel panel_3 = new JPanel();
						panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_3.setBounds(10, 173, 735, 2);
						panel.add(panel_3);
						
						JPanel panel_4 = new JPanel();
						panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_4.setBounds(326, 22, 1, 118);
						panel.add(panel_4);
						
						JLabel lblNewLabel_4_1_1 = new JLabel("Tiền Khách Trả Thêm:");
						lblNewLabel_4_1_1.setFont(new Font("Serif", Font.PLAIN, 15));
						lblNewLabel_4_1_1.setBounds(377, 82, 144, 13);
						panel.add(lblNewLabel_4_1_1);
						
						lblTienKhachTraThem = new JLabel("New label");
						lblTienKhachTraThem.setFont(new Font("Serif", Font.PLAIN, 15));
						lblTienKhachTraThem.setBounds(531, 82, 116, 13);
						panel.add(lblTienKhachTraThem);
						
						JPanel pnHeading = new JPanel();
						pnHeading.setLayout(null);
						pnHeading.setBackground(new Color(11, 101, 136));
						pnHeading.setBounds(0, 0, 1188, 45);
						pnContent.add(pnHeading);
						
								JLabel lb_ThemMoiSP = new JLabel("Thông Tin Hóa Đơn Đổi Trả");
								lb_ThemMoiSP.setBounds(10, 0, 351, 45);
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
	public void AddDataToLable(HoaDonDoiTra_entity hddt) {
		lblMaDT.setText(hddt.getMaDT());
		lblMaHD.setText(hddt.getMaHD());
		lblNgayDoiTra.setText(hddt.getNgayDoiTra()+"");
		lblTienTraLai.setText(hddt.getTienTraLai()+"");
		lblTienKhachTraThem.setText(hddt.getTienKhachtraThem()+"");
		lblTongSoLuongDoi.setText(hddt_DAO.soLuongSPTra(hddt.getMaDT())+"");
		lblTongSoLuongTra.setText(hddt_DAO.soLuongSPMua(hddt.getMaDT())+"");
		loadDataToTable(hddt.getMaDT());
	}
    private void loadDataToTable(String key) {
        // Lấy mô hình của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<SanPhamDoiTra> list = hddt_DAO.timKiemSPDT(key);
        
        // Xóa dữ liệu cũ
        model.setRowCount(0);

        // Duyệt qua danh sách và thêm từng dòng vào bảng
//        "Mã Nhập Hàng", "Mã Sản Phẩm", "Ngày Sản Xuất", "Ngày Hết Hạn", "Số Lượng", "Thành Tiền
        for (SanPhamDoiTra data : list) {
            model.addRow(new Object[] {data.getMaDT(),data.getTenSP(),data.getSoLuong(),data.getChietKhau(),data.getThanhTien(),data.getLoaiDoiTra(),data.getTrangThai()});
        }
    }

	
}