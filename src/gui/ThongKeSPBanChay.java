
package gui;

import com.lowagie.text.List;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import nguyenvu.components.SimpleForm;
import nguyenvu.model.ModelData;
import nguyenvu.model.ModelDataSP;
import raven.alerts.MessageAlerts;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import connectDB.connectDB;
import dao.ThongKe_DAO;
import entity.SanPham_entity;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 *
 * @author RAVEN
 */
public class ThongKeSPBanChay extends SimpleForm{

    private Chart chart;
	private JPanel pnCenter;
	private ThongKe_DAO tk_Dao;
    public ThongKeSPBanChay(int time) {
    	tk_Dao = new ThongKe_DAO();
		setPreferredSize(new Dimension(1117, 668));
        initComponents();
        setBackground(new Color(240, 240, 240,0));
        setLayout(new BorderLayout(0, 0));
        add(pnCenter);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        chart.addLegend(null, new Color(135, 189, 245));
        themData(time);
        chart.start();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        pnCenter = new JPanel();
        pnCenter.setBackground(new Color(240, 240, 240,0));
                        pnCenter.setLayout(new BorderLayout(0, 0));
                
                        chart = new com.raven.chart.Chart();
                        chart.setBackground(new Color(255, 255, 255));
                        pnCenter.add(chart);
                        
                                chart.setFont(new Font("Arial", Font.PLAIN, 12));
    }
    private void themData(int time) {
        Connection connection = null;
        try {
            
            connection = connectDB.accessDataBase(); // Lấy kết nối
            if (connection == null) {
                System.out.println("Cannot connect to the database.");
                return;
            }
            java.util.List<ModelDataSP> lists = tk_Dao.SanPhamBanChay(time);
            
            chart.clear();
            for (ModelDataSP d : lists) {
                chart.addData(new ModelChart(d.getTenSP(), new double[]{d.getTongSoLuong()})); 
            }
            chart.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



}