package nguyenvu.model;



public class DoanhSoBanHangModalData {

	private String thang;
	private int tongDoanhThu;
	private int tongChiPhi;
	private int loiNhuan;
	public DoanhSoBanHangModalData(String thang, int tongDoanhThu, int tongChiPhi, int loiNhuan) {
		super();
		this.thang = thang;
		this.tongDoanhThu = tongDoanhThu;
		this.tongChiPhi = tongChiPhi;
		this.loiNhuan = loiNhuan;
	}
	public DoanhSoBanHangModalData() {
		super();
	}
	public String getThang() {
		return thang;
	}
	public void setThang(String thang) {
		this.thang = thang;
	}
	public int getTongDoanhThu() {
		return tongDoanhThu;
	}
	public void setTongDoanhThu(int tongDoanhThu) {
		this.tongDoanhThu = tongDoanhThu;
	}
	public int getTongChiPhi() {
		return tongChiPhi;
	}
	public void setTongChiPhi(int tongChiPhi) {
		this.tongChiPhi = tongChiPhi;
	}
	public int getLoiNhuan() {
		return loiNhuan;
	}
	public void setLoiNhuan(int loiNhuan) {
		this.loiNhuan = loiNhuan;
	}
	
   
}
