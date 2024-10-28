package entity;

import java.time.LocalDate;

public class HoaDon_entity {
    private String maHD;                     // Mã hóa đơn
    private LocalDate ngayLapHD;              // Ngày lập hóa đơn
    private double tongTien;                  // Tổng tiền
    private double tienGiam;             // Tiền khách trả
    private String hinhThucThanhToan;        // Hình thức thanh toán
    private boolean trangThai;                // Trạng thái hóa đơn
    private KhachHang_entity khachHang;             // Khách hàng
    private NhanVien_entity nhanVien;               // Nhân viên
    private LoaiHoaDon_entity loaiHoaDon;           // Loại hóa đơn

    // Constructor mặc định
    public HoaDon_entity() {
    }

	public HoaDon_entity(String maHD) {
		super();
		this.maHD = maHD;
	}

	public HoaDon_entity(String maHD, LocalDate ngayLapHD, double tongTien, double tienGiam, String hinhThucThanhToan,
			boolean trangThai, KhachHang_entity khachHang, NhanVien_entity nhanVien, LoaiHoaDon_entity loaiHoaDon) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
		this.tienGiam = tienGiam;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.loaiHoaDon = loaiHoaDon;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDate ngayLapHD) {
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

	public KhachHang_entity getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang_entity khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien_entity getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien_entity nhanVien) {
		this.nhanVien = nhanVien;
	}

	public LoaiHoaDon_entity getLoaiHoaDon() {
		return loaiHoaDon;
	}

	public void setLoaiHoaDon(LoaiHoaDon_entity loaiHoaDon) {
		this.loaiHoaDon = loaiHoaDon;
	}

	@Override
	public String toString() {
		return "HoaDon_entity [maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", tongTien=" + tongTien + ", tienGiam="
				+ tienGiam + ", hinhThucThanhToan=" + hinhThucThanhToan + ", trangThai=" + trangThai + ", khachHang="
				+ khachHang + ", nhanVien=" + nhanVien + ", loaiHoaDon=" + loaiHoaDon + "]";
	}
    

    
}
