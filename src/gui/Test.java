package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import connectDB.connectDB;
import dao.ThongKe_DAO;
import entity.ModelData;
import nguyenvu.utils.RoundedPanel;
import raven.chart.ModelChart;
import javax.swing.JLabel;
import java.awt.Font;

/**
 *
 * @author RAVEN
 */
public class Test extends JPanel {
	private ThongKe_DAO tk_DAO;

	public Test() {
		tk_DAO = new ThongKe_DAO();
		initComponents();
		setLayout(new BorderLayout(0, 0));
		panelShadow1.setLayout(null);
		chart.setTitle("VND");
		panelShadow1.add(chart);
		add(panelShadow1);
		
		RoundedPanel pnThocSapHetHan = new RoundedPanel(50);
		pnThocSapHetHan.setBackground(new Color(255, 146, 36));
		pnThocSapHetHan.setBounds(37, 25, 440, 243);
		panelShadow1.add(pnThocSapHetHan);
		pnThocSapHetHan.setLayout(null);
		
		JLabel lbThuocSapHetHan = new JLabel();
		FlatSVGIcon icon = new FlatSVGIcon("gui/icon/expired.svg", 120, 120);
		lbThuocSapHetHan.setIcon(icon);
		lbThuocSapHetHan.setBounds(20, 21, 207, 199);
		pnThocSapHetHan.add(lbThuocSapHetHan);
		
		JLabel lbsphethan = new JLabel("Tổng Số Sản Phẩm Sắp Hết Hạn:");
		lbsphethan.setForeground(new Color(0, 0, 0));
		lbsphethan.setFont(new Font("Arial", Font.PLAIN, 20));
		lbsphethan.setBounds(122, 41, 308, 85);
		pnThocSapHetHan.add(lbsphethan);
		
		
		JLabel slHetHan = new JLabel("");
		slHetHan.setForeground(new Color(0, 0, 0));
		slHetHan.setFont(new Font("Arial", Font.BOLD, 70));
		int soLuongSapHetHan = tk_DAO.soSanPhamSapHetHan();
		slHetHan.setText(soLuongSapHetHan+"");
		
		slHetHan.setBounds(215, 123, 126, 63);
		pnThocSapHetHan.add(slHetHan);
		
		RoundedPanel pnThuocSapHetHang = new RoundedPanel(50);
		pnThuocSapHetHang.setBackground(new Color(255, 66, 66));
		pnThuocSapHetHang.setBounds(536, 25, 440, 243);
		panelShadow1.add(pnThuocSapHetHang);
		pnThuocSapHetHang.setLayout(null);
		
		JLabel lbThuocSapHetHang = new JLabel();
		FlatSVGIcon icon1 = new FlatSVGIcon("gui/icon/out-of-stock.svg", 120, 120);
		lbThuocSapHetHang.setIcon(icon1);
		lbThuocSapHetHang.setBounds(10, 10, 204, 208);
		pnThuocSapHetHang.add(lbThuocSapHetHang);
		
		JLabel lbsphethang = new JLabel("Tổng Số Sản Phẩm Sắp Hết Hàng:");
		lbsphethang.setForeground(new Color(0, 0, 0));
		lbsphethang.setFont(new Font("Arial", Font.PLAIN, 20));
		lbsphethang.setBounds(124, 53, 316, 61);
		pnThuocSapHetHang.add(lbsphethang);
		
		JLabel slHetHang = new JLabel("");
		slHetHang.setForeground(new Color(0, 0, 0));
		slHetHang.setFont(new Font("Arial", Font.BOLD, 70));
		slHetHang.setBounds(204, 114, 119, 82);
		int soLuongSapHetHang = tk_DAO.soSanPhamSapHetHang();
		slHetHang.setText(soLuongSapHetHang+"");
		pnThuocSapHetHang.add(slHetHang);
		
		RoundedPanel pnDoanhThuThangNay = new RoundedPanel(50);
		pnDoanhThuThangNay.setBounds(1027, 25, 440, 243);
		pnDoanhThuThangNay.setBackground(new Color(101, 220, 109));
		panelShadow1.add(pnDoanhThuThangNay);
		pnDoanhThuThangNay.setLayout(null);
		
		JLabel lbDoanhThuThangNay = new JLabel();
		FlatSVGIcon icon2 = new FlatSVGIcon("gui/icon/turnover.svg", 120, 120);
		lbDoanhThuThangNay.setIcon(icon2);
		lbDoanhThuThangNay.setBounds(21, 21, 180, 212);
		pnDoanhThuThangNay.add(lbDoanhThuThangNay);
		
		JLabel lbddtn = new JLabel("Doanh Thu Tháng Hiện Tại:");
		lbddtn.setForeground(new Color(0, 0, 0));
		lbddtn.setFont(new Font("Arial", Font.BOLD, 20));
		lbddtn.setBounds(154, 50, 263, 61);
		pnDoanhThuThangNay.add(lbddtn);
		
		JLabel slTongDTT = new JLabel("");
		slTongDTT.setForeground(new Color(0, 0, 0));
		int tongDTTThangNay = tk_DAO.doangThuThangNay();
		slTongDTT.setText(tongDTTThangNay+ "VNĐ");
		slTongDTT.setFont(new Font("Arial", Font.BOLD, 42));
		
		slTongDTT.setBounds(164, 111, 257, 80);
		pnDoanhThuThangNay.add(slTongDTT);
		
		JLabel lbMainTiTle = new JLabel("Doanh Số Bán Hàng Theo Tháng");
		lbMainTiTle.setForeground(new Color(0, 255, 255));
		lbMainTiTle.setFont(new Font("Arial", Font.BOLD, 25));
		lbMainTiTle.setBounds(561, 282, 390, 54);
		panelShadow1.add(lbMainTiTle);
		
		chart.addLegend("", Color.decode("#7b4397"), Color.decode("#dc2430"));
		setData();
	}

	private void setData() {
		Connection connection = null; // Khai báo biến kết nối
		try {
			List<ModelData> lists = new ArrayList<>(); // Khởi tạo danh sách lưu dữ liệu
			connection = connectDB.accessDataBase(); // Lấy kết nối
			if (connection == null) {
				System.out.println("Cannot connect to the database.");
				return; // Dừng nếu không kết nối được
			}

			String sql = "SELECT DATEPART(MONTH, ngayLapHD) AS Month, \r\n"
					+ "       SUM(tongTien - tienGiam) AS TotalAmount         \r\n"
					+ "FROM HoaDon\r\n"
					+ "WHERE DATEPART(YEAR, ngayLapHD) = 2024     \r\n"
					+ "GROUP BY DATEPART(MONTH, ngayLapHD)        \r\n"
					+ "ORDER BY Month ASC;";

			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet r = p.executeQuery();

			while (r.next()) {
				// Lấy tên tháng và tổng số tiền từ kết quả
				String month = r.getString("Month");
				double amount = r.getDouble("TotalAmount");
				lists.add(new ModelData("Tháng " + month, amount)); // Thêm dữ liệu vào danh sách
			}

			r.close();
			p.close();

			// Thêm dữ liệu vào biểu đồ
			for (ModelData d : lists) {
				chart.addData(new ModelChart(d.getMonth(), new double[] { d.getTotal() }));
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

	private void initComponents() {
		panelShadow1 = new raven.panel.PanelShadow();
		chart = new raven.chart.CurveLineChart();
		chart.setBounds(10, 296, 1480, 494);

		panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
		panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));

		chart.setForeground(new java.awt.Color(237, 237, 237));
		chart.setFillColor(true);

		setPreferredSize(new Dimension(1500, 800)); // Đặt kích thước ưu tiên
	}

	// Các biến thành viên của lớp
	private raven.chart.CurveLineChart chart;
	private raven.panel.PanelShadow panelShadow1;
}
