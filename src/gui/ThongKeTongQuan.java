/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.ThongKeTongQuan_DAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import raven.chart.ModelChart;
import nguyenvu.components.SimpleForm;
import nguyenvu.model.ModelKhachHangMoi;
import nguyenvu.model.ModelLineChart;
import nguyenvu.model.ModelTopKhachHang;
import nguyenvu.model.ModelTopNhanVien;
import nguyenvu.model.ModelTopSanPham;
import nguyenvu.utils.DateCalculator;
import raven.chart.ChartLegendRenderer;
import raven.chart.data.category.DefaultCategoryDataset;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.line.LineChart.ChartType;
import raven.datetime.component.date.DateEvent;
import raven.datetime.component.date.DatePicker;
import raven.datetime.component.date.DateSelectionListener;

/**
 *
 * @author HP
 */
public class ThongKeTongQuan extends SimpleForm {
    private DatePicker datePicker;
    private LocalDate startDate;
    private LocalDate endDate;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    
    public ThongKeTongQuan() {
        initComponents();
        datePicker = new DatePicker();
        datePicker.setEditor(editor);
        datePicker.setSeparator(" đến ");
        datePicker.setDateSelectionMode(DatePicker.DateSelectionMode.BETWEEN_DATE_SELECTED);
        datePicker.setUsePanelOption(true);
        datePicker.setDateSelectionAble(LocalDate -> !LocalDate.isAfter(LocalDate.now()));
        datePicker.setCloseAfterSelected(true);
        
        datePicker.addDateSelectionListener(new DateSelectionListener() {
            @Override
            public void dateSelected(DateEvent de) {LocalDate[] dates = datePicker.getSelectedDateRange();
                if (dates != null) {
                    System.out.println("Start Date: " + dtf.format(dates[0]));
                    System.out.println("End Date: " + dtf.format(dates[1]));
                    
                    setDataLineChart(ThongKeTongQuan_DAO.DateRange.CUSTOM, dates[0], dates[1]);
                    setDataPieChart(ThongKeTongQuan_DAO.DateRange.CUSTOM, dates[0], dates[1]);
                    setDataChart(ThongKeTongQuan_DAO.DateRange.CUSTOM, dates[0], dates[1]);

                } else {
                    System.out.println("No dates selected!");
                }
            }
        });
        
        setDataLineChart(ThongKeTongQuan_DAO.DateRange.LAST_30_DAYS, null, null);
        setDataPieChart(ThongKeTongQuan_DAO.DateRange.LAST_30_DAYS, null, null);
        setDataChart(ThongKeTongQuan_DAO.DateRange.LAST_30_DAYS, null, null);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnBtnDate = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblOpt = new javax.swing.JLabel();
        lblIcon2 = new javax.swing.JLabel(createIcon("gui/icon/down.svg", 0.15f));
        editor = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblValuesSumDoanhThu = new javax.swing.JLabel();
        lbllIconDoanhThu = new javax.swing.JLabel();
        lblChangeDoanhThu = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lineChart1 = new raven.chart.line.LineChart();
        jPanel8 = new javax.swing.JPanel();
        pieChart1 = new raven.chart.pie.PieChart();
        pieChart2 = new raven.chart.pie.PieChart();
        pieChart3 = new raven.chart.pie.PieChart();
        pnCustomer = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblValuesCustomer = new javax.swing.JLabel();
        lblIconCustomer = new javax.swing.JLabel();
        lblChangeCustomer = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        chart1 = new com.raven.chart.Chart();
        pnTransaction = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblValuesTransactions = new javax.swing.JLabel();
        lblIconTransactions = new javax.swing.JLabel();
        lblChangeTransactions = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lineChart2 = new raven.chart.line.LineChart();

        setPreferredSize(new java.awt.Dimension(1470, 1300));

        pnHeader.setBackground(new java.awt.Color(11, 101, 136));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Thống kê tổng quan");

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
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("lblDate");

        lblOpt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOpt.setText("lblOpt");

        javax.swing.GroupLayout pnBtnDateLayout = new javax.swing.GroupLayout(pnBtnDate);
        pnBtnDate.setLayout(pnBtnDateLayout);
        pnBtnDateLayout.setHorizontalGroup(
            pnBtnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtnDateLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnBtnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOpt, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnBtnDateLayout.setVerticalGroup(
            pnBtnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtnDateLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnBtnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBtnDateLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, 0)
                        .addComponent(lblOpt)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnBtnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(editor, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnBtnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(editor)
                        .addContainerGap())))
        );

        pnBtnDate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnBtnDate.setVisible(false);

        jPanel6.setPreferredSize(new java.awt.Dimension(1450, 398));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("TỔNG DOANH THU");

        lblValuesSumDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblValuesSumDoanhThu.setForeground(new java.awt.Color(0, 204, 51));
        lblValuesSumDoanhThu.setText("8,888,888,888.0");

        lblChangeDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChangeDoanhThu.setText("change");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblValuesSumDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lbllIconDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblChangeDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChangeDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbllIconDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblValuesSumDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel10.setText("Doanh thu theo thời gian");

        lineChart1.setPreferredSize(new java.awt.Dimension(1450, 193));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lineChart1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lineChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        lineChart1.setChartType(ChartType.LINE);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pieChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pieChart3, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(pieChart3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        JLabel header1 = new JLabel("Top 10 sản phẩm bán chạy");
        pieChart1.setHeader(header1);
        JLabel header2 = new JLabel("Top nhân viên có doanh số cao nhất");
        pieChart2.setHeader(header2);
        JLabel header3 = new JLabel("Top khách hàng thường xuyên mua sản phẩm");
        pieChart3.setHeader(header3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");
        jPanel8.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnCustomer.setPreferredSize(new java.awt.Dimension(700, 377));

        jLabel8.setText("TỔNG SỐ KHÁCH HÀNG MỚI");

        lblValuesCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblValuesCustomer.setForeground(new java.awt.Color(96, 196, 235));
        lblValuesCustomer.setText("jLabel9");

        lblChangeCustomer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblChangeCustomer.setText("change");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lblValuesCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIconCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChangeCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(524, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValuesCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lblIconCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChangeCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel13.setText("Khách hàng mới theo thời gian");

        javax.swing.GroupLayout pnCustomerLayout = new javax.swing.GroupLayout(pnCustomer);
        pnCustomer.setLayout(pnCustomerLayout);
        pnCustomerLayout.setHorizontalGroup(
            pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnCustomerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(chart1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        pnCustomerLayout.setVerticalGroup(
            pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCustomerLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnTransaction.setPreferredSize(new java.awt.Dimension(700, 275));

        jLabel22.setText("TỔNG SỐ GIAO DỊCH");

        lblValuesTransactions.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblValuesTransactions.setForeground(new java.awt.Color(96, 196, 235));
        lblValuesTransactions.setText("jLabel9");

        lblChangeTransactions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblChangeTransactions.setText("change");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(lblValuesTransactions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIconTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChangeTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValuesTransactions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lblIconTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChangeTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel26.setText("Giao dịch theo thời gian");

        lineChart2.setPreferredSize(new java.awt.Dimension(700, 193));

        javax.swing.GroupLayout pnTransactionLayout = new javax.swing.GroupLayout(pnTransaction);
        pnTransaction.setLayout(pnTransactionLayout);
        pnTransactionLayout.setHorizontalGroup(
            pnTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnTransactionLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lineChart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnTransactionLayout.setVerticalGroup(
            pnTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTransactionLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lineChart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addComponent(pnTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
        pnCustomer.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");
        pnTransaction.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");
    }// </editor-fold>//GEN-END:initComponents

    private void editorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editorActionPerformed

    private void setDataLineChart(ThongKeTongQuan_DAO.DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        ArrayList<ModelLineChart> currentData = 
                ThongKeTongQuan_DAO.getLineChartData(dateRange, startDate, endDate);
        ArrayList<ModelLineChart> prevData = 
                ThongKeTongQuan_DAO.getLineChartPrevData(dateRange, startDate, endDate);
        
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset<>();
        DefaultCategoryDataset categoryDataset2 = new DefaultCategoryDataset<>();
        
        double sumCurrentDoanhThu = 0;
        int sumGiaoDich = 0;
        
        for (ModelLineChart data : currentData) {
            sumCurrentDoanhThu += data.getDoanhThu();
            
            String date = dtf.format(data.getNgay());
            
            categoryDataset.addValue(data.getDoanhThu(), "Doanh Thu", date);
            categoryDataset.addValue(data.getChiPhi(), "Chi phí", date);
            categoryDataset.addValue(data.getThue(), "Thuế", date);
            categoryDataset.addValue(data.getDoanhThu() - data.getChiPhi() - data.getThue(), "Lợi nhuận", date);
            
            sumGiaoDich += data.getSlDonBan() + data.getSlDonDoiTra();
            
            categoryDataset2.addValue(data.getSlDonBan(), "Đơn bán sản phẩm", date);
            categoryDataset2.addValue(data.getSlDonDoiTra(), "Đơn đổi trả sản phẩm", date);
        }


//        int i = 0;
//        LocalDate start = startDate != null ? startDate : LocalDate.now().minusDays(30);
//        LocalDate end = endDate != null ? endDate : LocalDate.now();
//        for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusDays(1)) {
//            if (i < currentData.size() && !currentData.get(i).getNgay().equals(date)) {
//                categoryDataset.addValue(0, "Doanh Thu", date);
//                categoryDataset.addValue(0, "Chi phí", date);
//                categoryDataset.addValue(0, "Thuế", date);
//                categoryDataset.addValue(0, "Lợi nhuận", date);
//                
//                categoryDataset2.addValue(0, "Đơn bán sản phẩm", date);
//            } else if(i < currentData.size()) {
//                ModelLineChart data = currentData.get(i);
//                sumCurrentDoanhThu += data.getDoanhThu();
//                
//                categoryDataset.addValue(data.getDoanhThu(), "Doanh Thu", date);
//                categoryDataset.addValue(data.getChiPhi(), "Chi phí", date);
//                categoryDataset.addValue(data.getThue(), "Thuế", date);
//                categoryDataset.addValue(data.getDoanhThu() - data.getChiPhi() - data.getThue(), "Lợi nhuận", date);
//
//                categoryDataset2.addValue(data.getSlDonBan(), "Đơn bán sản phẩm", date);
//            }
//            i++;
//        }
        

        double sumPrevDoanhThu = 0;
        int sumPrevGiaoDich = 0;
        
        for(ModelLineChart data : prevData) {
            sumPrevDoanhThu += data.getDoanhThu();
            sumPrevGiaoDich += data.getSlDonBan() + data.getSlDonDoiTra();
        }
        
        LocalDate dateStart = currentData.get(0).getNgay();
        LocalDate dateEnd = currentData.get(currentData.size() - 1).getNgay();

        long diffDays = ChronoUnit.DAYS.between(dateStart, dateEnd);
        
        double d = Math.ceil((diffDays / 20f));
        lineChart1.setLegendRenderer(new ChartLegendRenderer() {
            @Override
            public Component getLegendComponent(Object legend, int index) {
                if (index % d == 0) {
                    return super.getLegendComponent(legend, index);
                } else {
                    return null;
                }
            }
        });
        
        lineChart1.setCategoryDataset(categoryDataset);
        lineChart1.getChartColor().addColor(Color.decode("#efff1f"), Color.decode("#8FDBF9"));
        lineChart1.startAnimation();
        
        lineChart2.setLegendRenderer(new ChartLegendRenderer() {
            @Override
            public Component getLegendComponent(Object legend, int index) {
                if (index % (d * 2f) == 0) {
                    return super.getLegendComponent(legend, index);
                } else {
                    return null;
                }
            }
        });
        
        lineChart2.setCategoryDataset(categoryDataset2);
        lineChart2.getChartColor().addColor(Color.decode("#efff1f"), Color.decode("#8FDBF9"));
        lineChart2.startAnimation();
        
        lblValuesSumDoanhThu.setText(df.format(sumCurrentDoanhThu) + " VND");
        
        double percent;
        if (sumPrevDoanhThu == 0) {
            if (sumCurrentDoanhThu > 0) {
                percent = 100; 
            } else {
                percent = 0; 
            }
        } else {
            percent = ((sumCurrentDoanhThu - sumPrevDoanhThu) / sumPrevDoanhThu) * 100;
        }
        
        System.out.println("Current Total DoanhThu: " + sumCurrentDoanhThu);
        System.out.println("Previous Total DoanhThu: " + sumPrevDoanhThu);
        System.err.println("Percent: " + percent);
        

        lblChangeDoanhThu.setText(df.format(Math.abs(percent)) + "%");

        
        if(percent == 0) {
            lbllIconDoanhThu.setIcon(createIcon("gui/icon/minus-gray.svg", 0.7f));
            lblChangeDoanhThu.setForeground(Color.GRAY);
        }
        else if(percent > 0) {
            lbllIconDoanhThu.setIcon(createIcon("gui/icon/arrow-up.svg", 0.8f));
            lblChangeDoanhThu.setForeground(new Color(51,204,0));
        } 
        else {
            lbllIconDoanhThu.setIcon(createIcon("gui/icon/arrow-down.svg", 0.4f));
            lblChangeDoanhThu.setForeground(new Color(255,0,0));
        }
        
        
        //Transactions
        lblValuesTransactions.setText(df.format(sumGiaoDich));
        
        
        double percentGiaoDich;
        if (sumPrevGiaoDich == 0) {
            if (sumGiaoDich > 0) {
                percentGiaoDich = 100; 
            } else {
                percentGiaoDich = 0; 
            }
        } else {
            percentGiaoDich = ((sumGiaoDich - sumPrevGiaoDich) / sumPrevGiaoDich) * 100;
        }
        
        System.out.println("Current Total GiaoDich: " + sumGiaoDich);
        System.out.println("Previous Total GiaoDich: " + sumPrevGiaoDich);
        System.err.println("Percent: " + percentGiaoDich);
        

        lblChangeTransactions.setText(df.format(Math.abs(percentGiaoDich)) + "%");
        
        if(percentGiaoDich == 0) {
            lblIconTransactions.setIcon(createIcon("gui/icon/minus-gray.svg", 0.7f));
            lblChangeTransactions.setForeground(Color.GRAY);
        }
        else if(percentGiaoDich > 0) {
            lblIconTransactions.setIcon(createIcon("gui/icon/arrow-up.svg", 0.8f));
            lblChangeTransactions.setForeground(new Color(51,204,0));
        } 
        else {
            lblIconTransactions.setIcon(createIcon("gui/icon/arrow-down.svg", 0.4f));
            lblChangeTransactions.setForeground(new Color(255,0,0));
        }
    }
    
  
    private void setDataPieChart(ThongKeTongQuan_DAO.DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        DefaultPieDataset<String> dataset2 = new DefaultPieDataset<>();
        DefaultPieDataset<String> dataset3 = new DefaultPieDataset<>();
        
        
        ArrayList<ModelTopSanPham> listSP = ThongKeTongQuan_DAO.getChartDataTopSanPham(dateRange, startDate, endDate);
        ArrayList<ModelTopNhanVien> listNV = ThongKeTongQuan_DAO.getChartDataTopNhanVien(dateRange, startDate, endDate);
        ArrayList<ModelTopKhachHang> listKH = ThongKeTongQuan_DAO.getChartDataTopKhachHang(dateRange, startDate, endDate);
        
        for (ModelTopSanPham data : listSP) {
            dataset.addValue(data.getTenSP(), data.getSoLuong());
        }
        
        for (ModelTopNhanVien data : listNV) {
            dataset2.addValue(data.getTenNV(), data.getDoanhSo());
        }
        
        for (ModelTopKhachHang data : listKH) {
            dataset3.addValue(data.getTenKH(), data.getTongTienMua());
        }
        
        pieChart1.setDataset(dataset);
        pieChart1.startAnimation();
        
        pieChart2.setDataset(dataset2);
        pieChart2.startAnimation();
        
        pieChart3.setDataset(dataset3);
        pieChart3.startAnimation();
    }
    
    private void setDataChart(ThongKeTongQuan_DAO.DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        ArrayList<ModelKhachHangMoi> datas = ThongKeTongQuan_DAO.getChartDataKhachHangMoi(dateRange, startDate, endDate);
        ArrayList<ModelKhachHangMoi> prevDatas = ThongKeTongQuan_DAO.getChartPrevDataKhachHangMoi(dateRange, startDate, endDate);
        
        int sumQuantity = 0;
        int sumPrevQuantity = 0;
        
        for (ModelKhachHangMoi data : datas) {
            sumQuantity += data.getSoLuong();
            chart1.addData(new com.raven.chart.ModelChart(String.valueOf(data.getDate()), new double[] {data.getSoLuong()}));
        }
        
        for (ModelKhachHangMoi prevData : prevDatas) {
            sumPrevQuantity += prevData.getSoLuong();  
        }
        
        lblValuesCustomer.setText(df.format(sumQuantity));
        
        double percent;
        if (sumPrevQuantity == 0) {
            if (sumQuantity > 0) {
                percent = 100; 
            } else {
                percent = 0; 
            }
        } else {
            percent = ((sumQuantity - sumPrevQuantity) / sumPrevQuantity) * 100;
        }
        
        System.out.println("Current Total Customer: " + sumQuantity);
        System.out.println("Previous Total Customer: " + sumPrevQuantity);
        System.err.println("Percent: " + percent);
        

        lblChangeCustomer.setText(df.format(Math.abs(sumPrevQuantity)) + "%");
        
        if(percent == 0) {
            lblIconCustomer.setIcon(createIcon("gui/icon/minus-gray.svg", 0.7f));
            lblChangeCustomer.setForeground(Color.GRAY);
        }
        else if(percent > 0) {
            lblIconCustomer.setIcon(createIcon("gui/icon/arrow-up.svg", 0.8f));
            lblChangeCustomer.setForeground(new Color(51,204,0));
        } 
        else {
            lblIconCustomer.setIcon(createIcon("gui/icon/arrow-down.svg", 0.4f));
            lblChangeCustomer.setForeground(new Color(255,0,0));
        }
        
        chart1.start();
    }
    
    
    private Icon createIcon(String path, float scale) {
        FlatSVGIcon icon = new FlatSVGIcon(path, scale);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#000000"), Color.decode("#FAFAFA"), Color.decode("#000000"));
        icon.setColorFilter(colorFilter);
        return icon;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.chart.Chart chart1;
    private javax.swing.JFormattedTextField editor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblChangeCustomer;
    private javax.swing.JLabel lblChangeDoanhThu;
    private javax.swing.JLabel lblChangeTransactions;
    private javax.swing.JLabel lblIcon2;
    private javax.swing.JLabel lblIconCustomer;
    private javax.swing.JLabel lblIconTransactions;
    private javax.swing.JLabel lblOpt;
    private javax.swing.JLabel lblValuesCustomer;
    private javax.swing.JLabel lblValuesSumDoanhThu;
    private javax.swing.JLabel lblValuesTransactions;
    private javax.swing.JLabel lbllIconDoanhThu;
    private raven.chart.line.LineChart lineChart1;
    private raven.chart.line.LineChart lineChart2;
    private raven.chart.pie.PieChart pieChart1;
    private raven.chart.pie.PieChart pieChart2;
    private raven.chart.pie.PieChart pieChart3;
    private javax.swing.JPanel pnBtnDate;
    private javax.swing.JPanel pnCustomer;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JPanel pnTransaction;
    // End of variables declaration//GEN-END:variables
}
