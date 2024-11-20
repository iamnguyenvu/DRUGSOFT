
package gui;

import com.lowagie.text.List;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;
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
	private JButton btnTruyVan;
	private JPanel pnCenter;
	private JDateChooser dcNgayBatDau;
	private JDateChooser dcNgayKetThuc;
	private JButton btnXuatExcel;
	private ThongKe_DAO tk_Dao;

	/**
     * Creates new form Main
     */
    public ThongKeSoLuongGiaoDichCuaNhanVien() {
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
        panel_1.setBounds(0, 10, 1490, 137);
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
        lblNewLabel_2.setBounds(106, 72, 100, 34);
        panel_1.add(lblNewLabel_2);
        
        dcNgayBatDau = new JDateChooser();
        dcNgayBatDau.setDateFormatString("dd-MM-yyyy");
        dcNgayBatDau.setBackground(new Color(255, 255, 255));
        dcNgayBatDau.setBounds(227, 72, 296, 34);
        panel_1.add(dcNgayBatDau);
        
        JLabel lblNewLabel_2_1 = new JLabel("Ngày Kết Thúc");
        lblNewLabel_2_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_2_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_2_1.setBounds(549, 72, 100, 34);
        panel_1.add(lblNewLabel_2_1);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        dcNgayKetThuc = new JDateChooser();
        dcNgayKetThuc.setDateFormatString("dd-MM-yyyy");
        dcNgayKetThuc.setBackground(new Color(255, 255, 255));
        dcNgayKetThuc.setBounds(659, 72, 296, 34);
        dcNgayBatDau.setBackground(new Color(255, 255, 255));
        dcNgayBatDau.getDateEditor().getUiComponent().setBackground(new Color(255, 255, 255));
        dcNgayKetThuc.setBackground(new Color(255, 255, 255));
        dcNgayKetThuc.getDateEditor().getUiComponent().setBackground(new Color(255, 255, 255));
        
        dcNgayBatDau.setBorder(border);
        dcNgayKetThuc.setBorder(border);
        panel_1.add(dcNgayKetThuc);
        btnTruyVan = new javax.swing.JButton();
        btnTruyVan.setForeground(new Color(0, 0, 0));
        btnTruyVan.setBackground(new Color(255, 255, 255));
        btnTruyVan.setFont(new Font("Arial", Font.PLAIN, 15));
        btnTruyVan.setBounds(1162, 72, 115, 34);
        panel_1.add(btnTruyVan);
        
                btnTruyVan.setText("Truy Vấn");
                
                btnXuatExcel = new JButton("Xuất File");
                btnXuatExcel.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		try {
							xuatExcel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
                        chart.setBounds(0, 157, 1490, 494);
                        pnCenter.add(chart);
                        
                                chart.setFont(new Font("Arial", Font.PLAIN, 12));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Lấy giá trị từ JDateChooser
        java.util.Date startDate = dcNgayBatDau.getDate();
        java.util.Date endDate = dcNgayKetThuc.getDate();
        
        if (startDate == null && endDate == null) {
    		themData();
        } else if (startDate != null && endDate == null) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Kết Thúc!", MessageAlerts.MessageType.ERROR);
            return;
        } else if (startDate == null && endDate != null) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Bắt Đầu!", MessageAlerts.MessageType.ERROR);
            return;
        } else {
        	 themData(startDate, endDate);
        }
       
    }

    private void themData(java.util.Date startDate, java.util.Date endDate) {
        Connection connection = null; // Declare connection variable
        try {
            ArrayList<ModalDataSoLuongGiaoDich> lists = new ArrayList<>(); // Initialize a list to store data
            connection = connectDB.accessDataBase(); // Get database connection
            if (connection == null) {
                System.out.println("Cannot connect to the database.");
                return; // Stop if the connection fails
            }

            String sql = "SELECT TOP 10 nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich\r\n"
                		+ "FROM HoaDon hd\r\n"
                		+ "JOIN NhanVien nv ON hd.maNV = nv.maNV\r\n"
                		+ "WHERE hd.ngayLapHD BETWEEN ? AND ? "
                		+ "GROUP BY hd.maNV, nv.hotenNV\r\n"
                		+ "ORDER BY soLuongGiaoDich DESC;";

            PreparedStatement p = connection.prepareStatement(sql);
            // Convert java.util.Date to java.sql.Date
            p.setDate(1, new java.sql.Date(startDate.getTime()));
            p.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet r = p.executeQuery();

            // Clear previous data from the chart
            chart.clear();

            while (r.next()) {
                String tenNV = r.getString("hotenNV");
                int sldd = r.getInt("soLuongGiaoDich");

                // Add data to your model for the chart
                ModelChart modelChart = new ModelChart(tenNV, new double[]{sldd});
                chart.addData(modelChart);
            }
            chart.start();
            
            r.close(); // Close the ResultSet
            p.close(); // Close the PreparedStatement
        } catch (SQLException e) {
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
        ArrayList<ModalDataSoLuongGiaoDich> lists = new ArrayList<>(); // Danh sách để lưu dữ liệu từ CSDL
        try {
            connection = connectDB.accessDataBase(); // Lấy kết nối
            if (connection == null) {
                System.out.println("Cannot connect to the database.");
                return; // Dừng nếu không kết nối được
            }

            // Truy vấn SQL để lấy top 10 sản phẩm bán chạy nhất
            String sql = "SELECT TOP 10 nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich " +
                         "FROM HoaDon hd " +
                         "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                         "GROUP BY hd.maNV, nv.hotenNV " +
                         "ORDER BY soLuongGiaoDich DESC;";

            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet r = p.executeQuery();

            // Lấy dữ liệu từ ResultSet và lưu vào danh sách lists
            while (r.next()) {
                String tenNV = r.getString("hotenNV");
                int soLuongGiaoDich = r.getInt("soLuongGiaoDich");
                lists.add(new ModalDataSoLuongGiaoDich(tenNV, soLuongGiaoDich));
            }

            // Đóng ResultSet và PreparedStatement sau khi sử dụng
            r.close();
            p.close();

            // Xóa dữ liệu cũ trên biểu đồ và thêm dữ liệu mới từ danh sách lists
            chart.clear();
            for (ModalDataSoLuongGiaoDich data : lists) {
                ModelChart modelChart = new ModelChart(data.getHoTenNV(), new double[]{data.getSoLuongGiaoDich()});
                chart.addData(modelChart);
            }

            // Bắt đầu hiển thị dữ liệu với hiệu ứng
            chart.start();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng kết nối nếu không null
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void xuatExcel() throws SQLException {
        // Lấy dữ liệu từ JDateChooser và JComboBox
        Date startDate = dcNgayBatDau.getDate();
        Date endDate = dcNgayKetThuc.getDate();
        ArrayList<SoLuongGiaoDichNV> dsList;
        
        if (startDate == null && endDate == null) {
        	dsList = tk_Dao.laySoLuongGiaoDichNV();
        } else if (startDate != null && endDate == null) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Kết Thúc!", MessageAlerts.MessageType.ERROR);
            return;
        } else if (startDate == null && endDate != null) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Vui Lòng Nhập Vào Ngày Bắt Đầu!", MessageAlerts.MessageType.ERROR);
            return;
        } else {
        	dsList = tk_Dao.laySoLuongGiaoDichNV(startDate, endDate);
        }

        // Thực hiện logic để lấy danh sách sản phẩm theo tiêu chí lọc
        
		
		// Tạo workbook và sheet
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Danh sách Số Lượng Giao Dịch Của Nhân Viên");

		// Tạo tiêu đề cột
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Mã Nhân Viên");
		headerRow.createCell(1).setCellValue("Họ Tên Nhân Viên");
		headerRow.createCell(2).setCellValue("Số Lượng Giao Dịch");

		
		// Thêm dữ liệu vào sheet
		int rowNum = 1;
		for (SoLuongGiaoDichNV ds : dsList) {
		    XSSFRow row = sheet.createRow(rowNum++);
		    row.createCell(0).setCellValue(ds.getMaNV());
		    row.createCell(1).setCellValue(ds.getHotenNV());
		    row.createCell(2).setCellValue(ds.getSoLuongDD());
		    
		}

		// Hiển thị JFileChooser để chọn vị trí lưu file
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
		fileChooser.setSelectedFile(new File("ThongKeSoLuongGiaoDichCuaNhanVien.xlsx")); // Đặt tên file mặc định

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
    }



}