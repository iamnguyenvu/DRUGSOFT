package gui;

import javax.swing.JPanel;

import nguyenvu.components.SimpleForm;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

public class ManHinhNen extends SimpleForm {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ManHinhNen() {
		setPreferredSize(new Dimension(1500, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnContent = new JPanel();
		add(pnContent, BorderLayout.CENTER);
		pnContent.setLayout(null);
		
		JPanel pnHeader = new JPanel();
		pnHeader.setBackground(new Color(255, 255, 255));
		pnHeader.setBounds(0, 0, 1500, 287);
		pnContent.add(pnHeader);
		pnHeader.setLayout(null);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(new Color(37, 61, 63));
		pnCenter.setBounds(0, 297, 1500, 503);
		pnContent.add(pnCenter);
		pnCenter.setLayout(null);

	}

}
