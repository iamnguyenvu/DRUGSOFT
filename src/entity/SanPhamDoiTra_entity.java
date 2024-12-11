package entity;

public class SanPhamDoiTra_entity {
	private String maDT;
	private String maSP;
	private int soLuong;
	private double thanhTien;
	private String trangThai;
	private String tenSP;
	
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public SanPhamDoiTra_entity(String maDT, String maSP, int soLuong, double thanhTien, String trangThai,
			String tenSP) {
		super();
		this.maDT = maDT;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		this.trangThai = trangThai;
		this.tenSP = tenSP;
	}
	public SanPhamDoiTra_entity() {
		super();
	}
	public SanPhamDoiTra_entity(String maDT, String maSP, int soLuong, double thanhTien, String trangThai) {
		super();
		this.maDT = maDT;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		this.trangThai = trangThai;
	}
	public String getMaDT() {
		return maDT;
	}
	public void setMaDT(String maDT) {
		this.maDT = maDT;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	

}
