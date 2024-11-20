
package gui;

import com.lowagie.text.List;

import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;
import nguyenvu.model.ModelData;
import nguyenvu.model.ModelDataSP;
import nguyenvu.model.ModelItemSell;
import nguyenvu.utils.RoundedBorder;
import nguyenvu.utils.RoundedPanel;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.toedter.calendar.JDateChooser;

import ThongKeReport.ReportManager;
import ThongKeReport.SPHetHanReport;
import connectDB.connectDB;
import dao.ThongKe_DAO;
import entity.SanPham_entity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.apache.poi.xwpf.usermodel.TableRowAlign;

/**
 *
 * @author RAVEN
 */
public class pnSanPhamHetHan extends SimpleForm implements ActionListener{
	private JPanel pnCenter;
	private ThongKe_DAO tk_Dao;
	private JLabel lblNewLabel;
	private JTable table;
	private DefaultTableModel dftb_SanPham;
	private JTable tb_SanPham;
	private JButton btnBaoCao;
	private JButton btnXuatFile;
	private JTextField tf_soLuong;
	private JComboBox cb_sp;

	/**
     * Creates new form Main
     */
    public pnSanPhamHetHan() {
    	tk_Dao = new ThongKe_DAO();
		setPreferredSize(new Dimension(1500, 660));
        initComponents();
        setBackground(new Color(240, 240, 240,0));
        setLayout(null);
        add(pnCenter);
        
        lblNewLabel = new JLabel("Danh Sách Sản Phẩm");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 10, 275, 40);
        pnCenter.add(lblNewLabel);
        
        String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Ngày Sản Xuất", "Ngày Hết Hạn",
				"Khối Lượng", "Đơn Vị Tính", "Nhà Cung Cấp", "Giá", "Thành Phần", "Công Dụng", "Hình Ảnh",
				"Loại SP","Thuế"};
        
        dftb_SanPham = new DefaultTableModel(columnNames, 0); // columnNames là mảng chứa tên cột
		tb_SanPham = new JTable(dftb_SanPham);
		tb_SanPham.setForeground(new Color(0, 0, 0));
		tb_SanPham.setFont(new Font("Arial", Font.PLAIN, 10));
		tb_SanPham.setModel(dftb_SanPham);

		tb_SanPham.setBackground(new Color(255, 255, 255));

		tb_SanPham.getTableHeader().setReorderingAllowed(false);
		tb_SanPham.setRowHeight(60);
		
		if (tb_SanPham.getColumnModel().getColumnCount() > 0) {
			tb_SanPham.getColumnModel().getColumn(0).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(0).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(1).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(1).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(2).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(2).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(3).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(3).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(4).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(4).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(5).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(5).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(6).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(6).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(7).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(7).setPreferredWidth(80);
			tb_SanPham.getColumnModel().getColumn(8).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(8).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(9).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(9).setPreferredWidth(100);
			tb_SanPham.getColumnModel().getColumn(10).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(10).setPreferredWidth(100);
			tb_SanPham.getColumnModel().getColumn(11).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(11).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(12).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(12).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(12).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(12).setPreferredWidth(40);
		}

		JScrollPane scp_SanPham = new JScrollPane(tb_SanPham);
		scp_SanPham.setBackground(new Color(255, 255, 255));
		scp_SanPham.setBounds(10, 132, 1480, 410);
		pnCenter.add(scp_SanPham);
		docDataVaoTableDaHetHan();
        btnXuatFile = new JButton("Xuất File Excel");
        btnXuatFile.setBackground(new Color(255, 255, 255));
        btnXuatFile.setForeground(new Color(0, 0, 0));
//        btnXuatFile.setBackground(Color.CYAN);
        btnXuatFile.setFont(new Font("Arial", Font.BOLD, 15));
        btnXuatFile.setBorder(new LineBorder(Color.BLACK, 1, true));
        btnXuatFile.setBounds(1156, 568, 145, 40);
        pnCenter.add(btnXuatFile);
        
        
        btnXuatFile.addActionListener(this);
        
        btnBaoCao = new JButton("In Báo Cáo");
        btnBaoCao.setBorder(new LineBorder(Color.BLACK, 1, true));
        btnBaoCao.addActionListener(this);
        btnBaoCao.setForeground(Color.BLACK);
        btnBaoCao.setFont(new Font("Arial", Font.BOLD, 15));
        btnBaoCao.setBackground(Color.WHITE);
        btnBaoCao.setBounds(1320, 568, 145, 40);
        pnCenter.add(btnBaoCao);
        
        JLabel lblSLng = new JLabel("Số Lượng :");
        lblSLng.setForeground(Color.BLACK);
        lblSLng.setFont(new Font("Serif", Font.PLAIN, 18));
        lblSLng.setBackground(Color.WHITE);
        lblSLng.setBounds(10, 82, 109, 40);
        pnCenter.add(lblSLng);
        
        tf_soLuong = new JTextField();
        tf_soLuong.setHorizontalAlignment(SwingConstants.LEFT);
        tf_soLuong.setFont(new Font("Serif", Font.PLAIN, 18));
        tf_soLuong.setForeground(new Color(0, 0, 0));
        tf_soLuong.setBackground(new Color(255, 255, 255));
        tf_soLuong.setBorder(null);
        tf_soLuong.setBounds(104, 82, 62, 40);
        tf_soLuong.setText(tk_Dao.soSanPhamSapHetHan()+"");
        pnCenter.add(tf_soLuong);
        tf_soLuong.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Sản Phẩm :");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(1121, 85, 109, 35);
        pnCenter.add(lblNewLabel_1);
        
        cb_sp = new JComboBox();
        cb_sp.setForeground(new Color(0, 0, 0));
        cb_sp.setBackground(new Color(255, 255, 255));
        cb_sp.setBorder(new LineBorder(Color.BLACK, 1));
        cb_sp.setBounds(1226, 82, 212, 39);
        pnCenter.add(cb_sp);
        cb_sp.addItem("Sản Phẩm Sắp Hết Hạn");      
        cb_sp.addItem("Sản Phẩm Đã Hết Hạn");   
        cb_sp.addActionListener(e -> {
            // Kiểm tra lựa chọn trong JComboBox và gọi phương thức phù hợp
            if (cb_sp.getSelectedItem().equals("Sản Phẩm Sắp Hết Hạn")) {
            	tf_soLuong.setText(tk_Dao.soSanPhamSapHetHan()+"");
                docDataVaoTableSapHetHan();
            } else if (cb_sp.getSelectedItem().equals("Sản Phẩm Đã Hết Hạn")) {
            	tf_soLuong.setText(tk_Dao.soSanPhamSapHetHan()+"");
            	tf_soLuong.setText(tk_Dao.soSanPhamdaHetHan()+"");
                docDataVaoTableDaHetHan();
            }
        });
        
    }

    private void initComponents() {
        
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        pnCenter.setBounds(10, 10, 1490, 640);
        pnCenter.setLayout(null);
    }
 // Phương thức để đọc dữ liệu sản phẩm sắp hết hạn vào bảng
    public void docDataVaoTableSapHetHan() {
        dftb_SanPham.setRowCount(0); // Xóa dữ liệu cũ
        java.util.List<SanPham_entity> products = tk_Dao.getAllSanPhamSapHetHan(); // Gọi phương thức lấy sản phẩm sắp hết hạn
        for (SanPham_entity product : products) {
            dftb_SanPham.addRow(new Object[] {
                product.getMaSP(), product.getTenSP(), product.getSoLuong(),
                product.getNgaySanXuat(), product.getNgayHetHan(), product.getKhoiLuong(), 
                product.getDonViTinh(), product.getNhaCungCap(), product.getGia(), 
                product.getThanhPhan(), product.getCongDung(), product.getHinhAnhSP(), 
                product.getLoaiSanPham().getMaLoaiSP(),product.getThue()
            });
        }
    }

    // Phương thức để đọc dữ liệu sản phẩm đã hết hạn vào bảng
    public void docDataVaoTableDaHetHan() {
        dftb_SanPham.setRowCount(0); // Xóa dữ liệu cũ
        java.util.List<SanPham_entity> products = tk_Dao.getAllSanPhamHetHan(); // Gọi phương thức lấy sản phẩm đã hết hạn
        for (SanPham_entity product : products) {
            dftb_SanPham.addRow(new Object[] {
                product.getMaSP(), product.getTenSP(), product.getSoLuong(),
                product.getNgaySanXuat(), product.getNgayHetHan(), product.getKhoiLuong(), 
                product.getDonViTinh(), product.getNhaCungCap(), product.getGia(), 
                product.getThanhPhan(), product.getCongDung(), product.getHinhAnhSP(), 
                product.getLoaiSanPham().getMaLoaiSP(),product.getThue()
            });
        }
    }
	public void openFile(String file) {
		try {
			File path = new File(file);
			Desktop.getDesktop().open(path);

					
		} catch (IOException io) {
			// TODO: handle exception
			System.out.println(io);
		}
		
	}
        public void xuatBaoCaoRaWord() {
    XWPFDocument document = new XWPFDocument();

    // Tạo tiêu đề "CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM"
    XWPFParagraph title1 = document.createParagraph();
    title1.setAlignment(ParagraphAlignment.CENTER); // Căn giữa
    XWPFRun title1Run = title1.createRun();
    title1Run.setBold(true);
    title1Run.setFontSize(16);
    title1Run.setText("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");

    // Tạo tiêu đề "Độc Lập - Tự Do - Hạnh Phúc"
    XWPFParagraph title2 = document.createParagraph();
    title2.setAlignment(ParagraphAlignment.CENTER); // Căn giữa
    XWPFRun title2Run = title2.createRun();
    title2Run.setBold(true);
    title2Run.setFontSize(16);
    title2Run.setText("Độc Lập - Tự Do - Hạnh Phúc");
    title2Run.addBreak(); // Thêm dòng trống
    title2Run.setText("-----------------------");
    title2Run.addBreak(); // Thêm dòng trống

    // Tạo phần "Ngày lập báo cáo" ngay sau "Độc Lập - Tự Do - Hạnh Phúc"
    XWPFParagraph dateParagraph = document.createParagraph();
    dateParagraph.setAlignment(ParagraphAlignment.RIGHT); // Căn phải
    XWPFRun dateRun = dateParagraph.createRun();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    dateRun.setText("Ngày lập báo cáo: " + sdf.format(new Date()));
    dateRun.setFontSize(12);
    dateRun.addBreak(); // Thêm dòng trống

    // Tạo tiêu đề "Hiệu Thuốc Bán Lẻ An Tâm"
    XWPFParagraph pharmacyName = document.createParagraph();
    pharmacyName.setAlignment(ParagraphAlignment.CENTER); // Căn giữa
    XWPFRun pharmacyRun = pharmacyName.createRun();
    pharmacyRun.setBold(true);
    pharmacyRun.setFontSize(16);
    pharmacyRun.setText("Hiệu Thuốc Bán Lẻ An Tâm");

    // Lấy lựa chọn từ ComboBox và đặt tiêu đề báo cáo
    String selectedItem = cb_sp.getSelectedItem().toString();
    XWPFParagraph reportTitle = document.createParagraph();
    reportTitle.setAlignment(ParagraphAlignment.CENTER); // Căn giữa
    XWPFRun reportRun = reportTitle.createRun();
    if (selectedItem.equals("Sản Phẩm Sắp Hết Hạn")) {
        reportRun.setText("Báo Cáo Sản Phẩm Sắp Hết Hạn");
    } else if (selectedItem.equals("Sản Phẩm Đã Hết Hạn")) {
        reportRun.setText("Báo Cáo Sản Phẩm Đã Hết Hạn");
    }
    reportRun.setFontSize(16);
    reportRun.setBold(true);
    reportRun.addBreak(); // Thêm dòng trống

    // Thêm phần "Kính Gửi" và các thông tin khác
    XWPFParagraph content = document.createParagraph();
    content.setAlignment(ParagraphAlignment.LEFT);
    XWPFRun contentRun = content.createRun();
    contentRun.setFontSize(12);
    contentRun.setText("Kính Gửi:");
    contentRun.addBreak();
    contentRun.addBreak();
    contentRun.setText("Họ Tên Nhân Viên:");
    contentRun.addBreak();
    contentRun.addBreak();
    contentRun.setText("Lý Do: Báo cáo danh sách sản phẩm " + (selectedItem.equals("Sản Phẩm Sắp Hết Hạn") ? "sắp hết hạn" : "đã hết hạn"));
    contentRun.addBreak();
    contentRun.addBreak();
    contentRun.setText("Danh Sách Sản Phẩm:");
    contentRun.addBreak();
    contentRun.addBreak();
    contentRun.addBreak();

    // Tạo bảng trong Word
    XWPFTable table = document.createTable();

    // Căn bảng ra chiếm hết chiều rộng
    table.setTableAlignment(TableRowAlign.CENTER); // Căn giữa cho bảng

    // Thiết lập độ rộng bảng chiếm hết trang
    CTTblWidth tblWidth = table.getCTTbl().addNewTblPr().addNewTblW();
    tblWidth.setW(BigInteger.valueOf(11000));  // 10000 tương ứng với khoảng 115% chiều rộng trang

    // Thêm hàng tiêu đề cho bảng
    XWPFTableRow headerRow = table.getRow(0);
    headerRow.getCell(0).setText("STT");
    headerRow.addNewTableCell().setText("Mã Sản Phẩm");
    headerRow.addNewTableCell().setText("Tên Sản Phẩm");
    headerRow.addNewTableCell().setText("Số Lượng");
    headerRow.addNewTableCell().setText("Ngày Sản Xuất");
    headerRow.addNewTableCell().setText("Ngày Hết Hạn");
    headerRow.addNewTableCell().setText("Khối Lượng");
    headerRow.addNewTableCell().setText("Giá");
    headerRow.addNewTableCell().setText("Loại SP");
    headerRow.addNewTableCell().setText("Thuế");

    // Cấu hình chiều rộng cho từng cột
    headerRow.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000)); // Cột STT
    headerRow.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000)); // Cột Mã Sản Phẩm
    headerRow.getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500)); // Cột Tên Sản Phẩm
    headerRow.getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1500)); // Cột Số Lượng
    headerRow.getCell(4).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000)); // Cột Ngày Sản Xuất
    headerRow.getCell(5).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000)); // Cột Ngày Hết Hạn
    headerRow.getCell(6).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1500)); // Cột Khối Lượng
    headerRow.getCell(7).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000)); // Cột Giá
    headerRow.getCell(8).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1500)); // Cột Loại SP
    headerRow.getCell(9).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1500)); // Cột Thuế

    // Thêm dữ liệu sản phẩm vào bảng
    for (int i = 0; i < tb_SanPham.getRowCount(); i++) {
        XWPFTableRow row = table.createRow();
        row.getCell(0).setText(String.valueOf(i + 1)); // Thêm STT (bắt đầu từ 1)
        row.getCell(1).setText(tb_SanPham.getValueAt(i, 0).toString());
        row.getCell(2).setText(tb_SanPham.getValueAt(i, 1).toString());
        row.getCell(3).setText(tb_SanPham.getValueAt(i, 2).toString());
        row.getCell(4).setText(tb_SanPham.getValueAt(i, 3).toString());
        row.getCell(5).setText(tb_SanPham.getValueAt(i, 4).toString());
        row.getCell(6).setText(tb_SanPham.getValueAt(i, 5).toString());
        row.getCell(7).setText(tb_SanPham.getValueAt(i, 8).toString());
        row.getCell(8).setText(tb_SanPham.getValueAt(i, 12).toString());
        row.getCell(9).setText(tb_SanPham.getValueAt(i, 13).toString());
    }
    
//   Thêm phần Ký Tên của Nhân Viên và Quản Lý
    XWPFParagraph signParagraph = document.createParagraph();
    signParagraph.setAlignment(ParagraphAlignment.LEFT); // Căn trái
    XWPFRun signRun = signParagraph.createRun();
    signRun.setFontSize(12);
    signRun.addBreak();
    signRun.addBreak();
    signRun.addBreak();
    signRun.setText("Nhân viên:");
    signRun.addBreak();
    signRun.setText("(Ký, ghi rõ họ tên)");

    // Thêm phần Ký Tên của Quản Lý
    XWPFParagraph managerSignParagraph = document.createParagraph();
    managerSignParagraph.setAlignment(ParagraphAlignment.RIGHT); // Căn trái
    XWPFRun managerSignRun = managerSignParagraph.createRun();
    managerSignRun.setFontSize(12);
    managerSignRun.setText("Quản Lý:");
    managerSignRun.addBreak();
    managerSignRun.setText("(Ký, ghi rõ họ tên)");

    // Tạo hộp thoại lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn vị trí lưu báo cáo");
    if (cb_sp.getSelectedItem().equals("Sản Phẩm Sắp Hết Hạn")) {
        fileChooser.setSelectedFile(new File("BaoCaoSanPhamSapHetHan.docx"));
    } else if (cb_sp.getSelectedItem().equals("Sản Phẩm Đã Hết Hạn")) {
        fileChooser.setSelectedFile(new File("BaoCaoSanPhamDaHetHan.docx"));
    }

    // Chọn vị trí lưu báo cáo
    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        try (FileOutputStream out = new FileOutputStream(fileChooser.getSelectedFile())) {
            document.write(out);
            JOptionPane.showMessageDialog(this, "Xuất báo cáo thành công!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất báo cáo!");
        }
    }
}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnXuatFile)) {
			try {
				JFileChooser fileChoose = new JFileChooser();
				fileChoose.showSaveDialog(this);
				File saveFile = fileChoose.getSelectedFile();
				if(saveFile != null) {
					saveFile = new File(saveFile.toString()+".xlsx");
					Workbook wb = new XSSFWorkbook();
					Sheet sheet = wb.createSheet("Product");
					Row rowCol = sheet.createRow(0);
					for(int i = 0; i < tb_SanPham.getColumnCount(); i ++) {
						Cell cell = rowCol.createCell(i);
						cell.setCellValue(tb_SanPham.getColumnName(i));
					}
					for(int j = 0; j < tb_SanPham.getRowCount();j++) {
						Row row = sheet.createRow(j);
						for( int k = 0; k < tb_SanPham.getColumnCount();k++) {
							Cell cell = row.createCell(k);
							if(tb_SanPham.getValueAt(j, k)!= null) {
								cell.setCellValue(tb_SanPham.getValueAt(j, k).toString());
							}
						}
					}
					FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
					wb.write(out);
					wb.close();
					out.close();
					openFile(saveFile.toString());
				}else {
					JOptionPane.showMessageDialog(null,"Error");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if (o.equals(btnBaoCao)) {
			xuatBaoCaoRaWord();
	    }
		

	}
}