package entity;

import java.time.LocalDate;

public class SanPham_entity {
    private String maSP;              // Mã sản phẩm
    private String tenSP;             // Tên sản phẩm
    private LocalDate ngaySanXuat;    // Ngày sản xuất
    private LocalDate ngayHetHan;     // Ngày hết hạn
    private double khoiLuong;			// Khối lượng
    private String donViTinh; 			// Đơn vị tính
    private String nhaCungCap;        // Nhà cung cấp
    private double gia;                // Giá sản phẩm
    private String thanhPhan;
    private String congDung;           // Công dụng
    private String hinhAnhSP;         // Hình ảnh sản phẩm
    private LoaiSanPham_entity loaiSanPham;  // Loại sản phẩm
    private int soLuong;
	public SanPham_entity() {
		super();
	}
	public SanPham_entity(String maSP, String tenSP, LocalDate ngaySanXuat, LocalDate ngayHetHan, double khoiLuong,
			String donViTinh, String nhaCungCap, double gia, String thanhPhan, String congDung, String hinhAnhSP,
			LoaiSanPham_entity loaiSanPham, int soLuong) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.ngaySanXuat = ngaySanXuat;
		this.ngayHetHan = ngayHetHan;
		this.khoiLuong = khoiLuong;
		this.donViTinh = donViTinh;
		this.nhaCungCap = nhaCungCap;
		this.gia = gia;
		this.thanhPhan = thanhPhan;
		this.congDung = congDung;
		this.hinhAnhSP = hinhAnhSP;
		this.loaiSanPham = loaiSanPham;
		this.soLuong = soLuong;
	}
	public SanPham_entity(String maSP) {
		super();
		this.maSP = maSP;
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
	public String getHinhAnhSP() {
		return hinhAnhSP;
	}
	public void setHinhAnhSP(String hinhAnhSP) {
		this.hinhAnhSP = hinhAnhSP;
	}
	public LoaiSanPham_entity getLoaiSanPham() {
		return loaiSanPham;
	}
	public void setLoaiSanPham(LoaiSanPham_entity loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public SanPham_entity(String hinhAnhSP, String maSP, String tenSP, String donViTinh, int soLuong, double gia) {
		super();
		this.hinhAnhSP = hinhAnhSP;
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.gia = gia;
	}
	@Override
	public String toString() {
		return "SanPham_entity [maSP=" + maSP + ", tenSP=" + tenSP + ", ngaySanXuat=" + ngaySanXuat + ", ngayHetHan="
				+ ngayHetHan + ", khoiLuong=" + khoiLuong + ", donViTinh=" + donViTinh + ", nhaCungCap=" + nhaCungCap
				+ ", gia=" + gia + ", thanhPhan=" + thanhPhan + ", congDung=" + congDung + ", hinhAnhSP=" + hinhAnhSP
				+ ", loaiSanPham=" + loaiSanPham + ", soLuong=" + soLuong + "]";
	}
    
    

}
