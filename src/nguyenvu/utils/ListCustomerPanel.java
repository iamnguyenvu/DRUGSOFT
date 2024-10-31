/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.FlatClientProperties;
import entity.KhachHang_entity;
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author HP
 */
public class ListCustomerPanel extends javax.swing.JPanel {
    private CustomerSelectListener listener;
    
    public void addCustomertSelectListener(CustomerSelectListener listener) {
        this.listener = listener;
    }
    
    public ListCustomerPanel() {
        initComponents();
        setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");
    }
    
    public void setData(ArrayList<KhachHang_entity> listKH) {
        this.removeAll();
        for (KhachHang_entity kh : listKH) {
            CustomerPanel pn = new CustomerPanel(kh);
            pn.addCustomerSelectListener(new CustomerSelectListener() {
                @Override
                public void onCustomerSeclect(KhachHang_entity customerSelect) {
                    if(listener != null) {
                        listener.onCustomerSeclect(customerSelect);
                    }
                }
            });
            this.add(pn, "wrap");
        }
        repaint();
        revalidate();
    }
    
    public int getListSize() {
        return getComponentCount();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
