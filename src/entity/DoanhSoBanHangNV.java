package entity;

import java.sql.Timestamp;

public class DoanhSoBanHangNV {
	private String maNV;
	private String hotenNV;
	private Timestamp ngayLapHD;
	private String hinhThucThanhToan;
	private boolean trangThai;
	private String ghiChu;
	private double doanhSo;
	public DoanhSoBanHangNV(String maNV, String hotenNV, Timestamp ngayLapHD, String hinhThucThanhToan,
			boolean trangThai, String ghiChu, double doanhSo) {
		super();
		this.maNV = maNV;
		this.hotenNV = hotenNV;
		this.ngayLapHD = ngayLapHD;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.doanhSo = doanhSo;
	}
	
	public DoanhSoBanHangNV(String hotenNV, double doanhSo) {
		super();
		this.hotenNV = hotenNV;
		this.doanhSo = doanhSo;
	}

	public DoanhSoBanHangNV() {
		super();
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHotenNV() {
		return hotenNV;
	}
	public void setHotenNV(String hotenNV) {
		this.hotenNV = hotenNV;
	}
	public Timestamp getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(Timestamp ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}
	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public double getDoanhSo() {
		return doanhSo;
	}
	public void setDoanhSo(double doanhSo) {
		this.doanhSo = doanhSo;
	}
	
	
	

}
