package entity;

public class ChiTietNhapHang_entity {
	private NhapHang_entity nhapHang;
	private SanPham_entity sanPham;
	private int soLuong;
	private double thanhTien;
	public ChiTietNhapHang_entity(NhapHang_entity nhapHang, SanPham_entity sanPham, int soLuong, double thanhTien) {
		super();
		this.nhapHang = nhapHang;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
	}
	public ChiTietNhapHang_entity() {
		super();
	}
	public NhapHang_entity getNhapHang() {
		return nhapHang;
	}
	public void setNhapHang(NhapHang_entity nhapHang) {
		this.nhapHang = nhapHang;
	}
	public SanPham_entity getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham_entity sanPham) {
		this.sanPham = sanPham;
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
	

}
