/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import dao.SanPhamDoiTra_DAO;
import entity.NhapHang_entity;
import entity.SanPhamDoiTra_entity;
import entity.SanPham_entity;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import nguyenvu.components.SimpleForm;
import nguyenvu.utils.ButtonEditor;
import nguyenvu.utils.ButtonRenderer;
import nguyenvu.utils.EditViewButtonEditor;
import nguyenvu.utils.EditViewButtonRenderer;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.RoundedTextField;
import nguyenvu.utils.EditViewButtonEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author HP
 */
public class SanPhamDoiTra extends SimpleForm {
	private SanPhamDoiTra_DAO spdt_DAO;
	private int currentPage = 1;
	private final int rowsPerPage = 9;
	private int totalPages = 0;
	private JLabel lblPageIndicator;
   
    public SanPhamDoiTra() {
    	spdt_DAO = new SanPhamDoiTra_DAO();
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHeader = new javax.swing.JPanel();
        txtSearch = new RoundedTextField(40);
        btnTimKiem = new javax.swing.JButton();
        btnTimKiem.setAction(action);
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnPrevious = new javax.swing.JButton(new FlatSVGIcon("gui/icon/prev.svg"));
        btnNext = new javax.swing.JButton(new FlatSVGIcon("gui/icon/next.svg"));
        btnFirst = new javax.swing.JButton(new FlatSVGIcon("gui/icon/first-page.svg", 0.03f));
        btnLast = new javax.swing.JButton(new FlatSVGIcon("gui/icon/last-page.svg", 0.03f));
        jButton7 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/print.svg", 0.35f));
        jButton8 = new javax.swing.JButton(new FlatSVGIcon("gui/icon/export.svg", 0.3f));

        setPreferredSize(new java.awt.Dimension(1470, 730));

        pnHeader.setBackground(new java.awt.Color(11, 101, 136));
        pnHeader.setPreferredSize(new java.awt.Dimension(1470, 50));

        btnTimKiem.setBackground(new java.awt.Color(11, 101, 136));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton2.setBackground(new java.awt.Color(11, 101, 136));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Scan barcode");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeader.setLayout(pnHeaderLayout);
        pnHeaderLayout.setHorizontalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnHeaderLayout.setVerticalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Tìm sản phẩm");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(11, 101, 136));
        jLabel1.setText("Sản phẩm đổi trả");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đổi trả ", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thành tiền", "Trạng thái", "Thao tác"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
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
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(150);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
        }
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        table.getTableHeader().setBackground(new Color(11,101,136));
        table.getTableHeader().setForeground(Color.WHITE);
        
        table.getColumn("Thao tác").setCellRenderer(new ButtonRenderer("Cập nhật"));
        table.getColumn("Thao tác").setCellEditor(new ButtonEditor("Cập nhật", e -> {
        	try {
		        int row = Integer.parseInt(e.getActionCommand().split("_")[1]);
		        String maDT = (String) table.getValueAt(row, 0);
		        SanPhamDoiTra_entity sp = spdt_DAO.getSanPhamDoiTraByID(maDT);

		        // Hiển thị form cập nhật sản phẩm
		        FormCapNhapSanPhamDoiTra updateForm = new FormCapNhapSanPhamDoiTra();
		        updateForm.AddDataToLable(sp);

		        JDialog dialog = new JDialog();
		        dialog.getContentPane().add(updateForm);
		        dialog.setSize(880, 600);
		        dialog.setLocationRelativeTo(null);
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
		}));
//        loadDataToTable();

		
        jButton7.setBackground(new java.awt.Color(255, 0, 0));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("In PDF");
        jButton7.setText("In PDF");
//      jButton7.addActionListener(e -> {
//          try {
//              JFileChooser fileChooser = new JFileChooser();
//              fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
//              fileChooser.setSelectedFile(new File("sanphamdoi.pdf"));
//
//              int userChoice = fileChooser.showSaveDialog(null);
//              if (userChoice == JFileChooser.APPROVE_OPTION) {
//                  String filePath = fileChooser.getSelectedFile().getAbsolutePath();
//
//                  if (!filePath.endsWith(".pdf")) {
//                      filePath += ".pdf";
//                  }
//
//                  // Khởi tạo tài liệu PDF
//                  Document document = new Document();
//                  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
//                  document.open();
//
//                  // Thêm nội dung tiếng Việt vào PDF (không nhắc đến Font trực tiếp)
//                  PdfContentByte canvas = writer.getDirectContent();
//                  BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//
//                  canvas.beginText();
//                  canvas.setFontAndSize(baseFont, 12);
//                  canvas.showTextAligned(Element.ALIGN_CENTER, "Sản phẩm đổi trả", 300, 800, 0); // Vị trí: (300, 800)
//                  canvas.endText();
//
//                  // Đóng tài liệu
//                  document.close();
//
//                  JOptionPane.showMessageDialog(null, "File PDF đã được lưu tại: " + filePath);
//              }
//          } catch (Exception ex) {
//              ex.printStackTrace();
//              JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + ex.getMessage());
//          }
//      });


        jButton8.setBackground(new java.awt.Color(51, 204, 0));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Xuất File");
        jButton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                exportToExcel();
            }

         // Phương thức exportToExcel
//            private void exportToExcel() {
//                try (XSSFWorkbook workbook = new XSSFWorkbook()) { // Tạo workbook
//                    Sheet sheet = workbook.createSheet("Sản phẩm đổi trả");
//
//                    // Tiêu đề chính: NHÀ THUỐC AN TÂM
//                    Row titleRow = sheet.createRow(0); // Dòng đầu tiên
//                    Cell titleCell = titleRow.createCell(0);
//                    titleCell.setCellValue("NHÀ THUỐC AN TÂM");
//                    titleCell.setCellValue("SẢN PHẨM ĐỔI TRẢ");
//                    // Style cho tiêu đề chính
//                    CellStyle titleStyle = workbook.createCellStyle();
//                    Font titleFont = workbook.createFont();
//                    titleFont.setBold(true);
//                    titleFont.setFontHeightInPoints((short) 14); // Kích thước chữ lớn hơn
//                    titleStyle.setFont(titleFont);
//                    titleStyle.setAlignment(HorizontalAlignment.CENTER);
//                    titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//                    titleStyle.setBorderTop(BorderStyle.THIN);
//                    titleStyle.setBorderBottom(BorderStyle.THIN);
//                    titleStyle.setBorderLeft(BorderStyle.THIN);
//                    titleStyle.setBorderRight(BorderStyle.THIN);
//
//                    // Dòng phụ: SẢN PHẨM ĐỔI TRẢ
//                    Row subtitleRow = sheet.createRow(1); // Dòng thứ hai
//                    Cell subtitleCell = subtitleRow.createCell(0);
//                    subtitleCell.setCellValue("SẢN PHẨM ĐỔI TRẢ");
//
//                    // Style cho dòng phụ
//                    CellStyle subtitleStyle = workbook.createCellStyle();
//                    Font subtitleFont = workbook.createFont();
//                    subtitleFont.setBold(true);
//                    subtitleStyle.setFont(subtitleFont);
//                    subtitleStyle.setAlignment(HorizontalAlignment.CENTER);
//                    subtitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//
//                    // Gộp các ô để NHÀ THUỐC AN TÂM và SẢN PHẨM ĐỔI TRẢ nằm trong cùng một khung
//                    sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 6)); // Gộp từ dòng 0 đến dòng 1, cột 0 đến cột 6
//                    titleCell.setCellStyle(titleStyle);
//                    subtitleCell.setCellStyle(titleStyle); // Sử dụng cùng style với tiêu đề chính
//
//                    // Hiển thị ngày, giờ xuất Excel
//                    Row dateTimeRow = sheet.createRow(2); // Dòng thứ ba
//                    Cell dateTimeCell = dateTimeRow.createCell(6); // Ô cuối cùng (cột 6)
//                    String dateTime = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")); // Định dạng ngày giờ
//                    dateTimeCell.setCellValue("Thời gian: " + dateTime);
//
//                    // Style cho ngày giờ
//                    CellStyle dateTimeStyle = workbook.createCellStyle();
//                    Font dateTimeFont = workbook.createFont();
//                    dateTimeFont.setItalic(true);
//                    dateTimeStyle.setFont(dateTimeFont);
//                    dateTimeStyle.setAlignment(HorizontalAlignment.RIGHT);
//                    dateTimeStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//                    dateTimeCell.setCellStyle(dateTimeStyle);
//
//                    // Tạo header (dòng 4)
//                    Row headerRow = sheet.createRow(3); // Dòng thứ tư
//                    String[] headers = {   "Mã sản phẩm", "Mã đổi trả", "Tên sản phẩm", "Số lượng", "Chiết khấu", "Thành tiền", "Loại đổi trả","Trạng thái", "Thao tác" };
//                    
//                    CellStyle headerStyle = workbook.createCellStyle();
//                    Font headerFont = workbook.createFont();
//                    headerFont.setBold(true);
//                    headerStyle.setFont(headerFont);
//                    headerStyle.setAlignment(HorizontalAlignment.CENTER);
//                    headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//                    headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex()); // Màu xanh nhạt
//                    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                    headerStyle.setBorderTop(BorderStyle.THIN);
//                    headerStyle.setBorderBottom(BorderStyle.THIN);
//                    headerStyle.setBorderLeft(BorderStyle.THIN);
//                    headerStyle.setBorderRight(BorderStyle.THIN);
//
//                    for (int i = 0; i < headers.length; i++) {
//                        Cell cell = headerRow.createCell(i);
//                        cell.setCellValue(headers[i]);
//                        cell.setCellStyle(headerStyle);
//                    }
//
//                    // Điền dữ liệu
//                    List<entity.SanPhamDoiTra_entity> listSPDT = sPhamDoiTra_DAO.getAllSanPhamDoiTra();
//                    int rowNum = 4; // Bắt đầu từ dòng thứ năm
//                    CellStyle cellStyle = workbook.createCellStyle();
//                    cellStyle.setBorderTop(BorderStyle.THIN);
//                    cellStyle.setBorderBottom(BorderStyle.THIN);
//                    cellStyle.setBorderLeft(BorderStyle.THIN);
//                    cellStyle.setBorderRight(BorderStyle.THIN);
//
//                    for (SanPhamDoiTra_entity sp : listSPDT) {
//                        Row row = sheet.createRow(rowNum++);
//
//                        Cell cell1 = row.createCell(0);
//                        cell1.setCellValue(sp.getMaSP() != null ? sp.getMaSP() : "");
//                        cell1.setCellStyle(cellStyle);
//                        
//
//                        Cell cell0 = row.createCell(1);
//                        cell0.setCellValue(sp.getMaDT() != null ? sp.getMaDT() : "");
//                        cell0.setCellStyle(cellStyle);
//
//                        Cell cell2 = row.createCell(2);
//                        cell2.setCellValue(sp.getTenSP());
//                        cell2.setCellStyle(cellStyle);
//
//                        Cell cell3 = row.createCell(3);
//                        cell3.setCellValue(sp.getSoLuong());
//                        cell3.setCellStyle(cellStyle);
//
//                        Cell cell4 = row.createCell(4);
//                        cell4.setCellValue(sp.getChietKhau());
//                        cell4.setCellStyle(cellStyle);
//
//                        Cell cell5 = row.createCell(5);
//                        cell5.setCellValue(sp.getThanhTien());
//                        cell5.setCellStyle(cellStyle);
//
//                    
//                        Cell cell6 = row.createCell(6);
//                        cell6.setCellValue(sp.getLoaiDoiTra());
//                        cell6.setCellStyle(cellStyle);
//                        
//                        Cell cell7 = row.createCell(7);
//                        cell7.setCellValue(sp.getLoaiDoiTra());
//                        cell7.setCellStyle(cellStyle);
//                        
//                        Cell cell8 = row.createCell(8);
//                        cell8.setCellValue("Xem");
//                        cell8.setCellStyle(cellStyle);
//                        
//
//             
//                    }
//
//                    // Điều chỉnh cột
//                    for (int i = 0; i < headers.length; i++) {
//                        sheet.autoSizeColumn(i);
//                    }
//
//                    // Chọn nơi lưu file
//                    JFileChooser fileChooser = new JFileChooser();
//                    fileChooser.setDialogTitle("Lưu file Excel");
//                    fileChooser.setSelectedFile(new File("SanPhamDoiTra.xlsx"));
//                    int userChoice = fileChooser.showSaveDialog(null);
//
//                    if (userChoice == JFileChooser.APPROVE_OPTION) {
//                        File file = fileChooser.getSelectedFile();
//                        try (FileOutputStream fileOut = new FileOutputStream(file)) {
//                            workbook.write(fileOut);
//                            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
//                        } catch (IOException ex) {
//                            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file: " + ex.getMessage());
//                            ex.printStackTrace();
//                        }
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
//                }
//            }
//          
        });

        
        lblPageIndicator = new JLabel("New label");
        lblPageIndicator.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        docDuLieuVaoTable(currentPage, rowsPerPage);

        btnFirst.addActionListener(e -> {
	        currentPage = 1;
	        docDuLieuVaoTable(currentPage, rowsPerPage);
	    });

                btnPrevious.addActionListener(e -> {
                    if (currentPage > 1) {
                        currentPage--;
                        docDuLieuVaoTable(currentPage, rowsPerPage);
                    }
                });

                btnNext.addActionListener(e -> {
                    if (currentPage < totalPages) {
                        currentPage++;
                        docDuLieuVaoTable(currentPage, rowsPerPage);
                    }
                });

	    btnLast.addActionListener(e -> {
	        currentPage = totalPages;
	        docDuLieuVaoTable(currentPage, rowsPerPage);
	    });
	    btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = txtSearch.getText().trim();
				if (searchText.equals("") || searchText.equals("Nhập Tên Hoặc Mã Sản Phẩm")) {
					docDuLieuVaoTable(currentPage, rowsPerPage);
				} else {
					DefaultTableModel modal = (DefaultTableModel) table.getModel();
					modal.setRowCount(0);
					List<SanPhamDoiTra_entity> filteredProducts = spdt_DAO.timKiemSanPham(searchText);
					for (SanPhamDoiTra_entity data : filteredProducts) {
						modal.addRow(new Object[] {data.getMaDT(),data.getMaSP(),data.getTenSP(),data.getSoLuong(),data.getThanhTien(),data.getTrangThai()});
					}
				}
				
			}
		});
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(pnHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnFirst, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblPageIndicator)
        					.addGap(21)
        					.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(1229, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
        					.addGap(18)
        					.addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        					.addGap(10))))

        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(pnHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        			.addGap(45)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(lblPageIndicator, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnPrevious, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        				.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
        				.addComponent(btnFirst, 0, 0, Short.MAX_VALUE)
        				.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap(129, Short.MAX_VALUE))

        );
        this.setLayout(layout);

        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
    }

    private void loadDataToTable() {
        // Lấy mô hình của bảng
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<SanPhamDoiTra_entity> list = spdt_DAO.getAllSanPhamDoiTra();
        // Xóa dữ liệu cũ
        model.setRowCount(0);

        // Duyệt qua danh sách và thêm từng dòng vào bảng"Mã đổi trả ", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thành tiền", "Trạng thái", "Thao tác"
        for (SanPhamDoiTra_entity data : list) {
            model.addRow(new Object[] {data.getMaDT(),data.getMaSP(),data.getTenSP(),data.getSoLuong(),data.getThanhTien(),data.getTrangThai()});
        }
    }

//    private void loadDataToTable() {
//        // Lấy mô hình của bảng
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        ArrayList<SanPhamDoiTra_entity> list = spdt_DAO.getAllSanPhamDoiTra();
//        // Xóa dữ liệu cũ
//        model.setRowCount(0);
//
//        // Duyệt qua danh sách và thêm từng dòng vào bảng"Mã đổi trả ", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thành tiền", "Trạng thái", "Thao tác"
//        for (SanPhamDoiTra_entity data : list) {
//            model.addRow(new Object[] {data.getMaDT(),data.getMaSP(),data.getTenSP(),data.getSoLuong(),data.getThanhTien(),data.getTrangThai()});
//        }
//    }

	public void docDuLieuVaoTable(int currentPage, int rowsPerPage) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<SanPhamDoiTra_entity> list = spdt_DAO.getAllSanPhamDoiTra();
        // Xóa dữ liệu cũ
        model.setRowCount(0);


	    // Tính tổng số trang
	    totalPages = (int) Math.ceil((double) list.size() / rowsPerPage);

	    // Tính toán chỉ số bắt đầu và kết thúc
	    int start = (currentPage - 1) * rowsPerPage;
	    int end = Math.min(start + rowsPerPage, list.size());

	    // Hiển thị dữ liệu của trang hiện tại
	    for (int i = start; i < end; i++) {
	    	SanPhamDoiTra_entity data = list.get(i);
	        model.addRow(new Object[] {data.getMaDT(),data.getMaSP(),data.getTenSP(),data.getSoLuong(),data.getThanhTien(),data.getTrangThai()});
	    }
	    lblPageIndicator.setText(currentPage + " / " + totalPages);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    private final Action action = new SwingAction();
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
