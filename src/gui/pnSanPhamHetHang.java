
package gui;

import com.lowagie.text.List;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;

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

import com.toedter.calendar.JDateChooser;

import connectDB.connectDB;
import dao.ThongKe_DAO;
import entity.ModelData;
import entity.ModelDataSP;
import entity.SanPham_entity;

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

/**
 *
 * @author RAVEN
 */
public class pnSanPhamHetHang extends SimpleForm implements ActionListener{
	private JPanel pnCenter;
	private ThongKe_DAO tk_Dao;
	private JLabel lblNewLabel;
	private JTable table;
	private DefaultTableModel dftb_SanPham;
	private JTable tb_SanPham;
	private JButton btnBaoCao;
	private JButton btnXuatFile;
	private JLabel lblNewLabel_1;
	private JTextField tf_soLuong;
	private JLabel lblNewLabel_2;
	private JComboBox cb_sp;

	/**
     * Creates new form Main
     */
    public pnSanPhamHetHang() {
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
        lblNewLabel.setBounds(10, 10, 201, 40);
        pnCenter.add(lblNewLabel);
        
        String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Ngày Sản Xuất", "Ngày Hết Hạn",
				"Khối Lượng", "Đơn Vị Tính", "Nhà Cung Cấp", "Giá", "Thành Phần", "Công Dụng", "Hình Ảnh",
				"Loại SP"};
        
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
		}

		JScrollPane scp_SanPham = new JScrollPane(tb_SanPham);
		scp_SanPham.setBackground(new Color(255, 255, 255));
		scp_SanPham.setBounds(10, 122, 1480, 426);
		pnCenter.add(scp_SanPham);
		
		docSanPhamSapHetHang();
        
        btnXuatFile = new JButton("Xuất File Excel");
        btnXuatFile.setForeground(new Color(0, 0, 0));
        btnXuatFile.setBackground(new Color(255, 255, 255));
        btnXuatFile.setFont(new Font("Arial", Font.BOLD, 15));
        btnXuatFile.setBorder(new LineBorder(Color.BLACK, 1, true));
        btnXuatFile.setBounds(1156, 572, 145, 40);
        pnCenter.add(btnXuatFile);
        
        
        btnXuatFile.addActionListener(this);
        
        btnBaoCao = new JButton("In Báo Cáo");
        btnBaoCao.setBorder(new LineBorder(Color.BLACK, 1, true));
        btnBaoCao.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		xuatBaoCaoRaWord();
        	}
        });
        btnBaoCao.setForeground(Color.BLACK);
        btnBaoCao.setFont(new Font("Arial", Font.BOLD, 15));
        btnBaoCao.setBackground(Color.WHITE);
        btnBaoCao.setBounds(1315, 572, 145, 40);
        pnCenter.add(btnBaoCao);
        
        lblNewLabel_1 = new JLabel("Số Lượng :");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(10, 79, 103, 33);
        pnCenter.add(lblNewLabel_1);
        
        tf_soLuong = new JTextField();
        tf_soLuong.setFont(new Font("Serif", Font.PLAIN, 18));
        tf_soLuong.setForeground(new Color(0, 0, 0));
        tf_soLuong.setBackground(new Color(255, 255, 255));
        tf_soLuong.setBounds(101, 79, 44, 34);
        tf_soLuong.setBorder(null);
        tf_soLuong.setText(tk_Dao.soSanPhamSapHetHang()+"");
        pnCenter.add(tf_soLuong);
        tf_soLuong.setColumns(10);
        
        lblNewLabel_2 = new JLabel("Sản Phẩm :");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setBackground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(1143, 79, 103, 33);
        pnCenter.add(lblNewLabel_2);
        
        cb_sp = new JComboBox();
        cb_sp.setForeground(new Color(0, 0, 0));
        cb_sp.setBackground(new Color(255, 255, 255));
        cb_sp.setBounds(1234, 79, 184, 34);
        cb_sp.setBorder(new LineBorder(Color.BLACK, 1));
        cb_sp.addItem("Sản Phẩm Sắp Hết Hàng");
        cb_sp.addItem("Sản Phẩm Đã Hết Hàng");
        pnCenter.add(cb_sp);
        cb_sp.addActionListener(e -> {
            // Kiểm tra lựa chọn trong JComboBox và gọi phương thức phù hợp
            if (cb_sp.getSelectedItem().equals("Sản Phẩm Sắp Hết Hàng")) {
            	tf_soLuong.setText(tk_Dao.soSanPhamSapHetHang()+"");
            	docSanPhamSapHetHang();
            } else if (cb_sp.getSelectedItem().equals("Sản Phẩm Đã Hết Hàng")) {
            	tf_soLuong.setText(tk_Dao.soSanPhamDaHetHang()+"");
            	docSanPhamDaHetHang();
            }
        });
    }

    private void initComponents() {
        
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        pnCenter.setBounds(10, 10, 1500, 640);
        pnCenter.setLayout(null);
    }
    public void docSanPhamDaHetHang() {
    	dftb_SanPham.setRowCount(0); // Clear the current table model
		java.util.List<SanPham_entity> products = tk_Dao.getAllSanPhamHetHang(); // Assume this method exists
		// Adding products to the table model
				for (SanPham_entity product : products) {
					dftb_SanPham.addRow(new Object[] { product.getMaSP(), product.getTenSP(), product.getSoLuong(),
							product.getNgaySanXuat(), product.getNgayHetHan(), product.getKhoiLuong(), product.getDonViTinh(),
							product.getNhaCungCap(), product.getGia(), product.getThanhPhan(), product.getCongDung(),
							product.getHinhAnhSP(), product.getLoaiSanPham().getMaLoaiSP(),
							// Add actions for Update and Delete as necessary
					});
				}
    	
    }
    public void docSanPhamSapHetHang() {
    	dftb_SanPham.setRowCount(0); // Clear the current table model
		java.util.List<SanPham_entity> products = tk_Dao.getAllSanPhamSapHetHang(); // Assume this method exists
		// Adding products to the table model
				for (SanPham_entity product : products) {
					dftb_SanPham.addRow(new Object[] { product.getMaSP(), product.getTenSP(), product.getSoLuong(),
							product.getNgaySanXuat(), product.getNgayHetHan(), product.getKhoiLuong(), product.getDonViTinh(),
							product.getNhaCungCap(), product.getGia(), product.getThanhPhan(), product.getCongDung(),
							product.getHinhAnhSP(), product.getLoaiSanPham().getMaLoaiSP(),
							// Add actions for Update and Delete as necessary
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

	}
	public void xuatBaoCaoRaWord() {
	    XWPFDocument document = new XWPFDocument();
	    
	    // Tạo tiêu đề báo cáo
	    XWPFParagraph title = document.createParagraph();
	    title.setAlignment(ParagraphAlignment.CENTER);
	    XWPFRun titleRun = title.createRun();
	    titleRun.setText("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
	    titleRun.addBreak();
	    titleRun.setText("Độc Lập - Tự Do - Hạnh Phúc");
	    titleRun.addBreak();
	    titleRun.addBreak();
	    titleRun.setText("Hiệu Thuốc Bán Lẻ An Tâm");
	    titleRun.addBreak();
	    titleRun.addBreak();
	    if (cb_sp.getSelectedItem().equals("Sản Phẩm Sắp Hết Hàng")) {
	    	titleRun.setText("Báo Cáo Sản Phẩm Sắp Hết Hàng");
        } else if (cb_sp.getSelectedItem().equals("Sản Phẩm Đã Hết Hàng")) {
        	titleRun.setText("Báo Cáo Sản Phẩm Đã Hết Hàng");
        }
	    
	    titleRun.addBreak();
	    titleRun.setBold(true);
	    titleRun.setFontSize(16);

	    // Thêm thông tin phụ
	    XWPFParagraph subTitle = document.createParagraph();
	    subTitle.setAlignment(ParagraphAlignment.RIGHT); // Căn phải đoạn văn bản
	    XWPFRun subTitleRun = subTitle.createRun();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    subTitleRun.setText("Ngày lập báo cáo: " + sdf.format(new Date()));
	    subTitleRun.setFontSize(12);

	    // Thêm phần "Kính Gửi" và thông tin khác với căn trái và không in đậm
	    XWPFParagraph content = document.createParagraph();
	    content.setAlignment(ParagraphAlignment.LEFT); // Căn trái đoạn văn bản
	    XWPFRun contentRun = content.createRun();
	    contentRun.setText("Kính Gửi:");
	    contentRun.addBreak();
	    contentRun.setText("Họ Tên Nhân Viên:");
	    contentRun.addBreak();
	    if (cb_sp.getSelectedItem().equals("Sản Phẩm Sắp Hết Hàng")) {
	    	contentRun.setText("Lý Do: Báo cáo danh sách sản phẩm sắp hết hàng");
        } else if (cb_sp.getSelectedItem().equals("Sản Phẩm Đã Hết Hàng")) {
        	contentRun.setText("Lý Do: Báo cáo danh sách sản phẩm đã hết hàng");
        }
	    contentRun.addBreak();
	    contentRun.setText("Danh Sách Sản Phẩm:");
	    contentRun.setFontSize(12); // Không in đậm, giữ font chữ nhỏ hơn
	

	    // Tạo bảng trong Word
	    XWPFTable table = document.createTable();

	 // Thêm hàng tiêu đề cho bảng
	    XWPFTableRow headerRow = table.getRow(0);
	    headerRow.getCell(0).setText("STT"); // Thêm cột STT
	    headerRow.addNewTableCell().setText("Mã Sản Phẩm");
	    headerRow.addNewTableCell().setText("Tên Sản Phẩm");
	    headerRow.addNewTableCell().setText("Số Lượng");
	    headerRow.addNewTableCell().setText("Ngày Sản Xuất");
	    headerRow.addNewTableCell().setText("Ngày Hết Hạn");
	    headerRow.addNewTableCell().setText("Khối Lượng");
	    headerRow.addNewTableCell().setText("Đơn Vị Tính");
	    headerRow.addNewTableCell().setText("Nhà Cung Cấp");
	    headerRow.addNewTableCell().setText("Giá");
	    headerRow.addNewTableCell().setText("Loại SP");
	    headerRow.addNewTableCell().setText("Thuế");

	    headerRow.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000)); 
	    headerRow.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5000));
	    headerRow.getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000)); 
	    headerRow.getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5000));
	    headerRow.getCell(4).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000)); 
	    headerRow.getCell(5).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000));
	    headerRow.getCell(6).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000)); 
	    headerRow.getCell(7).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(700));
	    headerRow.getCell(8).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000)); 
	    headerRow.getCell(9).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000));
	    headerRow.getCell(10).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000));
	    headerRow.getCell(11).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(7000));
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
	        row.getCell(7).setText(tb_SanPham.getValueAt(i, 6).toString());
	        row.getCell(8).setText(tb_SanPham.getValueAt(i, 7).toString());
	        row.getCell(9).setText(tb_SanPham.getValueAt(i, 8).toString());
	        row.getCell(10).setText(tb_SanPham.getValueAt(i, 9).toString());
	        row.getCell(11).setText(tb_SanPham.getValueAt(i, 10).toString());
	    }


	    // Tạo hộp thoại lưu file
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Chọn vị trí lưu báo cáo");
	    if (cb_sp.getSelectedItem().equals("Sản Phẩm Sắp Hết Hàng")) {
	    	fileChooser.setSelectedFile(new File("BaoCaoSanPhamSapHetHang.docx"));
        } else if (cb_sp.getSelectedItem().equals("Sản Phẩm Đã Hết Hàng")) {
        	fileChooser.setSelectedFile(new File("BaoCaoSanPhamDaHetHang.docx"));
        }
	    
	    int userSelection = fileChooser.showSaveDialog(null);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = fileChooser.getSelectedFile();
	        
	        // Lưu tài liệu ra file Word
	        try (FileOutputStream out = new FileOutputStream(fileToSave)) {
	            document.write(out);
	            JOptionPane.showMessageDialog(null, "Báo cáo đã được xuất thành công!");
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Lỗi khi lưu báo cáo: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}

}