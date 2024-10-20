/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.FlatClientProperties;
import entity.SanPham_entity;
import java.awt.Component;
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author HP
 */
public class ListProductSearchPanel extends javax.swing.JPanel {
    private ProductSelectListener listener;
    
    public void addProductSelectListener(ProductSelectListener listener) {
        this.listener = listener;
    }
    
    public ListProductSearchPanel() {
        initComponents();
        setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");
    }

    public void setData(ArrayList<SanPham_entity> listSP) {
        this.removeAll();
        for (SanPham_entity sp : listSP) {
            ProductSearchPanel pn = new ProductSearchPanel(sp);
            pn.addProductSelectListener(new ProductSelectListener() {
                @Override
                public void onProductSelected(SanPham_entity selectedProduct) {
                    if (listener != null) {
                        listener.onProductSelected(selectedProduct);
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

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
