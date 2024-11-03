
package gui;

import com.lowagie.text.List;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;
import raven.alerts.MessageAlerts;

import java.awt.Color;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.MatteBorder;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import connectDB.connectDB;
import dao.ThongKe_DAO;
import entity.ModelData;
import entity.ModelDataSP;
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
public class barChar_ThongKe extends SimpleForm{

    private Chart chart;
	private JButton btnTruyVan;
	private JPanel pnCenter;
	private JComboBox cbSPBanChayBanCham;
	private JDateChooser dcNgayBatDau;
	private JDateChooser dcNgayKetThuc;
	private JButton btnXuatExcel;
	private ThongKe_DAO tk_Dao;

	/**
     * Creates new form Main
     */
    public barChar_ThongKe() {
    	tk_Dao = new ThongKe_DAO();
		setPreferredSize(new Dimension(1500, 660));
        initComponents();
        setBackground(new Color(240, 240, 240,0));
        setLayout(null);
        add(pnCenter);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setLayout(null);
        panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        panel_1.setBounds(10, 10, 1480, 137);
        pnCenter.add(panel_1);
        
        JLabel lblNewLabel_1 = new JLabel("Thời Gian");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(10, 10, 196, 34);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Ngày Bắt Đầu");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setBackground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(10, 72, 100, 34);
        panel_1.add(lblNewLabel_2);
        
        dcNgayBatDau = new JDateChooser();
        dcNgayBatDau.setBounds(120, 72, 244, 34);
        panel_1.add(dcNgayBatDau);
        
        JLabel lblNewLabel_2_1 = new JLabel("Ngày Kết Thúc");
        lblNewLabel_2_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_2_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_2_1.setBounds(397, 72, 100, 34);
        panel_1.add(lblNewLabel_2_1);
        
        dcNgayKetThuc = new JDateChooser();
        dcNgayKetThuc.setBounds(507, 72, 244, 34);
        panel_1.add(dcNgayKetThuc);
        btnTruyVan = new javax.swing.JButton();
        btnTruyVan.setForeground(new Color(0, 0, 0));
        btnTruyVan.setBackground(new Color(255, 255, 255));
        btnTruyVan.setFont(new Font("Arial", Font.PLAIN, 15));
        btnTruyVan.setBounds(1162, 72, 115, 34);
        panel_1.add(btnTruyVan);
        
                btnTruyVan.setText("Truy Vấn");
                
                cbSPBanChayBanCham = new JComboBox();
                cbSPBanChayBanCham.setFont(new Font("Arial", Font.PLAIN, 13));
                cbSPBanChayBanCham.setBounds(905, 73, 215, 34);
                panel_1.add(cbSPBanChayBanCham);
                cbSPBanChayBanCham.addItem("Sản Phẩm Bán Chạy");
                cbSPBanChayBanCham.addItem("Sản Phẩm Bán Chậm");
                
                JLabel lblNewLabel_3 = new JLabel("Sản Phẩm");
                lblNewLabel_3.setForeground(new Color(0, 0, 0));
                lblNewLabel_3.setBackground(new Color(255, 255, 255));
                lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));
                lblNewLabel_3.setBounds(787, 72, 87, 34);
                panel_1.add(lblNewLabel_3);
                
                btnXuatExcel = new JButton("Xuất File");
                btnXuatExcel.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		xuatExcel();
                	}
                });
                btnXuatExcel.setForeground(new Color(0, 0, 0));
                btnXuatExcel.setBackground(new Color(255, 255, 255));
                btnXuatExcel.setFont(new Font("Arial", Font.PLAIN, 15));
                btnXuatExcel.setBounds(1325, 72, 100, 34);
                panel_1.add(btnXuatExcel);
                btnTruyVan.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });
        chart.addLegend(null, new Color(135, 189, 245));
        themData();
        chart.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(240, 240, 240,0));
        pnCenter.setBounds(0, 0, 1500, 661);
        pnCenter.setLayout(null);
                
                        chart = new com.raven.chart.Chart();
                        chart.setBackground(new Color(255, 255, 255));
                        chart.setBounds(10, 157, 1480, 494);
                        pnCenter.add(chart);
                        
                                chart.setFont(new Font("Arial", Font.PLAIN, 12));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Lấy giá trị từ JDateChooser
        java.util.Date startDate = dcNgayBatDau.getDate();
        java.util.Date endDate = dcNgayKetThuc.getDate();
        
        // Lấy giá trị từ JComboBox
        String selectedProductType = (String) cbSPBanChayBanCham.getSelectedItem();
        
        if (startDate == null && endDate == null) {
            // Nếu không chọn ngày nào, gọi phương thức không cần tham số
            themData();
        } else if (startDate != null && endDate == null) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Kết Thúc!", MessageAlerts.MessageType.ERROR);
            return;
        } else if (startDate == null && endDate != null) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Bắt Đầu!", MessageAlerts.MessageType.ERROR);
            return;
        } else {
            // Nếu cả hai ngày đều không null, gọi phương thức với tham số ngày bắt đầu, ngày kết thúc và loại sản phẩm
            themData(startDate, endDate, selectedProductType);
        }
    }


    private void themData(java.util.Date startDate, java.util.Date endDate, String productType) {
        if (startDate == null || endDate == null) {
            System.out.println("Start date and end date cannot be null.");
            return;
        }
        
        Connection connection = null; // Declare connection variable
        try {
            java.util.List<ModelDataSP> lists = new ArrayList<>(); // Initialize a list to store data
            connection = connectDB.accessDataBase(); // Get database connection
            if (connection == null) {
                System.out.println("Cannot connect to the database.");
                return; // Stop if the connection fails
            }

            String sql;
            if ("Sản Phẩm Bán Chạy".equals(productType)) {
                // SQL query to get top 10 best-selling products within the date range
                sql = "SELECT TOP 10 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan "
                        + "FROM ChiTietHoaDon cthd "
                        + "JOIN SanPham sp ON cthd.maSP = sp.maSP "
                        + "JOIN HoaDon hd ON cthd.maHD = hd.maHD "
                        + "WHERE hd.ngayLapHD BETWEEN ? AND ? "
                        + "GROUP BY sp.maSP, sp.tenSP "
                        + "ORDER BY tongSoLuongBan DESC";
            } else {
                // SQL query to get top 10 least-selling products within the date range
                sql = "SELECT TOP 10 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan "
                        + "FROM ChiTietHoaDon cthd "
                        + "JOIN SanPham sp ON cthd.maSP = sp.maSP "
                        + "JOIN HoaDon hd ON cthd.maHD = hd.maHD "
                        + "WHERE hd.ngayLapHD BETWEEN ? AND ? "
                        + "GROUP BY sp.maSP, sp.tenSP "
                        + "ORDER BY tongSoLuongBan ASC"; // Note: sort in ascending order
            }

            try (PreparedStatement p = connection.prepareStatement(sql)) {
                // Convert java.util.Date to java.sql.Date
                p.setDate(1, new java.sql.Date(startDate.getTime()));
                p.setDate(2, new java.sql.Date(endDate.getTime()));

                try (ResultSet r = p.executeQuery()) {
                    // Clear previous data from the chart
                    if (chart != null) {
                        chart.clear();
                    }

                    while (r.next()) {
                        String productName = r.getString("tenSP");
                        int totalSold = r.getInt("tongSoLuongBan");

                        // Add data to your model for the chart
                        ModelChart modelChart = new ModelChart(productName, new double[]{totalSold});
                        if (chart != null) {
                            chart.addData(modelChart);
                        }
                    }

                    // Refresh the chart to display new data
                    if (chart != null) {
                        chart.start();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Always close the connection in the 'finally' block to avoid leaks
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }




    private void themData() {
        Connection connection = null; // Khai báo biến kết nối
        try {
            java.util.List<ModelDataSP> lists = new ArrayList<>(); // Khởi tạo danh sách lưu dữ liệu
            connection = connectDB.accessDataBase(); // Lấy kết nối
            if (connection == null) {
                System.out.println("Cannot connect to the database.");
                return; // Dừng nếu không kết nối được
            }

            // Truy vấn SQL để lấy top 10 sản phẩm bán chạy nhất
            String sql = "SELECT TOP 10 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan "
                       + "FROM ChiTietHoaDon cthd "
                       + "JOIN SanPham sp ON cthd.maSP = sp.maSP "
                       + "GROUP BY sp.maSP, sp.tenSP "
                       + "ORDER BY tongSoLuongBan DESC";

            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                // Lấy tên sản phẩm và tổng số lượng bán ra từ kết quả
                String productName = r.getString("tenSP");
                int tongSoLuong = r.getInt("tongSoLuongBan");
                lists.add(new ModelDataSP(productName, tongSoLuong)); // Thêm dữ liệu vào danh sách
            }

            r.close();
            p.close();

            // Thêm dữ liệu vào biểu đồ
            chart.clear(); // Xóa dữ liệu cũ trong biểu đồ
            for (ModelDataSP d : lists) {
                chart.addData(new ModelChart(d.getTenSP(), new double[]{d.getTongSoLuong()})); 
            }

            // Bắt đầu hiển thị dữ liệu với hiệu ứng
            chart.start();
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi nếu có
        } finally {
            // Đảm bảo đóng kết nối nếu không null
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace(); // In lỗi nếu không đóng được kết nối
                }
            }
        }
    }
//    private void xuatExcel() {
//        // Lấy dữ liệu từ JDateChooser và JComboBox
//        Date startDate = dcNgayBatDau.getDate();
//        Date endDate = dcNgayKetThuc.getDate();
//        String selectedCategory = (String) cbSPBanChayBanCham.getSelectedItem();
//
//        // Thực hiện logic để lấy danh sách sản phẩm theo tiêu chí lọc
//        ArrayList<SanPham_entity> sanPhamList;
//		try {
//			sanPhamList = tk_Dao.layDanhSachSanPham(startDate, endDate, selectedCategory);
//	        // Tạo workbook và sheet
//	        XSSFWorkbook workbook = new XSSFWorkbook();
//	        XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");
//
//	        // Tạo tiêu đề cột
//	        XSSFRow headerRow = sheet.createRow(0);
//	        headerRow.createCell(0).setCellValue("Mã sản phẩm");
//	        headerRow.createCell(1).setCellValue("Tên sản phẩm");
//	        headerRow.createCell(2).setCellValue("Giá");
//	        headerRow.createCell(3).setCellValue("Ngày sản xuất");
//	        headerRow.createCell(4).setCellValue("Ngày hết hạn");
//	        headerRow.createCell(5).setCellValue("Khối lượng");
//	        headerRow.createCell(6).setCellValue("Đơn vị tính");
//	        headerRow.createCell(7).setCellValue("Nhà cung cấp");
//	        headerRow.createCell(8).setCellValue("Thành phần");
//	        headerRow.createCell(9).setCellValue("Công dụng");
//	        headerRow.createCell(10).setCellValue("Hình ảnh");
//	        
//	        // Thêm dữ liệu vào sheet
//	        int rowNum = 1;
//	        for (SanPham_entity sp : sanPhamList) {
//	            XSSFRow row = sheet.createRow(rowNum++);
//	            row.createCell(0).setCellValue(sp.getMaSP());
//	            row.createCell(1).setCellValue(sp.getTenSP());
//	            row.createCell(2).setCellValue(sp.getGia());
//	            row.createCell(3).setCellValue(sp.getNgaySanXuat().toString()); // Chuyển đổi LocalDate thành String
//	            row.createCell(4).setCellValue(sp.getNgayHetHan().toString());   // Chuyển đổi LocalDate thành String
//	            row.createCell(5).setCellValue(sp.getKhoiLuong());
//	            row.createCell(6).setCellValue(sp.getDonViTinh());
//	            row.createCell(7).setCellValue(sp.getNhaCungCap());
//	            row.createCell(8).setCellValue(sp.getThanhPhan());
//	            row.createCell(9).setCellValue(sp.getCongDung());
//	            row.createCell(10).setCellValue(sp.getHinhAnhSP());
//	            row.createCell(11).setCellValue(sp.getLoaiSanPham().getMaLoaiSP());
//	        }
//
//	        // Lưu file Excel
//	        try {
//	            FileOutputStream fileOut = new FileOutputStream("DanhSachSanPham.xlsx");
//	            workbook.write(fileOut);
//	            fileOut.close();
//	            workbook.close();
//	            JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
//	        } catch (IOException ex) {
//	            JOptionPane.showMessageDialog(null, "Lỗi xuất Excel: " + ex.getMessage());
//	        }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//    }
    private void xuatExcel() {
        // Lấy dữ liệu từ JDateChooser và JComboBox
        Date startDate = dcNgayBatDau.getDate();
        Date endDate = dcNgayKetThuc.getDate();
        String selectedCategory = (String) cbSPBanChayBanCham.getSelectedItem();

        // Thực hiện logic để lấy danh sách sản phẩm theo tiêu chí lọc
        ArrayList<SanPham_entity> sanPhamList;
        try {
        	
        	if (startDate == null && endDate == null) {
        		sanPhamList = tk_Dao.getAllSanPham();
            } else if (startDate != null && endDate == null) {
                MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Kết Thúc!", MessageAlerts.MessageType.ERROR);
                return;
            } else if (startDate == null && endDate != null) {
                MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Bắt Đầu!", MessageAlerts.MessageType.ERROR);
                return;
            } else {
            	sanPhamList = tk_Dao.layDanhSachSanPham(startDate, endDate, selectedCategory);
            }
            
            
            // Tạo workbook và sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");

            // Tạo tiêu đề cột
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã sản phẩm");
            headerRow.createCell(1).setCellValue("Tên sản phẩm");
            headerRow.createCell(2).setCellValue("Giá");
            headerRow.createCell(3).setCellValue("Ngày sản xuất");
            headerRow.createCell(4).setCellValue("Ngày hết hạn");
            headerRow.createCell(5).setCellValue("Khối lượng");
            headerRow.createCell(6).setCellValue("Đơn vị tính");
            headerRow.createCell(7).setCellValue("Nhà cung cấp");
            headerRow.createCell(8).setCellValue("Thành phần");
            headerRow.createCell(9).setCellValue("Công dụng");
            headerRow.createCell(10).setCellValue("Hình ảnh");
            headerRow.createCell(11).setCellValue("Loại Sản Phẩm");
            
            // Thêm dữ liệu vào sheet
            int rowNum = 1;
            for (SanPham_entity sp : sanPhamList) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(sp.getMaSP());
                row.createCell(1).setCellValue(sp.getTenSP());
                row.createCell(2).setCellValue(sp.getGia());
                row.createCell(3).setCellValue(sp.getNgaySanXuat().toString()); // Chuyển đổi LocalDate thành String
                row.createCell(4).setCellValue(sp.getNgayHetHan().toString());   // Chuyển đổi LocalDate thành String
                row.createCell(5).setCellValue(sp.getKhoiLuong());
                row.createCell(6).setCellValue(sp.getDonViTinh());
                row.createCell(7).setCellValue(sp.getNhaCungCap());
                row.createCell(8).setCellValue(sp.getThanhPhan());
                row.createCell(9).setCellValue(sp.getCongDung());
                row.createCell(10).setCellValue(sp.getHinhAnhSP());
                row.createCell(11).setCellValue(sp.getLoaiSanPham().getMaLoaiSP());
            }

            // Hiển thị JFileChooser để chọn vị trí lưu file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
            fileChooser.setSelectedFile(new File("DanhSachSanPham.xlsx")); // Đặt tên file mặc định

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi xuất Excel: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bạn đã hủy thao tác lưu file.");
            }

            try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Đảm bảo workbook được đóng

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu: " + e.getMessage());
        }
    }



}