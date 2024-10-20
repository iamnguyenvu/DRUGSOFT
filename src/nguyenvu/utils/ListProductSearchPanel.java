/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import entity.SanPham_entity;
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author HP
 */
public class ListProductSearchPanel extends javax.swing.JPanel {
    
    public ListProductSearchPanel() {
        initComponents();
        setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
    }

    public void setData(ArrayList<SanPham_entity> listSP) {
        this.removeAll();
        for (SanPham_entity sp : listSP) {
            ProductSearchPanel pn = new ProductSearchPanel(sp);
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
