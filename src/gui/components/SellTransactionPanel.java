/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JLabel;
import nguyenvu.model.ModelSellTransaction;
import nguyenvu.utils.CircleLabel;

/**
 *
 * @author HP
 */
public class SellTransactionPanel extends javax.swing.JPanel{
   
    public SellTransactionPanel() {
        initComponents();
    }
    
    public void setData(ModelSellTransaction data ) {
        setColoredTextByLength(lblInfor, lblIcon, data);
        LocalDateTime invoiceDate = data.getDate(); 
        LocalDateTime now = LocalDateTime.now();
        long numberDay = ChronoUnit.DAYS.between(invoiceDate, now);
        lblCalDate.setText(numberDay + " ngày trước");
    }
    
    public void setColoredTextByLength(JLabel label, JLabel icon, ModelSellTransaction data) {
        String phanQuyen = data.getPhanQuyen();
        String tenNV = data.getTenNV();
        String loaiHoaDon = data.getLoaiHoaDon();
        double thanhTien = data.getThanhTien();
        double tienTraLai = data.getTienTraLai();
        double tienKhachTraThem = data.getTienKhachTraThem();
        LocalDateTime date = data.getDate();
        
        DecimalFormat df = new DecimalFormat("#,##0.##");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        StringBuilder htmlText = new StringBuilder("<html>");

        htmlText.append(String.format("<span style='color: black;'>%s</span> ", phanQuyen));
        htmlText.append(String.format("<span style='color: #0B6588; font-weight: bold;'>%s</span> ", tenNV));

        switch(loaiHoaDon) {
            case "Ban":
                icon.setIcon(new FlatSVGIcon("gui/icon/sell.svg", 0.5f));
                ((CircleLabel) icon).setBackground(new Color(11,101,136));
                htmlText.append("<span style='color: black;'>đã tạo hóa đơn</span> ");
                htmlText.append("<span style='color: #0B6588;font-weight: bold;'>bán hàng</span> ");
                htmlText.append("<span style='color: black;'>trị giá</span> ");
                htmlText.append(String.format("<span style='color: black;font-weight: bold;'>%sđ</span> ", df.format(thanhTien)));
                break;
            case "DoiTra":
                icon.setIcon(new FlatSVGIcon("gui/icon/refund.svg", 0.08f));
                ((CircleLabel) icon).setBackground(new Color(255,102,51));
                lblIcon.setBackground(new Color(255,102,51));
                htmlText.append("<span style='color: black;'>đã tạo đơn</span> ");
                htmlText.append("<span style='color: #0B6588;font-weight: bold;'>đổi trả sản phẩm</span> ");
                
                if(tienKhachTraThem > 0) {
                    htmlText.append("<span style='color: black;'>khách bù thêm</span> ");
                    htmlText.append(String.format("<span style='color: black;font-weight: bold;'>%sđ</span> ", df.format(tienKhachTraThem)));
                }
                else {
                    htmlText.append("<span style='color: black;'>hoàn</span> ");
                    htmlText.append(String.format("<span style='color: black;font-weight: bold;'>%sđ</span> ", df.format(tienTraLai)));
                }
                break;
            default:
                htmlText.append("<span style='color: black;'>thực hiện giao dịch</span> ");
                break;
        }
        htmlText.append(String.format("<span style='color: black;'>vào</span> <span style='color: black;'>%s</span> ", formatter.format(date)));
        htmlText.append("</html>");

        label.setText(htmlText.toString());
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInfor = new javax.swing.JLabel();
        lblIcon = new CircleLabel();
        lblCalDate = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblInfor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblInfor.setText("Role");

        lblIcon.setForeground(new java.awt.Color(255, 255, 255));

        lblCalDate.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblCalDate.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfor, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(8, Short.MAX_VALUE)
                        .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCalDate;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblInfor;
    // End of variables declaration//GEN-END:variables
}
