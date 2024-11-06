
package gui;

import com.lowagie.text.List;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;
import nguyenvu.model.ModelItemSell;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import ThongKeReport.ReportManager;
import ThongKeReport.SPHetHanReport;
import connectDB.connectDB;
import dao.ThongKe_DAO;
import entity.ModelData;
import entity.ModelDataSP;
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
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

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
	private JTextField textField;

	/**
     * Creates new form Main
     */
    public pnSanPhamHetHan() {
    	tk_Dao = new ThongKe_DAO();
    	try {
			ReportManager.getInstance().compileReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				"Loại Sản Phẩm"};
        
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
		scp_SanPham.setBounds(10, 132, 1470, 410);
		pnCenter.add(scp_SanPham);
        
        btnXuatFile = new JButton("Xuất File Excel");
        btnXuatFile.setForeground(new Color(0, 0, 0));
        btnXuatFile.setBackground(new Color(255, 255, 255));
        btnXuatFile.setFont(new Font("Arial", Font.BOLD, 15));
        btnXuatFile.setBounds(1158, 568, 145, 40);
        pnCenter.add(btnXuatFile);
        
        
        btnXuatFile.addActionListener(this);
        
        btnBaoCao = new JButton("In Báo Cáo");
//        btnBaoCao.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		java.util.List<SanPham_entity> list = new ArrayList<SanPham_entity>();
//        		for(int i = 0; i<tb_SanPham.getRowCount();i++) {
//        			SanPham_entity data =  (SanPham_entity) tb_SanPham.getValueAt(i, 0);
//        			list.add(new SanPham_entity(data.getMaSP(),data.getTenSP(), data.getNgaySanXuat(),data.getNgayHetHan(), data.getKhoiLuong(), data.getDonViTinh(), data.getNhaCungCap(), data.getGia(), data.getThanhPhan(),data.getCongDung(), data.getHinhAnhSP(), data.getLoaiSanPham(), data.getSoLuong()));
//        		}
//        		SPHetHanReport SP = new SPHetHanReport();
//        		try {
//					ReportManager.getInstance().printReportProduct(SP);
//				} catch (JRException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//        	}
//        });
        btnBaoCao.setForeground(Color.BLACK);
        btnBaoCao.setFont(new Font("Arial", Font.BOLD, 15));
        btnBaoCao.setBackground(Color.WHITE);
        btnBaoCao.setBounds(1313, 568, 145, 40);
        pnCenter.add(btnBaoCao);
        
        JLabel lblSLng = new JLabel("Số Lượng :");
        lblSLng.setForeground(Color.BLACK);
        lblSLng.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSLng.setBackground(Color.WHITE);
        lblSLng.setBounds(10, 82, 109, 40);
        pnCenter.add(lblSLng);
        
        textField = new JTextField();
        textField.setBounds(104, 82, 96, 32);
        pnCenter.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Sản Phẩm :");
        lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(1158, 82, 109, 35);
        pnCenter.add(lblNewLabel_1);
        
        JComboBox cb_sp = new JComboBox();
        cb_sp.setBounds(1257, 83, 179, 32);
        pnCenter.add(cb_sp);
        
        docDataVaoTable();
    }

    private void initComponents() {
        
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(255, 255, 255));
        pnCenter.setBounds(10, 10, 1490, 640);
        pnCenter.setLayout(null);
    }
    public void docDataVaoTable() {
    	dftb_SanPham.setRowCount(0); // Clear the current table model
		java.util.List<SanPham_entity> products = tk_Dao.getAllSanPhamHetHan(); // Assume this method exists
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
}