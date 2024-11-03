package ThongKeReport;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {
	private static ReportManager instance;
	private JasperReport reportProduct;
	
	public static ReportManager getInstance() {
		if(instance == null) {
			instance = new ReportManager();
		}
		return instance;
	}
	private ReportManager() {
		// TODO Auto-generated constructor stub
	}
	public void compileReport() throws JRException {
		reportProduct = JasperCompileManager.compileReport(getClass().getResourceAsStream("/ThongKeReport/dsspHetHan.jrxml"));
		
	}
	public void printReportProduct(SPHetHanReport data) throws JRException {
		Map para = new HashMap();
		para.put("Mã Sản Phẩm",data.getMaSP());
		para.put("Tên Sản Phẩm",data.getTenSP());
		para.put("Số Lượng", data.getSoLuong());
		para.put("Ngày Sản Xuất", data.getNgaySanXuat());
		para.put("Ngày Hết Hạn", data.getNgayHetHan());
		para.put("Khối Lượng", data.getKhoiLuong());
		para.put("Đơn Vị Tính", data.getDonViTinh());
		para.put("Nhà Cung Cấp", data.getNhaCungCap());
		para.put("Gía", data.getGia());
		para.put("Thành Phần", data.getThanhPhan());
		para.put("Công Dụng", data.getCongDung());
		para.put("Hình Ảnh", data.getHinhAnh());
		para.put("Mã Loại", data.getLoaiSP());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getSanPham());
		JasperPrint print = JasperFillManager.fillReport(reportProduct, para,dataSource);
		view(print);
	}
	private void view(JasperPrint print) {
		JasperViewer.viewReport(print,false);
	}

}
