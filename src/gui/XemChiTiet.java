package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entity.HoaDon_entity;
import entity.SanPham_entity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import dao.HoaDon_DAO;
import dao.SanPham_DAO;
import dao.ChiTietHoaDon_DAO;
import entity.ChiTietHoaDon;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XemChiTiet extends JFrame {

    private HoaDon_DAO hoaDonDao;
    private ChiTietHoaDon_DAO chiTietHoaDonDao;
    private SanPham_DAO sanPhamDao;
    private JLabel lblMaHD, lblNgayLap, lblTongTien, lblTienGiam, lblHTTT, lblTrangThai, lblMaKH, lblMaNV;
    private JTable table;
    private DefaultTableModel tableModel;
	private JLabel lblMahd;
	private JLabel lblNgaylap;
	private JLabel lblHttt;
	private JLabel lblTt;
	private JLabel lblkh;
	private JLabel lblNV;
	private JLabel lblTG;
	private JLabel lblTT;

    public XemChiTiet(String maHD) {
        hoaDonDao = new HoaDon_DAO();
        chiTietHoaDonDao = new ChiTietHoaDon_DAO();
        
        sanPhamDao = new SanPham_DAO();

        HoaDon_entity hoaDon = hoaDonDao.getHoaDonByMa(maHD);
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với mã: " + maHD, "Lỗi", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        initUI();
        populateInvoiceDetails(hoaDon);
        loadInvoiceItems(maHD);
    }
 
    private void populateInvoiceDetails(HoaDon_entity hoaDon) {
    	lblMahd.setText(hoaDon.getMaHD());
    	lblNgaylap.setText(hoaDon.getNgayLapHD()+"");
    	lblHttt.setText(hoaDon.getHinhThucThanhToan());
    	lblTt.setText(hoaDon.isTrangThai()+"");
    	lblkh.setText(hoaDon.getSdtKH());
    	lblNV.setText(hoaDon.getMaNV());
    	lblTG.setText(hoaDon.getTienGiam()+"");
    	lblTT.setText(hoaDon.getTongTien()+"");
	}

	private void initUI() {
        setTitle("Chi Tiết Hóa Đơn");
        setSize(987, 558);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng","Đơn giá" }, 0);
        table = createTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 206, 619, 311);
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 255, 255));
        leftPanel.setLayout(null);
        leftPanel.add(scrollPane);

        JPanel rightPanel = createDetailsPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        
                JLabel label = new JLabel("Mã Hóa Đơn:");
                label.setFont(new Font("Serif", Font.PLAIN, 15));
                label.setBounds(10, 24, 102, 50);
                leftPanel.add(label);
                
                        JLabel label_1 = new JLabel("Ngày Lập:");
                        label_1.setFont(new Font("Serif", Font.PLAIN, 15));
                        label_1.setBounds(10, 84, 102, 50);
                        leftPanel.add(label_1);
                                        
                                                JLabel label_4 = new JLabel("Hình Thức Thanh Toán:");
                                                label_4.setFont(new Font("Serif", Font.PLAIN, 15));
                                                label_4.setBounds(316, 24, 152, 50);
                                                leftPanel.add(label_4);
                                                
                                                        JLabel label_5 = new JLabel("Trạng Thái:");
                                                        label_5.setFont(new Font("Serif", Font.PLAIN, 15));
                                                        label_5.setBounds(316, 84, 86, 50);
                                                        leftPanel.add(label_5);
                                                        
                                                        lblMahd = new JLabel("New label");
                                                        lblMahd.setFont(new Font("Serif", Font.PLAIN, 15));
                                                        lblMahd.setBounds(148, 32, 158, 34);
                                                        leftPanel.add(lblMahd);
                                                        
                                                        lblNgaylap = new JLabel("New label");
                                                        lblNgaylap.setFont(new Font("Serif", Font.PLAIN, 15));
                                                        lblNgaylap.setBounds(148, 88, 158, 34);
                                                        leftPanel.add(lblNgaylap);
                                                        
                                                        lblHttt = new JLabel("New label");
                                                        lblHttt.setFont(new Font("Serif", Font.PLAIN, 15));
                                                        lblHttt.setBounds(478, 32, 158, 34);
                                                        leftPanel.add(lblHttt);
                                                        
                                                        lblTt = new JLabel("New label");
                                                        lblTt.setFont(new Font("Serif", Font.PLAIN, 15));
                                                        lblTt.setBounds(478, 88, 158, 34);
                                                        leftPanel.add(lblTt);
        splitPane.setBounds(0, 0, 977, 529);
        splitPane.setDividerLocation(650);
        splitPane.setResizeWeight(0.67);
        getContentPane().setLayout(null);

        getContentPane().add(splitPane);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 10, 10);
        getContentPane().add(panel);
        setVisible(true);
    }

//    private void printInvoice(String maHD) {
//        try {
//        	  String jrxmlPath = "D:\\Student\\HK1 2024-2025\\PTUD2\\DRUGSOFT\\src\\HistoryDetail\\InvoiceReport.jrxml";
//              String jasperPath = "D:\\Student\\HK1 2024-2025\\PTUD2\\DRUGSOFT\\src\\HistoryDetail\\InvoiceReport.jasper";
//
//
//            File jasperFile = new File(jasperPath);
//            if (!jasperFile.exists() || jasperFile.lastModified() < new File(jrxmlPath).lastModified()) {
//                JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
//            }
//
//            HoaDon_entity hoaDon = hoaDonDao.getHoaDonByMa(maHD);
//            ArrayList<ChiTietHoaDon> chiTietList = 
//                (ArrayList<ChiTietHoaDon>) chiTietHoaDonDao.getChiTietHoaDonByMaHD(maHD);
////            String maSP = chiTietList.get(1).getMaSP();
//            
//
//            
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(chiTietList);
//
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("maHD", hoaDon.getMaHD());
//            parameters.put("ngayLap", hoaDon.getNgayLapHD().toString());
//            parameters.put("tongTien", hoaDon.getTongTien());
//            parameters.put("tienGiam", hoaDon.getTienGiam());
//            parameters.put("hinhThucThanhToan", hoaDon.getHinhThucThanhToan());
//            parameters.put("trangThai", hoaDon.isTrangThai() ? "Hoàn Thành" : "Chưa Hoàn Thành");
//            parameters.put("maKH", hoaDon.getSdtKH());
//            parameters.put("maNV", hoaDon.getMaNV());
//            ArrayList<ChiTietHoaDon> chiTietList1 = (ArrayList<ChiTietHoaDon>) chiTietHoaDonDao.getChiTietHoaDonByMaHD(maHD);
//            if (chiTietList != null) {
//                for (ChiTietHoaDon chiTiet : chiTietList1) {
////                   parameters.put("donGia", sanPhamDao.getDonGiaSanPhamByMaSP(chiTiet.getMaSP()));
////                   parameters.put("thanhTien", chiTiet.getThanhTien());
////                	DUYET QUA BẢNG
//                	
////                	parameters.put("donGia", sanPhamDao.getDonGiaSanPhamByMaSP(chiTietList1.get(1).getMaSP()));
//                	    
//						
//                	  
//                	
//                	
// 
//
//                }
//            } 
////            parameters.put("thanhTien", chiTietList.get(5).getThanhTien());
////            parameters.put("donGia", sanPhamDao.getDonGiaSanPhamByMaSP(chiTietList.get(1).getMaSP()));
//            parameters.put("thanhTien", chiTietList.get(5).getThanhTien());
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, parameters, dataSource);
//
//            JasperViewer.viewReport(jasperPrint, false);
//            JasperPrintManager.printReport(jasperPrint, true);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi khi in hóa đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    private JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setBackground(new Color(255, 255, 255));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(11, 101, 136));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 18));

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setGridColor(Color.WHITE);
        table.setSelectionBackground(new Color(173, 216, 230));

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        return table;
    }

    private JPanel createDetailsPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel invoiceInfoPanel = new JPanel();
        invoiceInfoPanel.setBackground(new Color(255, 255, 255));
        invoiceInfoPanel.setBounds(10, 10, 310, 341);
        Border thinBorder = BorderFactory.createLineBorder(new Color(192, 192, 192), 2);
        TitledBorder invoiceBorder = BorderFactory.createTitledBorder(thinBorder, "Thông tin hóa đơn",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), new Color(11, 101, 136));
        mainPanel.setLayout(null);
        invoiceInfoPanel.setBorder(invoiceBorder);
        invoiceInfoPanel.setLayout(null);

        mainPanel.add(invoiceInfoPanel);
        
                JLabel label_2 = new JLabel("Tổng Tiền:");
                label_2.setFont(new Font("Serif", Font.PLAIN, 15));
                label_2.setBounds(10, 285, 86, 46);
                invoiceInfoPanel.add(label_2);
                
                        JLabel label_3 = new JLabel("Tiền Giảm:");
                        label_3.setFont(new Font("Serif", Font.PLAIN, 15));
                        label_3.setBounds(10, 198, 84, 58);
                        invoiceInfoPanel.add(label_3);
                        
                        JLabel lblNewLabel = new JLabel("Khách Hàng:");
                        lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 15));
                        lblNewLabel.setBounds(10, 39, 102, 40);
                        invoiceInfoPanel.add(lblNewLabel);
                        
                        JLabel label_3_1 = new JLabel("Nhân Viên:");
                        label_3_1.setFont(new Font("Serif", Font.PLAIN, 15));
                        label_3_1.setBounds(10, 106, 93, 58);
                        invoiceInfoPanel.add(label_3_1);
                        
                        lblkh = new JLabel("New label");
                        lblkh.setFont(new Font("Serif", Font.PLAIN, 15));
                        lblkh.setBounds(148, 43, 158, 34);
                        invoiceInfoPanel.add(lblkh);
                        
                        lblNV = new JLabel("New label");
                        lblNV.setFont(new Font("Serif", Font.PLAIN, 15));
                        lblNV.setBounds(148, 117, 158, 34);
                        invoiceInfoPanel.add(lblNV);
                        
                        lblTG = new JLabel("New label");
                        lblTG.setFont(new Font("Serif", Font.PLAIN, 15));
                        lblTG.setBounds(147, 208, 158, 34);
                        invoiceInfoPanel.add(lblTG);
                        
                        lblTT = new JLabel("New label");
                        lblTT.setFont(new Font("Serif", Font.PLAIN, 15));
                        lblTT.setBounds(148, 292, 158, 34);
                        invoiceInfoPanel.add(lblTT);
        return mainPanel;
    }


    private void loadInvoiceItems(String maHD) {
        ArrayList<ChiTietHoaDon> chiTietList = (ArrayList<ChiTietHoaDon>) chiTietHoaDonDao.getChiTietHoaDonByMaHD(maHD);
        if (chiTietList != null) {
            for (ChiTietHoaDon chiTiet : chiTietList) {
            	String maSP = chiTiet.getMaSP();
                tableModel.addRow(new Object[]{
                        chiTiet.getMaHD(),
                        chiTiet.getMaSP(),
                        chiTiet.getTenSP(),
                   
                        chiTiet.getSoLuongSanPham(),
//                        SanPham_DAO.getDonGiaSanPhamByMaSP(maSP),
                      
                        String.format("%.2f VND", chiTiet.getGia())
                });
            }
        }
    }

}
