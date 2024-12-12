package entity;

import java.time.LocalDate;

public class ChiTietNhapHang_entity {
	private NhapHang_entity nhapHang;
	private SanPham_entity sanPham;
	private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
	private int soLuong;
	private double thanhTien;
	public ChiTietNhapHang_entity() {
		super();
	}
	
	public ChiTietNhapHang_entity(NhapHang_entity nhapHang, SanPham_entity sanPham, LocalDate ngaySanXuat,
			LocalDate ngayHetHan, int soLuong, double thanhTien) {
		super();
		this.nhapHang = nhapHang;
		this.sanPham = sanPham;
		this.ngaySanXuat = ngaySanXuat;
		this.ngayHetHan = ngayHetHan;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
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
	public LocalDate getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(LocalDate ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public LocalDate getNgayHetHan() {
		return ngayHetHan;
	}
	public void setNgayHetHan(LocalDate ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
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
