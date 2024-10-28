package gui;

import javax.swing.JPanel;

import nguyenvu.components.SimpleForm;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class ThongKe_GUI extends SimpleForm {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ThongKe_GUI() {
		setPreferredSize(new Dimension(1500, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnCenter = new JPanel();
		add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 22, 438, 338);
		pnCenter.add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(22, 390, 1468, 400);
		pnCenter.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(515, 22, 438, 338);
		pnCenter.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(1014, 22, 438, 338);
		pnCenter.add(panel_3);

	}
}
