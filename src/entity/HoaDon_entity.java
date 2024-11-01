package entity;

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
    private String maKH;             // Khách hàng
    private String maNV;               // Nhân viên
    private String maLoaiHoaDon;           // Loại hóa đơn

    public HoaDon_entity() {
    }

    public HoaDon_entity(String maHD, LocalDateTime ngayLapHD, double tongTien, double tienGiam, String hinhThucThanhToan, boolean trangThai, String maKH, String maNV, String maLoaiHoaDon) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.tongTien = tongTien;
        this.tienGiam = tienGiam;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.trangThai = trangThai;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maLoaiHoaDon = maLoaiHoaDon;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDateTime getNgayLapHD() {
//        return ngayLapHD.format(formatter);
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

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
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

    @Override
    public String toString() {
        return "HoaDon_entity{" + "maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", tongTien=" + tongTien + ", tienGiam=" + tienGiam + ", hinhThucThanhToan=" + hinhThucThanhToan + ", trangThai=" + trangThai + ", maKH=" + maKH + ", maNV=" + maNV + ", maLoaiHoaDon=" + maLoaiHoaDon + '}';
    }

    public HoaDon_entity(String maHD, LocalDateTime ngayLapHD, String hinhThucThanhToan) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }
}
