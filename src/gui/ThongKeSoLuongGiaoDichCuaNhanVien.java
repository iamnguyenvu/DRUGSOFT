
package gui;

import com.lowagie.text.List;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;
import nguyenvu.model.DoanhSoBanHangModalData;
import nguyenvu.model.ModalDataSoLuongGiaoDich;
import nguyenvu.model.ModelData;
import nguyenvu.model.ModelDataSP;
import nguyenvu.model.SoLuongGiaoDichNV;
import raven.alerts.MessageAlerts;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import connectDB.connectDB;
import dao.ThongKe_DAO;
import entity.DoanhSoBanHangNV;
import entity.HoaDon_entity;
import entity.SanPham_entity;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 *
 * @author RAVEN
 */
public class ThongKeSoLuongGiaoDichCuaNhanVien extends SimpleForm{

    private Chart chart;
	private JPanel pnCenter;
	private ThongKe_DAO tk_Dao;

    public ThongKeSoLuongGiaoDichCuaNhanVien(int time) {
    	tk_Dao = new ThongKe_DAO();
		setPreferredSize(new Dimension(1093, 668));
        initComponents();
        setBackground(new Color(240, 240, 240,0));
        setLayout(new BorderLayout(0, 0));
        add(pnCenter);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        chart.addLegend("Số Lượng Giao Dịch Của Nhân Viên", new Color(135, 189, 245));
        themData(time);
        chart.start();
    }
    public ThongKeSoLuongGiaoDichCuaNhanVien(java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc) {
    	tk_Dao = new ThongKe_DAO();
		setPreferredSize(new Dimension(1041, 668));
        initComponents();
        setBackground(new Color(240, 240, 240,0));
        setLayout(new BorderLayout(0, 0));
        add(pnCenter);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        chart.addLegend("Số Lượng Giao Dịch Của Nhân Viên", new Color(135, 189, 245));
        themData(ngayBatDau,ngayKetThuc);
        chart.start();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(240, 240, 240,0));
                        pnCenter.setLayout(new BorderLayout(0, 0));
                
                        chart = new com.raven.chart.Chart();
                        chart.setBackground(new Color(255, 255, 255));
                        pnCenter.add(chart);
                        
                                chart.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    private void themData(java.util.Date startDate, java.util.Date endDate) {
    	java.util.List<ModalDataSoLuongGiaoDich> lists = tk_Dao.soLuongGiaoDichNhanVien(startDate, endDate);
        chart.clear();
        for (ModalDataSoLuongGiaoDich data : lists) {
        	ModelChart mdChart = new ModelChart(data.getHoTenNV(),new double [] {data.getSoLuongGiaoDich()});
            chart.addData(mdChart);
        }
        chart.start();
    }



    public void themData(int time) {
    	java.util.List<ModalDataSoLuongGiaoDich> lists = tk_Dao.soLuongGiaoDichNhanVien(time);
            chart.clear();
            for (ModalDataSoLuongGiaoDich data : lists) {
            	ModelChart mdChart = new ModelChart(data.getHoTenNV(),new double [] {data.getSoLuongGiaoDich()});
                chart.addData(mdChart);
            }
            chart.start();
    }

//    private void xuatExcel() throws SQLException {
//        // Lấy dữ liệu từ JDateChooser và JComboBox
//        Date startDate = dcNgayBatDau.getDate();
//        Date endDate = dcNgayKetThuc.getDate();
//        ArrayList<SoLuongGiaoDichNV> dsList;
//        
//        if (startDate == null && endDate == null) {
//        	dsList = tk_Dao.laySoLuongGiaoDichNV();
//        } else if (startDate != null && endDate == null) {
//            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Kết Thúc!", MessageAlerts.MessageType.ERROR);
//            return;
//        } else if (startDate == null && endDate != null) {
//            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Bắt Đầu!", MessageAlerts.MessageType.ERROR);
//            return;
//        } else {
//        	dsList = tk_Dao.laySoLuongGiaoDichNV(startDate, endDate);
//        }
//
//        // Thực hiện logic để lấy danh sách sản phẩm theo tiêu chí lọc
//        
//		
//		// Tạo workbook và sheet
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet("Danh sách Số Lượng Giao Dịch Của Nhân Viên");
//
//		// Tạo tiêu đề cột
//		XSSFRow headerRow = sheet.createRow(0);
//		headerRow.createCell(0).setCellValue("Mã Nhân Viên");
//		headerRow.createCell(1).setCellValue("Họ Tên Nhân Viên");
//		headerRow.createCell(2).setCellValue("Số Lượng Giao Dịch");
//
//		
//		// Thêm dữ liệu vào sheet
//		int rowNum = 1;
//		for (SoLuongGiaoDichNV ds : dsList) {
//		    XSSFRow row = sheet.createRow(rowNum++);
//		    row.createCell(0).setCellValue(ds.getMaNV());
//		    row.createCell(1).setCellValue(ds.getHotenNV());
//		    row.createCell(2).setCellValue(ds.getSoLuongDD());
//		    
//		}
//
//		// Hiển thị JFileChooser để chọn vị trí lưu file
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
//		fileChooser.setSelectedFile(new File("ThongKeSoLuongGiaoDichCuaNhanVien.xlsx")); // Đặt tên file mặc định
//
//		int userSelection = fileChooser.showSaveDialog(null);
//		if (userSelection == JFileChooser.APPROVE_OPTION) {
//		    File fileToSave = fileChooser.getSelectedFile();
//		    try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
//		        workbook.write(fileOut);
//		        JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
//		    } catch (IOException ex) {
//		        JOptionPane.showMessageDialog(null, "Lỗi xuất Excel: " + ex.getMessage());
//		    }
//		} else {
//		    JOptionPane.showMessageDialog(null, "Bạn đã hủy thao tác lưu file.");
//		}
//
//		try {
//			workbook.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // Đảm bảo workbook được đóng
//    }



}