package entity;

import java.time.LocalDate;

public class NhapHang_entity {
	private String maNhapHang;
	private LocalDate ngayNhapHang;
	private double tongTien;
	private String ghiChu;
	private String trangThai;
	private String hinhThucThanhToan;
	public NhapHang_entity() {
		super();
	}
	
	public NhapHang_entity(String maNhapHang) {
		super();
		this.maNhapHang = maNhapHang;
	}

	public NhapHang_entity(String maNhapHang, LocalDate ngayNhapHang, double tongTien, String ghiChu, String trangThai,String hinhThucThanhToan) {
		super();
		this.maNhapHang = maNhapHang;
		this.ngayNhapHang = ngayNhapHang;
		this.tongTien = tongTien;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
		this.hinhThucThanhToan = hinhThucThanhToan;
	}
	public String getMaNhapHang() {
		return maNhapHang;
	}
	public void setMaNhapHang(String maNhapHang) {
		this.maNhapHang = maNhapHang;
	}
	public LocalDate getNgayNhapHang() {
		return ngayNhapHang;
	}
	public void setNgayNhapHang(LocalDate ngayNhapHang) {
		this.ngayNhapHang = ngayNhapHang;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}
	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}
	@Override
	public String toString() {
		return "NhapHang_entity [maNhapHang=" + maNhapHang + ", ngayNhapHang=" + ngayNhapHang + ", tongTien=" + tongTien
				+ ", ghiChu=" + ghiChu + ", trangThai=" + trangThai + ", hinhThucThanhToan=" + hinhThucThanhToan + "]";
	}
	
	
	
}
