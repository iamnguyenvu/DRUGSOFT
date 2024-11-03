package ThongKeReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import entity.SanPham_entity;

public class SPHetHanReport {
	private String maSP;
	private String tenSP;
	private int soLuong;
	private LocalDate ngaySanXuat;
	private LocalDate ngayHetHan;
	private double khoiLuong;
	private String donViTinh;
	private String nhaCungCap;
	private double gia;
	private String thanhPhan;
	private String congDung;
	private String hinhAnh;
	private String loaiSP;
	
	List<SanPham_entity> sanPham;

	public SPHetHanReport() {
		super();
	}

	public SPHetHanReport(String maSP, String tenSP, int soLuong, LocalDate ngaySanXuat, LocalDate ngayHetHan,
			double khoiLuong, String donViTinh, String nhaCungCap, double gia, String thanhPhan, String congDung,
			String hinhAnh, String loaiSP, List<SanPham_entity> sanPham) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.ngaySanXuat = ngaySanXuat;
		this.ngayHetHan = ngayHetHan;
		this.khoiLuong = khoiLuong;
		this.donViTinh = donViTinh;
		this.nhaCungCap = nhaCungCap;
		this.gia = gia;
		this.thanhPhan = thanhPhan;
		this.congDung = congDung;
		this.hinhAnh = hinhAnh;
		this.loaiSP = loaiSP;
		this.sanPham = sanPham;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
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

	public double getKhoiLuong() {
		return khoiLuong;
	}

	public void setKhoiLuong(double khoiLuong) {
		this.khoiLuong = khoiLuong;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public String getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}

	public String getCongDung() {
		return congDung;
	}

	public void setCongDung(String congDung) {
		this.congDung = congDung;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}

	public List<SanPham_entity> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<SanPham_entity> sanPham) {
		this.sanPham = sanPham;
	}
	

}
