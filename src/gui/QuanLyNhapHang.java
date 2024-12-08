/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.NhapHang_DAO;
import entity.NhapHang_entity;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.RoundedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class QuanLyNhapHang extends SimpleForm {
    
   
    public QuanLyNhapHang() {
    	nhapHang_DAO = new NhapHang_DAO();
        initComponents();
    }
    private void initComponents() {
        pnHeader = new javax.swing.JPanel();
        pnHeader.setBounds(0, 0, 1470, 50);
        txtSearch = new RoundedTextField(40);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setBounds(20, 60, 248, 30);
        jButton7 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/print.svg", 0.35f));
        jButton7.setBounds(1202, 100, 120, 40);
        jButton8 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/export.svg", 0.3f));
        jButton8.setBounds(1340, 100, 120, 40);
        setPreferredSize(new java.awt.Dimension(1470, 730));
        pnHeader.setBackground(new java.awt.Color(11, 101, 136));
        pnHeader.setPreferredSize(new java.awt.Dimension(1470, 50));
        jButton1.setBackground(new java.awt.Color(11, 101, 136));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm kiếm");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton2.setBackground(new java.awt.Color(11, 101, 136));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Scan barcode");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeader.setLayout(pnHeaderLayout);
        pnHeaderLayout.setHorizontalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnHeaderLayout.setVerticalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Tìm sản phẩm");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(11, 101, 136));
        jLabel1.setText("Quản Lý Nhập Hàng");


        jButton7.setBackground(new java.awt.Color(255, 0, 0));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Lưu Tạm");

        jButton8.setBackground(new java.awt.Color(51, 204, 0));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Đã Thanh Toán");
        setLayout(null);
        add(jLabel1);
        add(jButton7);
        add(jButton8);
        add(pnHeader);
        
        pnTable = new JPanel();
        pnTable.setBounds(20, 100, 1172, 560);
        add(pnTable);
        pnTable.setLayout(null);
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBounds(0, 0, 1172, 491);
        pnTable.add(jScrollPane1);
        table = new javax.swing.JTable();
        
                table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
        
                    },
                    new String [] {
                        "Mã nhập hàng ", "Ngày nhập hàng", "Tổng tiền", "Phương thức thanh toán", "Trạng thái", "Thao tác"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false, false, true
                    };
        
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                table.setRowHeight(50);
                table.setRowSelectionAllowed(false);
                table.getTableHeader().setReorderingAllowed(false);
                jScrollPane1.setViewportView(table);
                jButton5 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/first-page.svg", 0.03f));
                jButton5.setBounds(10, 513, 60, 33);
                pnTable.add(jButton5);
                jButton3 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/prev.svg"));
                jButton3.setBounds(80, 513, 60, 33);
                pnTable.add(jButton3);
                jButton4 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/next.svg"));
                jButton4.setBounds(150, 513, 60, 33);
                pnTable.add(jButton4);
                jButton6 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/last-page.svg", 0.03f));
                jButton6.setBounds(220, 513, 60, 33);
                pnTable.add(jButton6);
                table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
                table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
                table.getTableHeader().setBackground(new Color(11,101,136));
                table.getTableHeader().setForeground(Color.WHITE);
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
                    table.getColumnModel().getColumn(4).setPreferredWidth(150);
                    table.getColumnModel().getColumn(5).setResizable(false);
                    table.getColumnModel().getColumn(5).setPreferredWidth(50);
                }
                        loadDataToTable();
        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
    }// </editor-fold>//GEN-END:initComponents


    private void loadDataToTable() {
        // Lấy mô hình của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<NhapHang_entity> list = nhapHang_DAO.getALLNHAPHANG();
        // Xóa dữ liệu cũ
        model.setRowCount(0);

        // Duyệt qua danh sách và thêm từng dòng vào bảng
        for (NhapHang_entity data : list) {
            model.addRow(new Object[] {data.getMaNhapHang(),data.getNgayNhapHang(),data.getTongTien(),data.getHinhThucThanhToan(),data.getTrangThai()});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    private JPanel pnTable;
    private NhapHang_DAO nhapHang_DAO;
    // End of variables declaration//GEN-END:variables
}
