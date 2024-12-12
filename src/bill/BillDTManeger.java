/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bill;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class BillDTManeger {

    private static BillDTManeger instance;

    private JasperReport bill;

    public static BillDTManeger getInstance() {
        if (instance == null) {
            instance = new BillDTManeger();
            try {
                instance.compileBill();  // Ensure the report is compiled when the instance is created
            } catch (JRException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private BillDTManeger() {
    }

    public void compileBill() throws JRException {
        System.out.println("Compiling HoaDon.jrxml...");
        bill = JasperCompileManager.compileReport(getClass().getResourceAsStream("/bill/Blank_A4_2.jrxml"));
        System.out.println("Compilation successful.");
    }

    public void printBill(ParameterBillDT data) throws JRException {
        Map para = new HashMap();
        para.put("ngayLapHD", data.getNgayLapHD());
        para.put("tenNV", data.getTenNV());
        para.put("tenKH", data.getTenKH());
        para.put("SDT", data.getSDT());
        para.put("qrcode", data.getQrcode());
        para.put("tongTienHangTra", data.getTongTienHangTra());
        para.put("tongPhiTraHang", data.getTongPhiTraHang());
        para.put("tienHoan", data.getTienHoan());
        para.put("tongTienDoi", data.getTongTienDoi());
        para.put("giamTru", data.getGiamTru());
        para.put("thanhToan", data.getThanhToan());
        para.put("daThanhToan", data.getThanhToan());
        para.put("ghiChu", data.getGhiChu());
        para.put("maHD", data.getMaHD());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getListFB());
        System.out.println("dataSource");
        JasperPrint print = JasperFillManager.fillReport(bill, para, dataSource);
        System.out.println("print");
        view(print);
    }

    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
