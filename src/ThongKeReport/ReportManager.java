package ThongKeReport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

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

}
