/*
l * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bill;

import java.util.HashMap;
import java.util.Map;
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
public class BillManeger {
    
    private static BillManeger instance;
    
    private JasperReport bill;
    
    public static BillManeger getInstance() {
        if (instance == null) {
            instance = new BillManeger();
            try {
                instance.compileBill();  // Ensure the report is compiled when the instance is created
            } catch (JRException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    private BillManeger() {
    }
    
    public void compileBill() throws JRException {
        System.out.println("Compiling HoaDon.jrxml...");
        bill = JasperCompileManager.compileReport(getClass().getResourceAsStream("/bill/HoaDon.jrxml"));
        System.out.println("Compilation successful.");
    }

    
    public void printBill(ParameterBill data) throws JRException {
        Map para = new HashMap();
        para.put("ngayLapHD", data.getNgayLapHD());
        para.put("tenNV", data.getTenNV());
        para.put("tenKH", data.getTenKH());
        para.put("SDT", data.getSDT());
        para.put("qrcode", data.getQrcode());
        para.put("tongTien", data.getTongTien());
        para.put("giamTru", data.getGiamTru());
        para.put("maHD", data.getMaHD());
        para.put("diemThuong", data.getDiemThuong());
        para.put("daThanhToan", data.getThanhToan());
        para.put("thanhToan", data.getThanhToan());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getListFB());
        JasperPrint print = JasperFillManager.fillReport(bill, para, dataSource);
        view(print);
    }
    
    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
