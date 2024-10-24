package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import nguyenvu.components.SimpleForm;

public class ManHinhNen extends SimpleForm {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ManHinhNen() {
		setPreferredSize(new Dimension(1500, 800)); // Đặt kích thước ưu tiên cho ManHinhNen
		setLayout(new BorderLayout(0, 0)); // Thiết lập layout BorderLayout

		// Tạo một đối tượng Test và thêm vào panel chính
		Test testPanel = new Test(); // Tạo đối tượng Test

		// Thêm testPanel vào ManHinhNen
		add(testPanel, BorderLayout.CENTER); // Thêm vào giữa layout

		// Kích thước ưu tiên cho testPanel
		testPanel.setPreferredSize(new Dimension(1500, 800)); // Đặt kích thước cho Test

		// Không cần gọi lại hàm loadData() ở đây vì đã gọi trong Test
	}
}
