/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import dao.HoaDonDoiTra_DAO;
import entity.HoaDonDoiTra_entity;
import entity.NhapHang_entity;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import nguyenvu.components.SimpleForm;
import nguyenvu.utils.ButtonEditor;
import nguyenvu.utils.ButtonRenderer;
import nguyenvu.utils.EditViewButtonEditor;
import nguyenvu.utils.EditViewButtonRenderer;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.RoundedTextField;
import nguyenvu.utils.EditViewButtonEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;

/**
 *
 * @author HP
 */
public class HoaDonDoiTra extends SimpleForm {
    private HoaDonDoiTra_DAO hddt_DAO;
   
    public HoaDonDoiTra() {
    	hddt_DAO = new HoaDonDoiTra_DAO();
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHeader = new javax.swing.JPanel();
        txtSearch = new RoundedTextField(40);
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/prev.svg"));
        jButton4 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/next.svg"));
        jButton5 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/first-page.svg", 0.03f));
        jButton6 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/last-page.svg", 0.03f));

        setPreferredSize(new java.awt.Dimension(1470, 730));

        pnHeader.setBackground(new java.awt.Color(11, 101, 136));
        pnHeader.setPreferredSize(new java.awt.Dimension(1470, 50));

        jButton1.setBackground(new java.awt.Color(11, 101, 136));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm kiếm");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeaderLayout.setHorizontalGroup(
        	pnHeaderLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnHeaderLayout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(1004, Short.MAX_VALUE))
        );
        pnHeaderLayout.setVerticalGroup(
        	pnHeaderLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(pnHeaderLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(pnHeaderLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        				.addComponent(txtSearch, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        			.addContainerGap())
        );
        pnHeader.setLayout(pnHeaderLayout);

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Tìm sản phẩm");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(11, 101, 136));
        jLabel1.setText("Hóa đơn đổi trả");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đổi trả ", "Mã hóa đơn", "Ngày đổi trả", "Tiền trả lại", "Tiền khách trả thêm", "Thao tác"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(50);
        table.setRowSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(150);

        }
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        table.getTableHeader().setBackground(new Color(11,101,136));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getColumn("Thao tác").setCellRenderer(new ButtonRenderer("Xem"));
        table.getColumn("Thao tác").setCellEditor(new ButtonEditor("Xem", e -> {
        	try {
		        int row = Integer.parseInt(e.getActionCommand().split("_")[1]);
		        String maDT = (String) table.getValueAt(row, 0);
		        HoaDonDoiTra_entity hddt = hddt_DAO.timKiem(maDT);
		        
		        FormXemChiTietHoaDonDoiTra updateForm = new FormXemChiTietHoaDonDoiTra();
		        updateForm.AddDataToLable(hddt);

		        JDialog dialog = new JDialog();
		        dialog.getContentPane().add(updateForm);
		        dialog.setSize(1193, 680);
		        dialog.setLocationRelativeTo(null);
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
        }));
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "L\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        loadDataToTable();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(1229, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
        					.addGap(4))))
        		.addComponent(pnHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        			.addGap(25)
        			.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(1176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(pnHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jButton4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        						.addComponent(jButton5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(79))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        );
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Ngày Đổi Trả");
        lblNewLabel.setBounds(10, 40, 118, 32);
        panel.add(lblNewLabel);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(138, 46, 128, 26);
        panel.add(comboBox);
        this.setLayout(layout);

        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
    }
    
    private void loadDataToTable() {
        // Lấy mô hình của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<HoaDonDoiTra_entity> list = hddt_DAO.getALLHDDT();
        // Xóa dữ liệu cũ
        model.setRowCount(0);

        // Duyệt qua danh sách và thêm từng dòng vào bảng
        for (HoaDonDoiTra_entity data : list) {
            model.addRow(new Object[] {data.getMaDT(),data.getMaHD(),data.getNgayDoiTra(),data.getTienTraLai(),data.getTienKhachtraThem()});
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
}
