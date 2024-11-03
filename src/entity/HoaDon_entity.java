package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class HoaDon_entity {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    private String maHD;                     // Mã hóa đơn
    private LocalDateTime ngayLapHD;              // Ngày lập hóa đơn
    private double tongTien;                  // Tổng tiền
    private double tienGiam;             // Tiền khách trả
    private String hinhThucThanhToan;        // Hình thức thanh toán
    private boolean trangThai;                // Trạng thái hóa đơn
    private String sdtKH;             // Khách hàng
    private String maNV;               // Nhân viên
    private String maLoaiHoaDon;           // Loại hóa đơn
    private String ghiChu;

    public HoaDon_entity(String mahd2, LocalDate lcNgayLapHD, double tongTien2, double tienGiam2, String hinhThucThanhToan2, boolean trangThai2, KhachHang_entity kh, NhanVien_entity nv, LoaiHoaDon_entity lhd) {
    }



    

    
    
    
    public DateTimeFormatter getFormatter() {
		return formatter;
	}








	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}








	public String getMaHD() {
		return maHD;
	}








	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}








	public LocalDateTime getNgayLapHD() {
		return ngayLapHD;
	}








	public void setNgayLapHD(LocalDateTime ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}








	public double getTongTien() {
		return tongTien;
	}








	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}








	public double getTienGiam() {
		return tienGiam;
	}








	public void setTienGiam(double tienGiam) {
		this.tienGiam = tienGiam;
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








	public String getSdtKH() {
		return sdtKH;
	}








	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}








	public String getMaNV() {
		return maNV;
	}








	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}








	public String getMaLoaiHoaDon() {
		return maLoaiHoaDon;
	}








	public void setMaLoaiHoaDon(String maLoaiHoaDon) {
		this.maLoaiHoaDon = maLoaiHoaDon;
	}








	public String getGhiChu() {
		return ghiChu;
	}








	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

















	public HoaDon_entity(String maHD, LocalDateTime ngayLapHD, double tongTien, double tienGiam,
			String hinhThucThanhToan, boolean trangThai, String sdtKH, String maNV, String maLoaiHoaDon,
			String ghiChu) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
		this.tienGiam = tienGiam;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.trangThai = trangThai;
		this.sdtKH = sdtKH;
		this.maNV = maNV;
		this.maLoaiHoaDon = maLoaiHoaDon;
		this.ghiChu = ghiChu;
	}








	public HoaDon_entity(String maHD, LocalDateTime ngayLapHD, String hinhThucThanhToan) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }








	public HoaDon_entity getKhachHang() {
		// TODO Auto-generated method stub
		return null;
	}
}
