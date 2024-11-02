package entity;

import java.time.LocalDate;

public class NhanVien_entity {
    private String maNV;                // Mã nhân viên
    private String hotenNV; 
    private String gioiTinh;			// Gioi Tính
    private String sdt;                 // Số điện thoại
    private String cccd;
    private String diaChi;              // Địa chỉ
    private LocalDate ngaySinh;         // Ngày sinh
    private boolean trangThai;          // Trạng thái
    private LocalDate ngayVaoLam;       // Ngày vào làm
    private String maLoaiNhanVien;  // Loại nhân viên
    private String hinhAnhNV;           // Hình ảnh nhân viên

    public NhanVien_entity() {
    }

    public NhanVien_entity(String maNV) {
        this.maNV = maNV;
    }
    
    

    public NhanVien_entity(String maNV, String hotenNV, String gioiTinh, String sdt, String cccd, String diaChi, LocalDate ngaySinh, boolean trangThai, LocalDate ngayVaoLam, String maLoaiNhanVien, String hinhAnhNV) {
        this.maNV = maNV;
        this.hotenNV = hotenNV;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.trangThai = trangThai;
        this.ngayVaoLam = ngayVaoLam;
        this.maLoaiNhanVien = maLoaiNhanVien;
        this.hinhAnhNV = hinhAnhNV;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getMaLoaiNhanVien() {
        return maLoaiNhanVien;
    }

    public void setMaLoaiNhanVien(String maLoaiNhanVien) {
        this.maLoaiNhanVien = maLoaiNhanVien;
    }

    public String getHinhAnhNV() {
        return hinhAnhNV;
    }

    public void setHinhAnhNV(String hinhAnhNV) {
        this.hinhAnhNV = hinhAnhNV;
    }

    @Override
    public String toString() {
        return "NhanVien_entity{" + "maNV=" + maNV + ", hotenNV=" + hotenNV + ", gioiTinh=" + gioiTinh + ", sdt=" + sdt + ", cccd=" + cccd + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh + ", trangThai=" + trangThai + ", ngayVaoLam=" + ngayVaoLam + ", maLoaiNhanVien=" + maLoaiNhanVien + ", hinhAnhNV=" + hinhAnhNV + '}';
    }

    public NhanVien_entity(String maNV, String hotenNV) {
        this.maNV = maNV;
        this.hotenNV = hotenNV;
    }

}
