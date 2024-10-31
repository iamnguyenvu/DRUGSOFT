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

import connectDB.connectDB;
import entity.ModelData;
import raven.chart.ModelChart;

/**
 *
 * @author RAVEN
 */
public class Test extends JPanel {

	public Test() {
		initComponents();
		setLayout(new BorderLayout(0, 0));
		panelShadow1.setLayout(null);
		chart.setTitle("Chart Data");
		panelShadow1.add(chart);
		add(panelShadow1);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 25, 422, 306);
		panelShadow1.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(524, 25, 422, 306);
		panelShadow1.add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(1013, 25, 422, 306);
		panelShadow1.add(panel_1_1);
		chart.addLegend("Doanh Thu Theo Tháng", Color.decode("#7b4397"), Color.decode("#dc2430"));
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

			String sql = "SELECT DATEPART(MONTH, ngayLapHD) AS Month, SUM(tongTien) AS TotalAmount " + "FROM HoaDon "
					+ "WHERE DATEPART(YEAR, ngayLapHD) = 2024 " + "GROUP BY DATEPART(MONTH, ngayLapHD) "
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
		chart.setBounds(10, 341, 1480, 449);

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
