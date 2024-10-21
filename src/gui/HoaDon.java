package gui;

import javax.swing.JPanel;

import nguyenvu.components.SimpleForm;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

public class HoaDon extends SimpleForm {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HoaDon() {
		setPreferredSize(new Dimension(1500, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnContent = new JPanel();
		add(pnContent, BorderLayout.CENTER);
		pnContent.setLayout(new BorderLayout(0, 0));
		
		JPanel pnHeader = new JPanel();
		pnHeader.setBackground(new Color(255, 255, 255));
		pnHeader.setPreferredSize(new Dimension(1500, 70));
		pnContent.add(pnHeader, BorderLayout.NORTH);
		pnHeader.setLayout(null);

	}

}
