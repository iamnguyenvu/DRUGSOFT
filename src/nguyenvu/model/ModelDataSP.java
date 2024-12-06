package nguyenvu.model;

public class ModelDataSP {
	private String tenSP;
	private int tongSoLuong;
	public ModelDataSP(String tenSP, int tongSoLuong) {
		super();
		this.tenSP = tenSP;
		this.tongSoLuong = tongSoLuong;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getTongSoLuong() {
		return tongSoLuong;
	}
	public void setTongSoLuong(int tongSoLuong) {
		this.tongSoLuong = tongSoLuong;
	}
	public ModelDataSP() {
		super();
	}
	

}
