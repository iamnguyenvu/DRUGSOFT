/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.TrangChu_DAO;
import gui.components.LastTransactionPanel;
import gui.components.SellTransactionPanel;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import nguyenvu.components.SimpleForm;
import nguyenvu.menu.FormManager;
import nguyenvu.model.ModelCard;
import nguyenvu.model.ModelSellTransaction;
import nguyenvu.model.ModelTransaction;
import nguyenvu.model.ModelUser;

/**
 *
 * @author HP
 */
public class TrangChu extends SimpleForm {
    private ModelUser user;
    
    public TrangChu(ModelUser user) {
        this.user = user;
        initComponents();
        initCardData();
        initTransactionData();
    }
    
    private void initCardData() {
        card1.setData(new ModelCard("Sản phẩm sắp hết hàng: ", TrangChu_DAO.getListSPSapHetHang().size(), new FlatSVGIcon("gui/icon/out-of-stock.svg", 0.1f)));
        card2.setData(new ModelCard("Sản phẩm sắp hết hạn: ", TrangChu_DAO.getListSPSapHetHan().size(), new FlatSVGIcon("gui/icon/outdate.svg", 0.4f)));
        card3.setData(new ModelCard("Doanh thu trong tháng: ", TrangChu_DAO.getDoanhThuThang().getDoanhThu(), new FlatSVGIcon("gui/icon/revenue.svg", 0.09f)));
        card4.setData(new ModelCard("Giao dịch trong tháng: ", TrangChu_DAO.getDoanhThuThang().getSoGiaoDich(), new FlatSVGIcon("gui/icon/transaction.svg", 0.4f)));
    }
    
    private void initTransactionData() {
        pnTransaction. setLayout(new MigLayout("wrap, fill, insets 10 10 10 10", "[center]", "[]10[]"));

//        ArrayList<ModelTransaction> transactions = TrangChu_DAO.getListLastTransaction();
//        for(ModelTransaction transaction : transactions) {
//            LastTransactionPanel pn = new LastTransactionPanel();
//            pn.setData(transaction);
//            pnTransaction.add(pn);
//        }
        ArrayList<ModelSellTransaction> transactions = TrangChu_DAO.getListLastSellTransaction();
        for(ModelSellTransaction transaction : transactions) {
            SellTransactionPanel pn = new SellTransactionPanel();
            pn.setData(transaction);
            pnTransaction.add(pn);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new gui.components.Card();
        pnHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        card2 = new gui.components.Card();
        card3 = new gui.components.Card();
        card4 = new gui.components.Card();
        spTransaction = new javax.swing.JScrollPane();
        pnTransaction = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1470, 730));

        card1.setGradientColor(new java.awt.Color(255, 102, 51));
        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });

        pnHeader.setBackground(new java.awt.Color(11, 101, 136));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Trang chủ");

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeader.setLayout(pnHeaderLayout);
        pnHeaderLayout.setHorizontalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnHeaderLayout.setVerticalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        card2.setGradientColor(new java.awt.Color(255, 51, 51));
        card2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card2MouseClicked(evt);
            }
        });

        card3.setGradientColor(new java.awt.Color(71, 193, 4));
        card3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card3MouseClicked(evt);
            }
        });

        card4.setGradientColor(new java.awt.Color(28, 181, 224));
        card4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card4MouseClicked(evt);
            }
        });

        spTransaction.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(11, 101, 136), 1, true), "Các hoạt động gần đây", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N

        javax.swing.GroupLayout pnTransactionLayout = new javax.swing.GroupLayout(pnTransaction);
        pnTransaction.setLayout(pnTransactionLayout);
        pnTransactionLayout.setHorizontalGroup(
            pnTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        pnTransactionLayout.setVerticalGroup(
            pnTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );

        spTransaction.setViewportView(pnTransaction);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1096, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(spTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        card1.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
        card2.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
        card3.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
        card4.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
        jLabel2.setIcon(new ImageIcon(
            new ImageIcon("src/img/rule.jpg").getImage().
            getScaledInstance(1096, 511, Image.SCALE_SMOOTH)));
    }// </editor-fold>//GEN-END:initComponents

    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        // TODO add your handling code here:
        FormManager.showForm(new ThongKeSanPham_GUI());
    }//GEN-LAST:event_card1MouseClicked

    private void card2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card2MouseClicked
        // TODO add your handling code here:
        FormManager.showForm(new ThongKeSanPham_GUI());
    }//GEN-LAST:event_card2MouseClicked

    private void card3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card3MouseClicked
        // TODO add your handling code here:
        FormManager.showForm(new ThongKeDoanhThu_GUI(user));
    }//GEN-LAST:event_card3MouseClicked

    private void card4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card4MouseClicked
        // TODO add your handling code here:.
        FormManager.showForm(new ThongKeDoanhThu_GUI(user));
    }//GEN-LAST:event_card4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.components.Card card1;
    private gui.components.Card card2;
    private gui.components.Card card3;
    private gui.components.Card card4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JPanel pnTransaction;
    private javax.swing.JScrollPane spTransaction;
    // End of variables declaration//GEN-END:variables
}
