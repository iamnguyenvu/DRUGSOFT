/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author HP
 */
public class CustomerSearchPanel extends javax.swing.JPanel {
    public CustomerSearchPanel() {
        initComponents();
        setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
    }
    
    public void setData(ArrayList<DataSearch> data) {
        this.removeAll();
        for (DataSearch ds : data) {
            
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
