package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CapNhat_NV extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public CapNhat_NV() {
		setLayout(null);
		
		table = new JTable();
		table.setBackground(new Color(0, 128, 192));
		table.setBounds(45, 29, 727, 48);
		add(table);
		
		JButton btnNewButton = new JButton("Cập nhật");
		btnNewButton.setForeground(new Color(0, 128, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(0, 0, 160));
		btnNewButton.setBounds(107, 411, 95, 34);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.setBounds(329, 411, 114, 34);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Tìm kiếm");
		btnNewButton_2.setBounds(528, 411, 89, 34);
		add(btnNewButton_2);

	}
}
