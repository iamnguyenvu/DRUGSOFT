
package gui;

import com.lowagie.text.List;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;
import nguyenvu.model.DoanhSoBanHangModalData;
import nguyenvu.model.ModalDataSoLuongGiaoDich;
import nguyenvu.model.ModelData;
import nguyenvu.model.ModelDataSP;
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
public class DoanhSoBanHang extends SimpleForm{

    private Chart chart;
	private JPanel pnCenter;
	private ThongKe_DAO tk_Dao;

	/**
     * Creates new form Main
     */
    public DoanhSoBanHang(int time) {
    	tk_Dao = new ThongKe_DAO();
		setPreferredSize(new Dimension(1041, 668));
        initComponents();
        setBackground(new Color(240, 240, 240,0));
        setLayout(null);
        add(pnCenter);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        chart.addLegend("Doanh Thu", new Color(245, 189, 135));
        chart.addLegend("Chi Phí", new Color(135, 189, 245));
        chart.addLegend("Lợi Nhuận", new Color(189, 135, 245));
        themData(time);
        chart.start();
    }
    public DoanhSoBanHang(java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc) {
    	tk_Dao = new ThongKe_DAO();
		setPreferredSize(new Dimension(1041, 668));
        initComponents();
        setBackground(new Color(240, 240, 240,0));
        setLayout(null);
        add(pnCenter);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        chart.addLegend("Doanh Thu", new Color(245, 189, 135));
        chart.addLegend("Chi Phí", new Color(135, 189, 245));
        chart.addLegend("Lợi Nhuận", new Color(189, 135, 245));
        themData(ngayBatDau, ngayKetThuc);
        chart.start();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(240, 240, 240,0));
        pnCenter.setBounds(0, 0, 1500, 668);
        pnCenter.setLayout(null);
                
                        chart = new com.raven.chart.Chart();
                        chart.setBackground(new Color(255, 255, 255));
                        chart.setBounds(0, 0, 1040, 668);
                        pnCenter.add(chart);
                        
                                chart.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    public void themData(int time) {
    	java.util.List<DoanhSoBanHangModalData> lists = new ArrayList<DoanhSoBanHangModalData>();
    		 lists = tk_Dao.layDoanhSoBanHangTQTheoThoiGian(time);
       
            chart.clear();
            for (DoanhSoBanHangModalData data : lists) {
            	ModelChart mdChart = new ModelChart(data.getThang(),new double[] {data.getTongDoanhThu(),data.getTongChiPhi(),data.getLoiNhuan()});
                chart.addData(mdChart);
            }
            chart.start();
    }
    public void themData(java.sql.Date ngayBatDau,java.sql.Date ngayKetThuc) {
    	java.util.List<DoanhSoBanHangModalData> lists = new ArrayList<DoanhSoBanHangModalData>();
    		 lists = tk_Dao.layDoanhSoBanHangTQTheoThoiGian(ngayBatDau, ngayKetThuc);
       
            chart.clear();
            for (DoanhSoBanHangModalData data : lists) {
            	ModelChart mdChart = new ModelChart(data.getThang(),new double[] {data.getTongDoanhThu(),data.getTongChiPhi(),data.getLoiNhuan()});
                chart.addData(mdChart);
            }
            chart.start();
    }


//    private void xuatExcel() throws SQLException {
//        // Lấy dữ liệu từ JDateChooser và JComboBox
//        Date startDate = dcNgayBatDau.getDate();
//        Date endDate = dcNgayKetThuc.getDate();
//        ArrayList<DoanhSoBanHangNV> dsList;
//        
//        if (startDate == null && endDate == null) {
//        	dsList = tk_Dao.layDoanhSoBanHangTQ();
//        } else if (startDate != null && endDate == null) {
//            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Kết Thúc!", MessageAlerts.MessageType.ERROR);
//            return;
//        } else if (startDate == null && endDate != null) {
//            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Bắt Đầu!", MessageAlerts.MessageType.ERROR);
//            return;
//        } else {
//        	dsList = tk_Dao.layDoanhSoBanHangTQ(startDate, endDate);
//        }
//
//        // Thực hiện logic để lấy danh sách sản phẩm theo tiêu chí lọc
//        
//		
//		// Tạo workbook và sheet
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet("Doanh Số Bán Hàng Theo Tháng");
//
//		// Tạo tiêu đề cột
//		XSSFRow headerRow = sheet.createRow(0);
//		headerRow.createCell(0).setCellValue("Tháng");
//		headerRow.createCell(1).setCellValue("Doanh Số");
//
//		
//		// Thêm dữ liệu vào sheet
//		int rowNum = 1;
//		for (DoanhSoBanHangNV ds : dsList) {
//		    XSSFRow row = sheet.createRow(rowNum++);
//		    row.createCell(0).setCellValue(ds.getHotenNV());
//		    row.createCell(1).setCellValue(ds.getDoanhSo());
//		    
//		}
//
//		// Hiển thị JFileChooser để chọn vị trí lưu file
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
//		fileChooser.setSelectedFile(new File("DoanhSoBanHangTongQuan.xlsx")); // Đặt tên file mặc định
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