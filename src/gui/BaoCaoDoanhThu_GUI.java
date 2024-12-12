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
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.BaoCaoDoanhThu_DAO;
import entity.BaoCaoDoanhThu_enity;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.EditViewButtonEditor;
import nguyenvu.utils.EditViewButtonRenderer;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.EditViewButtonEvent;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import entity.SanPham_entity;
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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class BaoCaoDoanhThu_GUI extends SimpleForm {
    
    private BaoCaoDoanhThu_DAO dao;
    private int currentPage = 1;
    private int rowsPerPage = 10;
    private int totalPages = 0;
    private Date selectedStartDate = null;
    private Date selectedEndDate = null;
    private JDialog dateDialog = null;
    private List<BaoCaoDoanhThu_enity> reportData;

    private LocalDate ngayHienTai = LocalDate.now();
    private LocalDate bayNgayTruoc = ngayHienTai.minusDays(7);
    private LocalDate bamuoiNgayTruoc = ngayHienTai.minusDays(30);
    private LocalDate chinmuoiNgayTruoc = ngayHienTai.minusDays(90);
    private LocalDate basaunamngaytruoc = ngayHienTai.minusDays(365);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    SimpleDateFormat formatterForDate = new SimpleDateFormat("dd/MM/yyyy");
    
    public BaoCaoDoanhThu_GUI(){
        initComponents();
        loadData7Ngay(currentPage, rowsPerPage);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlTuyChinh = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        toDate = new com.toedter.calendar.JDateChooser();
        btnDialog = new javax.swing.JButton();
        pnHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnPrevious = new javax.swing.JButton(new FlatSVGIcon("gui/icon/prev.svg"));
        btnNext = new javax.swing.JButton(new FlatSVGIcon("gui/icon/next.svg"));
        btnFirst = new javax.swing.JButton(new FlatSVGIcon("gui/icon/first-page.svg", 0.03f));
        btnLast = new javax.swing.JButton(new FlatSVGIcon("gui/icon/last-page.svg", 0.03f));
        xuatPDF = new javax.swing.JButton(new FlatSVGIcon("gui/icon/print.svg", 0.35f));
        xuatExcel = new javax.swing.JButton(new FlatSVGIcon("gui/icon/export.svg", 0.3f));
        cbTime = new javax.swing.JComboBox<>();
        lblPageIndicator = new javax.swing.JLabel();

        dlTuyChinh.setLocation(new java.awt.Point(0, 0));

        jLabel2.setText("Ngày Bắt Đầu");

        fromDate.setDateFormatString("dd/MM/yyyy");

        jLabel3.setText("Ngày Kết Thúc");

        toDate.setDateFormatString("dd/MM/yyyy");

        btnDialog.setText("Xác nhận");

        javax.swing.GroupLayout dlTuyChinhLayout = new javax.swing.GroupLayout(dlTuyChinh.getContentPane());
        dlTuyChinh.getContentPane().setLayout(dlTuyChinhLayout);
        dlTuyChinhLayout.setHorizontalGroup(
            dlTuyChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlTuyChinhLayout.createSequentialGroup()
                .addGroup(dlTuyChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlTuyChinhLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(dlTuyChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(dlTuyChinhLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnDialog)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        dlTuyChinhLayout.setVerticalGroup(
            dlTuyChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlTuyChinhLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDialog)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(1470, 730));

        pnHeader.setBackground(new java.awt.Color(11, 101, 136));
        pnHeader.setPreferredSize(new java.awt.Dimension(1470, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Báo cáo doanh thu");

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeader.setLayout(pnHeaderLayout);
        pnHeaderLayout.setHorizontalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
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

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "Doanh thu", "Chi phí", "Lợi nhuận", "Tổng số lượng giao dịch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(50);
        table.setRowSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(200);
        }
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        table.getTableHeader().setBackground(new Color(11,101,136));
        table.getTableHeader().setForeground(Color.WHITE);

        xuatPDF.setBackground(new java.awt.Color(255, 0, 0));
        xuatPDF.setForeground(new java.awt.Color(255, 255, 255));
        xuatPDF.setText("In PDF");
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

        cbTime.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cbTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7 Ngày Qua", "30 Ngày Qua", "90 Ngày Qua", "365 Ngày Qua", "Toàn Thời Gian", "Tùy Chỉnh", " " }));
        cbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimeActionPerformed(evt);
            }
        });

        lblPageIndicator.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xuatPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPageIndicator)
                            .addGap(18, 18, 18)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
            .addComponent(pnHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 1460, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xuatPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPageIndicator)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
        btnFirst.addActionListener(e -> {
            currentPage = 1;
            loadDataBasedOnSelection();
        });

        btnPrevious.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadDataBasedOnSelection();
            }
        });

        btnNext.addActionListener(e -> {
            if (currentPage < totalPages) {
                currentPage++;
                loadDataBasedOnSelection();
            }
        });

        btnLast.addActionListener(e -> {
            currentPage = totalPages;
            loadDataBasedOnSelection();
        });
    }// </editor-fold>//GEN-END:initComponents

    private void cbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimeActionPerformed
        String selected = cbTime.getSelectedItem().toString();  
        
        if(selected.equals("7 Ngày Qua")){
            currentPage = 1;
            loadData7Ngay(currentPage,rowsPerPage);
        } else if (selected.equals("30 Ngày Qua")){
            currentPage = 1;
            loadData30Ngay(currentPage,rowsPerPage);
        } else if (selected.equals("90 Ngày Qua")){
            currentPage = 1;
            loadData90Ngay(currentPage,rowsPerPage);
        } else if (selected.equals("365 Ngày Qua")){
            currentPage = 1;
            loadData365Ngay(currentPage,rowsPerPage);
        } else if (selected.equals("Toàn Thời Gian")){
            currentPage = 1;
            loadDataToanTG(currentPage,rowsPerPage);
        } else {
            currentPage = 1;
            showCustomDateRange();
        }  
    }//GEN-LAST:event_cbTimeActionPerformed

    private void xuatPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatPDFActionPerformed
        xuatPDF();
    }//GEN-LAST:event_xuatPDFActionPerformed

    private void xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatExcelActionPerformed
        xuatExcel();
    }//GEN-LAST:event_xuatExcelActionPerformed

    public void loadDataCustomer(Date startDate, Date endDate, int currentPage, int rowsPerPage) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        dao = new BaoCaoDoanhThu_DAO();
        ArrayList<BaoCaoDoanhThu_enity> dsBaoCao = dao.getAllBaoCaoDoanhThuTuyChinh(startDate, endDate);

        // Tính tổng số trang
        totalPages = (int) Math.ceil((double) dsBaoCao.size() / rowsPerPage);

        // Tính chỉ số bắt đầu và kết thúc
        int start = (currentPage - 1) * rowsPerPage;
        int end = Math.min(start + rowsPerPage, dsBaoCao.size());

        // Hiển thị dữ liệu của trang hiện tại
        for (int i = start; i < end; i++) {
            BaoCaoDoanhThu_enity bc = dsBaoCao.get(i);
            Object[] rowData = {
                bc.getThoiGian(),
                String.format("%.2f", bc.getDoanhThu()),
                String.format("%.2f", bc.getChiPhi()),
                String.format("%.2f", bc.getLoiNhuan()),
                bc.getSoLuongGD()
            };
            model.addRow(rowData);
        }

        // Cập nhật chỉ báo trang
        lblPageIndicator.setText(currentPage + " / " + totalPages);
    }

    private void showCustomDateRange() {
        if (dateDialog == null) { // Chỉ tạo dialog một lần
            // Tạo một panel để chứa JDateChooser và nút Xác Nhận
            JPanel datePanel = new JPanel();
            datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));

            // Tạo JPanel chứa JLabel và căn trái
            JPanel panelStartDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel nbd = new JLabel("Ngày Bắt Đầu:");
            panelStartDate.add(nbd);
            datePanel.add(panelStartDate);

            // Tạo và thêm JDateChooser cho ngày bắt đầu
            JDateChooser dateChooserStart = new JDateChooser();
            datePanel.add(dateChooserStart);

            // Tạo JPanel chứa JLabel và căn trái
            JPanel panelEndDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel nkt = new JLabel("Ngày Kết Thúc:");
            panelEndDate.add(nkt);
            datePanel.add(panelEndDate);

            // Tạo và thêm JDateChooser cho ngày kết thúc
            JDateChooser dateChooserEnd = new JDateChooser();
            datePanel.add(dateChooserEnd);

            // Tạo JPanel cho nút xác nhận và căn phải
            JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnConfirm = new JButton("Xác Nhận");
            panelButton.add(btnConfirm);
            datePanel.add(panelButton);

            // Đặt màu nền cho panel là tím nhạt
            datePanel.setBackground(new Color(240, 240, 240));

            // Hộp thoại tùy chỉnh JOptionPane
            dateDialog = new JDialog((Frame) null, "Chọn Ngày", true);
            dateDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dateDialog.getContentPane().add(datePanel);
            dateDialog.pack();
            dateDialog.setSize(250, 200);
            dateDialog.setLocationRelativeTo(null);

            // Xử lý sự kiện xác nhận
            btnConfirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    java.util.Date startDate = dateChooserStart.getDate();
                    java.util.Date endDate = dateChooserEnd.getDate();

                    if (startDate != null && endDate != null) {
                        if (startDate.after(endDate)) {
                            JOptionPane.showMessageDialog(dateDialog, "Ngày bắt đầu không được sau ngày kết thúc.");
                            return;
                        }

                        // Cập nhật giá trị vào các biến toàn cục
                        selectedStartDate = startDate;
                        selectedEndDate = endDate;

                        // Định dạng ngày sang chuỗi dd/MM/yyyy
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedStartDate = dateFormat.format(startDate);
                        String formattedEndDate = dateFormat.format(endDate);

                        String customDateRange = formattedStartDate + " - " + formattedEndDate;

                        cbTime.setEditable(true);
                        cbTime.setSelectedItem(customDateRange);
                        cbTime.setEditable(false);

                        // Gọi hàm tải dữ liệu
                        currentPage = 1; // Reset về trang đầu tiên
                        loadDataCustomer(selectedStartDate, selectedEndDate, currentPage, rowsPerPage);

                        dateDialog.dispose(); // Ẩn dialog
                    } else {
                        JOptionPane.showMessageDialog(dateDialog, "Vui lòng chọn đầy đủ ngày bắt đầu và kết thúc.");
                    }
                }
            });
        }

        // Hiển thị dialog
        dateDialog.setVisible(true);
    }

    public void loadData7Ngay(int currentPage, int rowsPerPage) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            // Xóa toàn bộ dữ liệu cũ trong bảng
	    dao = new BaoCaoDoanhThu_DAO();
            ArrayList<BaoCaoDoanhThu_enity> dsBaoCao = dao.getAllBaoCaoDoanhThu7Ngay();
	    // Tính tổng số trang
	    totalPages = (int) Math.ceil((double) dsBaoCao.size() / rowsPerPage);

	    // Tính toán chỉ số bắt đầu và kết thúc
	    int start = (currentPage - 1) * rowsPerPage;
	    int end = Math.min(start + rowsPerPage, dsBaoCao.size());

	    // Hiển thị dữ liệu của trang hiện tại
	    for (int i = start; i < end; i++) {
                BaoCaoDoanhThu_enity bc = dsBaoCao.get(i);
                Object[] rowData = {
                    bc.getThoiGian(),
                    String.format("%.2f", bc.getDoanhThu()),
                    String.format("%.2f", bc.getChiPhi()),
                    String.format("%.2f", bc.getLoiNhuan()),
                    bc.getSoLuongGD()
                };
                model.addRow(rowData);
            }
	    lblPageIndicator.setText(currentPage + " / " + totalPages);
	}
    
    public void loadData30Ngay(int currentPage, int rowsPerPage) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            // Xóa toàn bộ dữ liệu cũ trong bảng
	    dao = new BaoCaoDoanhThu_DAO();
            ArrayList<BaoCaoDoanhThu_enity> dsBaoCao = dao.getAllBaoCaoDoanhThu30Ngay();
	    // Tính tổng số trang
	    totalPages = (int) Math.ceil((double) dsBaoCao.size() / rowsPerPage);

	    // Tính toán chỉ số bắt đầu và kết thúc
	    int start = (currentPage - 1) * rowsPerPage;
	    int end = Math.min(start + rowsPerPage, dsBaoCao.size());

	    // Hiển thị dữ liệu của trang hiện tại
	    for (int i = start; i < end; i++) {
                BaoCaoDoanhThu_enity bc = dsBaoCao.get(i);
                Object[] rowData = {
                    bc.getThoiGian(),
                    String.format("%.2f", bc.getDoanhThu()),
                    String.format("%.2f", bc.getChiPhi()),
                    String.format("%.2f", bc.getLoiNhuan()),
                    bc.getSoLuongGD()
                };
                model.addRow(rowData);
            }
	    lblPageIndicator.setText(currentPage + " / " + totalPages);
	}

    public void loadData90Ngay(int currentPage, int rowsPerPage) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            // Xóa toàn bộ dữ liệu cũ trong bảng
	    dao = new BaoCaoDoanhThu_DAO();
            ArrayList<BaoCaoDoanhThu_enity> dsBaoCao = dao.getAllBaoCaoDoanhThu90Ngay();
	    // Tính tổng số trang
	    totalPages = (int) Math.ceil((double) dsBaoCao.size() / rowsPerPage);

	    // Tính toán chỉ số bắt đầu và kết thúc
	    int start = (currentPage - 1) * rowsPerPage;
	    int end = Math.min(start + rowsPerPage, dsBaoCao.size());

	    // Hiển thị dữ liệu của trang hiện tại
	    for (int i = start; i < end; i++) {
                BaoCaoDoanhThu_enity bc = dsBaoCao.get(i);
                Object[] rowData = {
                    bc.getThoiGian(),
                    String.format("%.2f", bc.getDoanhThu()),
                    String.format("%.2f", bc.getChiPhi()),
                    String.format("%.2f", bc.getLoiNhuan()),
                    bc.getSoLuongGD()
                };
                model.addRow(rowData);
            }
	    lblPageIndicator.setText(currentPage + " / " + totalPages);
	}
    
    public void loadData365Ngay(int currentPage, int rowsPerPage) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            // Xóa toàn bộ dữ liệu cũ trong bảng
	    dao = new BaoCaoDoanhThu_DAO();
            ArrayList<BaoCaoDoanhThu_enity> dsBaoCao = dao.getAllBaoCaoDoanhThu365Ngay();
	    // Tính tổng số trang
	    totalPages = (int) Math.ceil((double) dsBaoCao.size() / rowsPerPage);

	    // Tính toán chỉ số bắt đầu và kết thúc
	    int start = (currentPage - 1) * rowsPerPage;
	    int end = Math.min(start + rowsPerPage, dsBaoCao.size());

	    // Hiển thị dữ liệu của trang hiện tại
	    for (int i = start; i < end; i++) {
                BaoCaoDoanhThu_enity bc = dsBaoCao.get(i);
                Object[] rowData = {
                    bc.getThoiGian(),
                    String.format("%.2f", bc.getDoanhThu()),
                    String.format("%.2f", bc.getChiPhi()),
                    String.format("%.2f", bc.getLoiNhuan()),
                    bc.getSoLuongGD()
                };
                model.addRow(rowData);
            }
	    lblPageIndicator.setText(currentPage + " / " + totalPages);
	}
    
    public void loadDataToanTG(int currentPage, int rowsPerPage) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            // Xóa toàn bộ dữ liệu cũ trong bảng
	    dao = new BaoCaoDoanhThu_DAO();
            ArrayList<BaoCaoDoanhThu_enity> dsBaoCao = dao.getAllBaoCaoDoanhThuToanTG();
	    // Tính tổng số trang
	    totalPages = (int) Math.ceil((double) dsBaoCao.size() / rowsPerPage);

	    // Tính toán chỉ số bắt đầu và kết thúc
	    int start = (currentPage - 1) * rowsPerPage;
	    int end = Math.min(start + rowsPerPage, dsBaoCao.size());

	    // Hiển thị dữ liệu của trang hiện tại
	    for (int i = start; i < end; i++) {
                BaoCaoDoanhThu_enity bc = dsBaoCao.get(i);
                Object[] rowData = {
                    bc.getThoiGian(),
                    String.format("%.2f", bc.getDoanhThu()),
                    String.format("%.2f", bc.getChiPhi()),
                    String.format("%.2f", bc.getLoiNhuan()),
                    bc.getSoLuongGD()
                };
                model.addRow(rowData);
            }
	    lblPageIndicator.setText(currentPage + " / " + totalPages);
	}
    
    private void loadDataBasedOnSelection() {
            String selected = cbTime.getSelectedItem().toString();
            if (selected.equals("7 Ngày Qua")) {
                loadData7Ngay(currentPage, rowsPerPage);
            } else if (selected.equals("30 Ngày Qua")) {
                loadData30Ngay(currentPage, rowsPerPage);
            } else if (selected.equals("90 Ngày Qua")) {
                loadData90Ngay(currentPage, rowsPerPage);
            } else if (selected.equals("365 Ngày Qua")) {
                loadData365Ngay(currentPage, rowsPerPage);
            } else if (selected.equals("Toàn Thời Gian")) {
                loadDataToanTG(currentPage, rowsPerPage);
            } else  {
                loadDataCustomer(selectedStartDate, selectedEndDate, currentPage, rowsPerPage);
            }
        }

    private void xuatExcel() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Lưu File Excel");
    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx", "xls"));

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        if (!fileToSave.getName().endsWith(".xlsx")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("BaoCaoDoanhThu");

            // Tạo header row
            Row headerRow = sheet.createRow(0);
            String[] columnHeaders = {"Thời Gian", "Doanh Thu", "Chi Phí", "Lợi Nhuận", "Số Lượng Giao Dịch"};
            for (int i = 0; i < columnHeaders.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeaders[i]);
            }

            // Lấy dữ liệu từ DAO
            String selected = cbTime.getSelectedItem().toString();
            if(selected.equals("7 Ngày Qua")){
               reportData = dao.getAllBaoCaoDoanhThu7Ngay();
            } else if (selected.equals("30 Ngày Qua")){
               reportData = dao.getAllBaoCaoDoanhThu30Ngay();
            } else if (selected.equals("90 Ngày Qua")){
               reportData = dao.getAllBaoCaoDoanhThu90Ngay();
            } else if (selected.equals("365 Ngày Qua")){
               reportData = dao.getAllBaoCaoDoanhThu365Ngay();
            } else if (selected.equals("Toàn Thời Gian")){
               reportData = dao.getAllBaoCaoDoanhThuToanTG();
            } else {
               reportData = dao.getAllBaoCaoDoanhThuTuyChinh(selectedStartDate, selectedEndDate);
            }

            // Ghi dữ liệu vào file Excel
            int rowIndex = 1;  // Dữ liệu bắt đầu từ hàng 2 (sau header)
            for (BaoCaoDoanhThu_enity item : reportData) {
                Row dataRow = sheet.createRow(rowIndex++);
                
                dataRow.createCell(0).setCellValue(item.getThoiGian() != null ? item.getThoiGian().toString() : "");
                dataRow.createCell(1).setCellValue(item.getDoanhThu());
                dataRow.createCell(2).setCellValue(item.getChiPhi());
                dataRow.createCell(3).setCellValue(item.getLoiNhuan());
                dataRow.createCell(4).setCellValue(item.getSoLuongGD());
            }

            // Tự động điều chỉnh kích thước cột
            for (int i = 0; i < columnHeaders.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi file Excel
            try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    public void xuatPDF() {
        try {
            // Tạo đối tượng Font cho tiêu đề và nội dung
            BaseFont baseFont = BaseFont.createFont("/gui/vi/Times New Roman Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // Đảm bảo đường dẫn chính xác đến file TTF của font
            Font titleFont = new Font(baseFont, 20, Font.BOLD);  // Font cho tiêu đề
            Font contentFont = new Font(baseFont, 12, Font.NORMAL);  // Font cho nội dung bảng
            Font tenQuayFont = new Font(baseFont, 13, Font.BOLD);
            Font thoiGianFont = new Font(baseFont, 13, Font.BOLD);

            // Tạo file PDF
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
                }

                // Khởi tạo đối tượng Document
                Document document = new Document(PageSize.A4);
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileToSave));

                // Mở file PDF
                document.open();

                // Tiêu đề báo cáo
                Paragraph tenQuay = new Paragraph("Quầy thuốc DRUGSOFT", tenQuayFont);
                tenQuay.setAlignment(Element.ALIGN_LEFT);  // Căn trái
                document.add(tenQuay);

                Paragraph title = new Paragraph("Báo cáo doanh thu", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);


                String selected = cbTime.getSelectedItem().toString();
                if(selected.equals("7 Ngày Qua")){
                    Paragraph form = new Paragraph("Từ "+  bayNgayTruoc.format(formatter).toString() + " tới " +ngayHienTai.format(formatter).toString()  ,thoiGianFont);
                    form.setAlignment(Element.ALIGN_CENTER);
                    document.add(form);
                } else if (selected.equals("30 Ngày Qua")){
                    Paragraph form = new Paragraph("Từ "+  bamuoiNgayTruoc.format(formatter).toString() + " tới " +ngayHienTai.format(formatter).toString()  ,thoiGianFont);
                    form.setAlignment(Element.ALIGN_CENTER);
                    document.add(form);
                } else if (selected.equals("90 Ngày Qua")){
                    Paragraph form = new Paragraph("Từ "+  chinmuoiNgayTruoc.format(formatter).toString() + " tới " +ngayHienTai.format(formatter).toString()  ,thoiGianFont);
                    form.setAlignment(Element.ALIGN_CENTER);
                    document.add(form);
                } else if (selected.equals("365 Ngày Qua")){
                   Paragraph form = new Paragraph("Từ "+  basaunamngaytruoc.format(formatter).toString() + " tới " +ngayHienTai.format(formatter).toString()  ,thoiGianFont);
                    form.setAlignment(Element.ALIGN_CENTER);
                    document.add(form);
                } else if (selected.equals("Toàn Thời Gian")){
                    Paragraph form = new Paragraph("Từ trước đến giờ"  ,thoiGianFont);
                    form.setAlignment(Element.ALIGN_CENTER);
                    document.add(form);
                } else {
                    Paragraph form = new Paragraph("Từ "+  formatterForDate.format(selectedStartDate).toString() + " tới " + formatterForDate.format(selectedStartDate).toString()  ,thoiGianFont);
                    form.setAlignment(Element.ALIGN_CENTER);
                    document.add(form);
                }

                document.add(new Paragraph(" "));
                // Lấy dữ liệu từ DAO
                if(selected.equals("7 Ngày Qua")){
                   reportData = dao.getAllBaoCaoDoanhThu7Ngay();
                } else if (selected.equals("30 Ngày Qua")){
                   reportData = dao.getAllBaoCaoDoanhThu30Ngay();
                } else if (selected.equals("90 Ngày Qua")){
                   reportData = dao.getAllBaoCaoDoanhThu90Ngay();
                } else if (selected.equals("365 Ngày Qua")){
                   reportData = dao.getAllBaoCaoDoanhThu365Ngay();
                } else if (selected.equals("Toàn Thời Gian")){
                   reportData = dao.getAllBaoCaoDoanhThuToanTG();
                } else {
                   reportData = dao.getAllBaoCaoDoanhThuTuyChinh(selectedStartDate, selectedEndDate);
                }

                // Tạo bảng PDF
                PdfPTable pdfTable = new PdfPTable(5); // 5 cột: Thời gian, Doanh thu, Chi phí, Lợi nhuận, Tổng số lượng giao dịch
                pdfTable.setWidthPercentage(100);
                pdfTable.setSpacingBefore(10f);
                pdfTable.setSpacingAfter(10f);

                // Thêm tiêu đề bảng với font tùy chỉnh
                String[] headers = {"Thời gian", "Doanh thu", "Chi phí", "Lợi nhuận", "Tổng số lượng giao dịch"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Phrase(header, tenQuayFont));  // Sử dụng titleFont cho tiêu đề
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

                // Thêm dữ liệu vào bảng PDF
                DecimalFormat df = new DecimalFormat("#,###");
                for (BaoCaoDoanhThu_enity item : reportData) {
                    pdfTable.addCell(item.getThoiGian() != null ? item.getThoiGian().toString() : "");
                    pdfTable.addCell(df.format(item.getDoanhThu()));
                    pdfTable.addCell(df.format(item.getChiPhi()));
                    pdfTable.addCell(df.format(item.getLoiNhuan()));
                    pdfTable.addCell(String.valueOf(item.getSoLuongGD()));
                }

                // Thêm bảng vào document
                document.add(pdfTable);

                Paragraph thoiGian = new Paragraph("Ngày " + ngayHienTai.getDayOfMonth() + " tháng " +
                                    ngayHienTai.getMonthValue() + " năm " + 
                                    ngayHienTai.getYear(), thoiGianFont);
                thoiGian.setAlignment(Element.ALIGN_RIGHT); // Căn giữa đoạn "Ngày...tháng...năm..."
                thoiGian.setIndentationRight(10f);
                document.add(thoiGian);

                // Thêm "Người lập báo cáo"
                Paragraph nguoiLap = new Paragraph("Người lập báo cáo", thoiGianFont);
                nguoiLap.setAlignment(Element.ALIGN_RIGHT); // Căn giữa
                nguoiLap.setIndentationRight(40f);
                document.add(nguoiLap);

                // Thêm "(Ký họ tên)"
                Paragraph kyTen = new Paragraph("(Ký họ tên)", thoiGianFont);
                kyTen.setAlignment(Element.ALIGN_RIGHT); // Căn giữa
                kyTen.setIndentationRight(55f);
                document.add(kyTen);
                
                // Đóng tài liệu PDF
                document.close();

                // Thông báo thành công
                JOptionPane.showMessageDialog(null, "Đã xuất file PDF thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDialog;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JComboBox<String> cbTime;
    private javax.swing.JDialog dlTuyChinh;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPageIndicator;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JTable table;
    private com.toedter.calendar.JDateChooser toDate;
    private javax.swing.JButton xuatExcel;
    private javax.swing.JButton xuatPDF;
    // End of variables declaration//GEN-END:variables
}
