package gui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;

import nguyenvu.components.SimpleForm;
import nguyenvu.utils.RoundedPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JDateChooser;

import groovy.transform.stc.FirstParam.Component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.CardLayout;

public class ThongKeSanPham_GUI extends SimpleForm implements ActionListener{

    private static final long serialVersionUID = 1L;
	private JPanel pnChart;
	private JComboBox cb_SelectedTime;
	private JRadioButton btnSPBanChay;
	private JRadioButton btnSPBanCham;
	private JRadioButton btnSPHetHan;
	private JRadioButton btnSPHetHang;

    /**
     * Create the panel.
     */
    public ThongKeSanPham_GUI() {
        setPreferredSize(new Dimension(1470, 730));
        setLayout(new BorderLayout(0, 0));
        
        JPanel pnCenter = new JPanel();
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        
        JPanel pnHeading = new JPanel();
        pnHeading.setBackground(new Color(11, 101, 136));
        pnHeading.setBounds(0, 0, 1470, 45);
        pnCenter.add(pnHeading);
        pnHeading.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Thống Kê Doanh Thu");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 18));
        lblNewLabel_1.setBounds(10, 10, 231, 25);
        pnHeading.add(lblNewLabel_1);
        
        pnChart = new JPanel();
        pnChart.setBackground(Color.WHITE);
        pnChart.setBounds(0, 55, 1117, 668);
        pnCenter.add(pnChart);
        
        JPanel pnController = new JPanel();
        pnController.setLayout(null);
        pnController.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        pnController.setBounds(1138, 60, 322, 517);
        pnCenter.add(pnController);
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 196, 297, 287);
        pnController.add(panel);
        
        btnSPBanChay = new JRadioButton("Sản Phẩm Bán Chạy");
        btnSPBanChay.setHorizontalAlignment(SwingConstants.LEFT);
        btnSPBanChay.setBounds(10, 20, 281, 54);
        btnSPBanChay.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.setLayout(null);
        panel.add(btnSPBanChay);
        
        btnSPBanCham = new JRadioButton("Sản Phẩm Bán Chậm");
        btnSPBanCham.setHorizontalAlignment(SwingConstants.LEFT);
        btnSPBanCham.setBounds(10, 84, 281, 54);
        btnSPBanCham.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(btnSPBanCham);
        
        btnSPHetHan = new JRadioButton("Sản Phẩm Hết Hạn");
        btnSPHetHan.setHorizontalAlignment(SwingConstants.LEFT);

        btnSPHetHan.setBounds(10, 148, 281, 54);
        btnSPHetHan.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(btnSPHetHan);
        
        btnSPHetHang = new JRadioButton("Sản Phẩm Hết Hàng");
        btnSPHetHang.setHorizontalAlignment(SwingConstants.LEFT);
        btnSPHetHang.setBounds(10, 212, 281, 54);
        btnSPHetHang.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(btnSPHetHang);
        
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnSPBanChay);
        btnGroup.add(btnSPBanCham);
        btnGroup.add(btnSPHetHan);
        btnGroup.add(btnSPHetHang);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.setBackground(new Color(11, 101, 136));
        panel_2.setBounds(10, 110, 297, 75);
        pnController.add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Mối Quan Tâm");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
        panel_2.add(lblNewLabel);
        
        cb_SelectedTime = new JComboBox();
        cb_SelectedTime.addItem("7 Ngày Qua");
        cb_SelectedTime.addItem("30 Ngày Qua");
        cb_SelectedTime.addItem("90 Ngày Qua");
        cb_SelectedTime.addItem("365 Ngày Qua");
        cb_SelectedTime.addItem("Toàn Thời Gian");
        cb_SelectedTime.addItem("2024");
        cb_SelectedTime.addItem("2023");
        cb_SelectedTime.setFont(new Font("Serif", Font.PLAIN, 17));
        cb_SelectedTime.setBounds(10, 10, 297, 78);
        pnController.add(cb_SelectedTime);
        
        cb_SelectedTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn trong JComboBox cb_SelectedTime
                String selectedTime = cb_SelectedTime.getSelectedItem().toString();

                // Xóa các thành phần cũ trong pnChart
                pnChart.removeAll();

                // Kiểm tra xem radio nào được chọn và cập nhật giao diện tương ứng
                if (btnSPBanChay.isSelected()) {
                	if (selectedTime.equals("7 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanChay(7));
                	} else if (selectedTime.equals("30 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanChay(30));
                	} else if (selectedTime.equals("90 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanChay(90));
                	} else if (selectedTime.equals("365 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanChay(365));
                	} else if (selectedTime.equals("Toàn Thời Gian")) {
                	    pnChart.add(new ThongKeSPBanChay(0));
                	} else if (selectedTime.equals("2024")) {
                	    pnChart.add(new ThongKeSPBanChay(2024));
                	} else if (selectedTime.equals("2023")) {
                	    pnChart.add(new ThongKeSPBanChay(2023));
                	}
//                    else if (selectedTime.equals("Tùy Chỉnh")) {
//                    	showCustomDateRange();
//                    }
                } 
                else if (btnSPBanCham.isSelected()) {
                    if (selectedTime.equals("7 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanCham(7));
                	} else if (selectedTime.equals("30 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanCham(30));
                	} else if (selectedTime.equals("90 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanCham(90));
                	} else if (selectedTime.equals("365 Ngày Qua")) {
                	    pnChart.add(new ThongKeSPBanCham(365));
                	} else if (selectedTime.equals("Toàn Thời Gian")) {
                	    pnChart.add(new ThongKeSPBanCham(0));
                	} else if (selectedTime.equals("2024")) {
                	    pnChart.add(new ThongKeSPBanCham(2024));
                	} else if (selectedTime.equals("2023")) {
                	    pnChart.add(new ThongKeSPBanCham(2023));
                	}
                }

                // Cập nhật lại giao diện
                pnChart.revalidate();
                pnChart.repaint();
            }
        });

        // Thêm ActionListener cho radio_DoanhSoBHTQ
        btnSPBanChay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cb_SelectedTime.setVisible(true);
            	String selectedTime = cb_SelectedTime.getSelectedItem().toString();
                pnChart.removeAll();
                if (selectedTime.equals("7 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanChay(7));
            	} else if (selectedTime.equals("30 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanChay(30));
            	} else if (selectedTime.equals("90 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanChay(90));
            	} else if (selectedTime.equals("365 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanChay(365));
            	} else if (selectedTime.equals("Toàn Thời Gian")) {
            	    pnChart.add(new ThongKeSPBanChay(0));
            	} else if (selectedTime.equals("2024")) {
            	    pnChart.add(new ThongKeSPBanChay(2024));
            	} else if (selectedTime.equals("2023")) {
            	    pnChart.add(new ThongKeSPBanChay(2023));
            	}

                // Cập nhật lại giao diện
                pnChart.revalidate();
                pnChart.repaint();
            }
        });
        btnSPBanCham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnChart.removeAll();
                cb_SelectedTime.setVisible(true);
                String selectedTime = cb_SelectedTime.getSelectedItem().toString();
                if (selectedTime.equals("7 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanCham(7));
            	} else if (selectedTime.equals("30 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanCham(30));
            	} else if (selectedTime.equals("90 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanCham(90));
            	} else if (selectedTime.equals("365 Ngày Qua")) {
            	    pnChart.add(new ThongKeSPBanCham(365));
            	} else if (selectedTime.equals("Toàn Thời Gian")) {
            	    pnChart.add(new ThongKeSPBanCham(0));
            	} else if (selectedTime.equals("2024")) {
            	    pnChart.add(new ThongKeSPBanCham(2024));
            	} else if (selectedTime.equals("2023")) {
            	    pnChart.add(new ThongKeSPBanCham(2023));
            	}
                // Cập nhật lại giao diện
                pnChart.revalidate();
                pnChart.repaint();
            }
        });
        btnSPHetHan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnChart.removeAll();
                cb_SelectedTime.setVisible(false);
            		pnChart.add(new pnSanPhamHetHan());
                // Cập nhật lại giao diện
                pnChart.revalidate();
                pnChart.repaint();
            }
        });
        btnSPHetHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnChart.removeAll();
                cb_SelectedTime.setVisible(false);
            		pnChart.add(new pnSanPhamHetHan());
                // Cập nhật lại giao diện
                pnChart.revalidate();
                pnChart.repaint();
            }
        });
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
//	private void showCustomDateRange() {
//	    // Tạo một panel để chứa JDateChooser và nút Xác Nhận
//	    JPanel datePanel = new JPanel();
//	    datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
//
//	    // Tạo JPanel chứa JLabel và căn trái
//	    JPanel panelStartDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
//	    JLabel nbd = new JLabel("Ngày Bắt Đầu:");
//	    panelStartDate.add(nbd);
//	    datePanel.add(panelStartDate);
//
//	    // Tạo và thêm JDateChooser cho ngày bắt đầu
//	    JDateChooser dateChooserStart = new JDateChooser();
//	    datePanel.add(dateChooserStart);
//
//	    // Tạo JPanel chứa JLabel và căn trái
//	    JPanel panelEndDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
//	    JLabel nkt = new JLabel("Ngày Kết Thúc:");
//	    panelEndDate.add(nkt);
//	    datePanel.add(panelEndDate);
//
//	    // Tạo và thêm JDateChooser cho ngày kết thúc
//	    JDateChooser dateChooserEnd = new JDateChooser();
//	    datePanel.add(dateChooserEnd);
//
//	    // Tạo JPanel cho nút xác nhận và căn phải
//	    JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//	    JButton btnConfirm = new JButton("Xác Nhận");
//	    panelButton.add(btnConfirm);
//	    datePanel.add(panelButton);
//
//	    // Đặt màu nền cho panel là tím nhạt
//	    datePanel.setBackground(new Color(240, 240, 240));
//
//	    // Hộp thoại tùy chỉnh JOptionPane
//	    JDialog dialog = new JDialog((Frame) null, "Chọn Ngày", true);
//	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//	    dialog.getContentPane().add(datePanel);
//	    dialog.pack();
//	    dialog.setSize(250,200);
//	    dialog.setLocationRelativeTo(null);
//
//	    btnConfirm.addActionListener(new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            java.util.Date startDate = dateChooserStart.getDate();
//	            java.util.Date endDate = dateChooserEnd.getDate();
//
//	            if (startDate != null && endDate != null) {
//	                if (startDate.after(endDate)) {
//	                    JOptionPane.showMessageDialog(dialog, "Ngày bắt đầu không được sau ngày kết thúc.");
//	                    return;
//	                }
//
//	                // Định dạng ngày sang chuỗi dd/MM/yyyy
//	                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//	                String formattedStartDate = dateFormat.format(startDate);
//	                String formattedEndDate = dateFormat.format(endDate);
//
//	                String customDateRange = formattedStartDate + " - " + formattedEndDate;
//
//	                cb_SelectedTime.setEditable(true);
//	                cb_SelectedTime.setSelectedItem(customDateRange);
//	                cb_SelectedTime.setEditable(false);
//
//	                pnChart.add(new DoanhSoBanHang(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime())));
//
//	                dialog.dispose();
//	            } else {
//	                JOptionPane.showMessageDialog(dialog, "Vui lòng chọn đầy đủ ngày bắt đầu và kết thúc.");
//	            }
//	        }
//	    });
//	    dialog.setVisible(true);
//	}
}
