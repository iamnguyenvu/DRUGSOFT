/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import com.toedter.calendar.JDateChooser;
import dao.BaoCaoDoanhThu_DAO;
import dao.BaoCaoSanPhamBanChay_DAO;

import dao.SanPhamDoiTra_DAO;
import entity.BaoCaoDoanhThu_enity;
import entity.BaoCaoSanPhamBanChay_enity;
import entity.SanPham_entity;
import groovy.model.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nguyenvu.components.SimpleForm;
import nguyenvu.utils.ButtonEditor;
import nguyenvu.utils.ButtonRenderer;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.RoundedTextField;

/**
 *
 * @author HP
 */
public class BaoCaoSanPham_GUI extends SimpleForm {
   
	private int currentPage = 1;
        private int rowsPerPage = 10;
        private int totalPages = 0;
        private BaoCaoSanPhamBanChay_DAO dao;
        private JDialog dateDialog;
        private Date selectedStartDate = null;
        private Date selectedEndDate = null;
        private BaoCaoSanPhamBanChay_DAO banChay;
        
        private List<BaoCaoSanPhamBanChay_enity> reportData;
        
        private LocalDate ngayHienTai = LocalDate.now();
        private LocalDate bayNgayTruoc = ngayHienTai.minusDays(7);
        private LocalDate bamuoiNgayTruoc = ngayHienTai.minusDays(30);
        private LocalDate chinmuoiNgayTruoc = ngayHienTai.minusDays(90);
        private LocalDate basaunamngaytruoc = ngayHienTai.minusDays(365);
        
        private BaoCaoSanPham_Panel banChay0 ;
        private BaoCaoSanPham_Panel banChay7 ;
        private BaoCaoSanPham_Panel banChay30 ;
        private BaoCaoSanPham_Panel banChay90 ;
        private BaoCaoSanPham_Panel banChay365 ;
        private BaoCaoSanPham_Panel banChayTuyChinh ;
        
        private BaoCaoSanPhamBanCham_Panel banCham0;
        private BaoCaoSanPhamBanCham_Panel banCham7;
        private BaoCaoSanPhamBanCham_Panel banCham30;
        private BaoCaoSanPhamBanCham_Panel banCham90;
        private BaoCaoSanPhamBanCham_Panel banCham365;
        private BaoCaoSanPhamBanCham_Panel banChamTuyChinh;
        
        private Panel_BaoCaoSanPhamHetHan sanPhamHetHan;
        
        private Panel_BaoCaoSanPhamHetHang sanPhamHetHang;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat formatterForDate = new SimpleDateFormat("dd/MM/yyyy");
    
	public BaoCaoSanPham_GUI() {
	    initComponents();
            radioTrangThaiSanPham();
            pnMain.removeAll();
            banChay7 = new BaoCaoSanPham_Panel(7);
            pnMain.add(banChay7);
            pnMain.revalidate();
            pnMain.repaint();
	}

        private void radioTrangThaiSanPham(){
            ButtonGroup trangThaiSanPham = new ButtonGroup();
            trangThaiSanPham.add(rSanPhamBanChay);
            trangThaiSanPham.add(rSanPhamBanCham);
            trangThaiSanPham.add(rSanPhamHetHan);
            trangThaiSanPham.add(rSanPhamSapHetHang);
            rSanPhamBanChay.setSelected(true);
        }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        xuatPDF = new javax.swing.JButton(new FlatSVGIcon("gui/icon/print.svg", 0.35f));
        xuatExcel = new javax.swing.JButton(new FlatSVGIcon("gui/icon/export.svg", 0.3f));
        jPanel1 = new javax.swing.JPanel();
        rSanPhamBanCham = new javax.swing.JRadioButton();
        rSanPhamHetHan = new javax.swing.JRadioButton();
        rSanPhamSapHetHang = new javax.swing.JRadioButton();
        rSanPhamBanChay = new javax.swing.JRadioButton();
        cbTime = new javax.swing.JComboBox<>();
        pnMain = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1470, 730));

        pnHeader.setBackground(new java.awt.Color(11, 101, 136));
        pnHeader.setPreferredSize(new java.awt.Dimension(1470, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Báo cáo sản phẩm");

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeader.setLayout(pnHeaderLayout);
        pnHeaderLayout.setHorizontalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnHeaderLayout.setVerticalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHeaderLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        xuatPDF.setBackground(new java.awt.Color(255, 0, 0));
        xuatPDF.setForeground(new java.awt.Color(255, 255, 255));
        xuatPDF.setText("Xuất PDF");
        xuatPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatPDFActionPerformed(evt);
            }
        });

        xuatExcel.setBackground(new java.awt.Color(51, 204, 0));
        xuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        xuatExcel.setText("Xuất File");
        xuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatExcelActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Trạng thái sản phẩm"));

        rSanPhamBanCham.setText("Sản phẩm bán chậm");
        rSanPhamBanCham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSanPhamBanChamActionPerformed(evt);
            }
        });

        rSanPhamHetHan.setText("Sản phẩm hết hạn");
        rSanPhamHetHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSanPhamHetHanActionPerformed(evt);
            }
        });

        rSanPhamSapHetHang.setText("Sản phẩm hết hàng");
        rSanPhamSapHetHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSanPhamSapHetHangActionPerformed(evt);
            }
        });

        rSanPhamBanChay.setText("Sản phẩm bán chạy");
        rSanPhamBanChay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSanPhamBanChayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSanPhamBanCham)
                    .addComponent(rSanPhamHetHan)
                    .addComponent(rSanPhamSapHetHang)
                    .addComponent(rSanPhamBanChay))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(rSanPhamBanChay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSanPhamBanCham, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSanPhamHetHan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSanPhamSapHetHang)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        cbTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7 Ngày Qua", "30 Ngày Qua", "90 Ngày Qua", "365 Ngày Qua", "Toàn Thời Gian", "Tùy Chỉnh" }));
        cbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimeActionPerformed(evt);
            }
        });

        pnMain.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 1460, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(xuatPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xuatPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 367, Short.MAX_VALUE))
                    .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
    }// </editor-fold>//GEN-END:initComponents

    private void cbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimeActionPerformed
        String selected = cbTime.getSelectedItem().toString().trim();
        pnMain.removeAll();
        if (rSanPhamBanChay.isSelected()) {
            if (selected.equals("Toàn Thời Gian")) {
                pnMain.removeAll();
                banChay0 = new BaoCaoSanPham_Panel(0);
                pnMain.add(banChay0);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("7 Ngày Qua")){
                pnMain.removeAll();
                banChay7 = new BaoCaoSanPham_Panel(7);
                pnMain.add(banChay7);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("30 Ngày Qua")){
                pnMain.removeAll();
                banChay30 = new BaoCaoSanPham_Panel(30);
                pnMain.add(banChay30);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("90 Ngày Qua")){
                pnMain.removeAll();
                banChay90 = new BaoCaoSanPham_Panel(90);
                pnMain.add(banChay90);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("365 Ngày Qua")){
                pnMain.removeAll();
                banChay365 = new BaoCaoSanPham_Panel(365);
                pnMain.add(banChay365);
                pnMain.revalidate();
                pnMain.repaint();
            }else{
                pnMain.removeAll();
                banChayTuyChinh = new BaoCaoSanPham_Panel(10);
                pnMain.add(banChayTuyChinh);
                pnMain.revalidate();
                pnMain.repaint();
            }

            
        } else if(rSanPhamBanCham.isSelected()){
            if (selected.equals("Toàn Thời Gian")) {
                pnMain.removeAll();
                banCham0 = new BaoCaoSanPhamBanCham_Panel(0);
                pnMain.add(banCham0);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("7 Ngày Qua")){
                pnMain.removeAll();
                banCham7 = new BaoCaoSanPhamBanCham_Panel(7);
                pnMain.add(banCham7);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("30 Ngày Qua")){
                pnMain.removeAll();
                banCham30 = new BaoCaoSanPhamBanCham_Panel(30);
                pnMain.add(banCham30);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("90 Ngày Qua")){
                pnMain.removeAll();
                banCham90 = new BaoCaoSanPhamBanCham_Panel(90);
                pnMain.add(banCham90);
                pnMain.revalidate();
                pnMain.repaint();
            }
            else if(selected.equals("365 Ngày Qua")){
                pnMain.removeAll();
                banCham365 = new BaoCaoSanPhamBanCham_Panel(365);
                pnMain.add(banCham365);
                pnMain.revalidate();
                pnMain.repaint();
            }else{
                pnMain.removeAll();
                banChamTuyChinh = new BaoCaoSanPhamBanCham_Panel(10);
                pnMain.add(banChamTuyChinh);
                pnMain.revalidate();
                pnMain.repaint();
            }
        }
        
        
    }//GEN-LAST:event_cbTimeActionPerformed

    private void rSanPhamBanChayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSanPhamBanChayActionPerformed
        cbTime.setVisible(true);
        pnMain.removeAll();
                banChay0 = new BaoCaoSanPham_Panel(0);
                pnMain.add(banChay0);
                pnMain.revalidate();
                pnMain.repaint();
    }//GEN-LAST:event_rSanPhamBanChayActionPerformed
        
    private void rSanPhamBanChamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSanPhamBanChamActionPerformed
        cbTime.setVisible(true);
        pnMain.removeAll();
                banCham7 = new BaoCaoSanPhamBanCham_Panel(7);
                pnMain.add(banCham7);
                pnMain.revalidate();
                pnMain.repaint();
    }//GEN-LAST:event_rSanPhamBanChamActionPerformed

    private void rSanPhamHetHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSanPhamHetHanActionPerformed
        pnMain.removeAll();
        sanPhamHetHan = new Panel_BaoCaoSanPhamHetHan();
        pnMain.add(sanPhamHetHan);
        pnMain.revalidate();
        pnMain.repaint();
        cbTime.setVisible(false);
    }//GEN-LAST:event_rSanPhamHetHanActionPerformed

    private void rSanPhamSapHetHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSanPhamSapHetHangActionPerformed
        pnMain.removeAll();
        sanPhamHetHang = new Panel_BaoCaoSanPhamHetHang();
        pnMain.add(sanPhamHetHang);
        pnMain.revalidate();
        pnMain.repaint();
        cbTime.setVisible(false);
    }//GEN-LAST:event_rSanPhamSapHetHangActionPerformed

    private void xuatPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatPDFActionPerformed
         String selected = cbTime.getSelectedItem().toString().trim();
        if (rSanPhamBanChay.isSelected()) {
            if (selected.equals("Toàn Thời Gian")) {
                banChay0.xuatPDF();
            }
            else if(selected.equals("7 Ngày Qua")){
                banChay7.xuatPDF();
            }
            else if(selected.equals("30 Ngày Qua")){
                banChay30.xuatPDF();
            }
            else if(selected.equals("90 Ngày Qua")){
                banChay90.xuatPDF();
            }
            else if(selected.equals("365 Ngày Qua")){
                banChay365.xuatPDF();
            }else{
                banChayTuyChinh.xuatPDF();
            }  
        }else if(rSanPhamBanCham.isSelected()){
            if (selected.equals("Toàn Thời Gian")) {
                banCham0.xuatPDF();
            }
            else if(selected.equals("7 Ngày Qua")){
                banCham7.xuatPDF();
            }
            else if(selected.equals("30 Ngày Qua")){
                banCham30.xuatPDF();
            }
            else if(selected.equals("90 Ngày Qua")){
                banCham90.xuatPDF();
            }
            else if(selected.equals("365 Ngày Qua")){
                banCham365.xuatPDF();
            }else{
                banChamTuyChinh.xuatPDF();
            }
        }else if(rSanPhamHetHan.isSelected()){
            sanPhamHetHan.xuatPDF();
        } else if(rSanPhamSapHetHang.isSelected()){
            sanPhamHetHang.xuatPDF();
        }
        
    }//GEN-LAST:event_xuatPDFActionPerformed

    private void xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatExcelActionPerformed
         String selected = cbTime.getSelectedItem().toString().trim();
        if (rSanPhamBanChay.isSelected()) {
            if (selected.equals("Toàn Thời Gian")) {
                banChay0.xuatExcel();
            }
            else if(selected.equals("7 Ngày Qua")){
                banChay7.xuatExcel();
            }
            else if(selected.equals("30 Ngày Qua")){
                banChay30.xuatExcel();
            }
            else if(selected.equals("90 Ngày Qua")){
                banChay90.xuatExcel();
            }
            else if(selected.equals("365 Ngày Qua")){
                banChay365.xuatExcel();
            }else{
                banChayTuyChinh.xuatExcel();
            }  
        }else if(rSanPhamBanCham.isSelected()){
            if (selected.equals("Toàn Thời Gian")) {
                banCham0.xuatExcel();
            }
            else if(selected.equals("7 Ngày Qua")){
                banCham7.xuatExcel();
            }
            else if(selected.equals("30 Ngày Qua")){
                banCham30.xuatExcel();
            }
            else if(selected.equals("90 Ngày Qua")){
                banCham90.xuatExcel();
            }
            else if(selected.equals("365 Ngày Qua")){
                banCham365.xuatExcel();
            }else{
                banChamTuyChinh.xuatExcel();
            }
        }else if(rSanPhamHetHan.isSelected()){
            sanPhamHetHan.xuatExcel();
        } else if(rSanPhamSapHetHang.isSelected()){
            sanPhamHetHang.xuatExcel();
        }
    }//GEN-LAST:event_xuatExcelActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JPanel pnMain;
    private javax.swing.JRadioButton rSanPhamBanCham;
    private javax.swing.JRadioButton rSanPhamBanChay;
    private javax.swing.JRadioButton rSanPhamHetHan;
    private javax.swing.JRadioButton rSanPhamSapHetHang;
    private javax.swing.JButton xuatExcel;
    private javax.swing.JButton xuatPDF;
    // End of variables declaration//GEN-END:variables
}
