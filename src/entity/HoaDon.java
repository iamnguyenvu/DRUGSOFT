package entity;

import java.time.LocalDate;

public class HoaDon {
    private String maHD;                     // Mã hóa đơn
    private LocalDate ngayLapHD;              // Ngày lập hóa đơn
    private double tongTien;                  // Tổng tiền
    private double tienKhachTra;             // Tiền khách trả
    private String hinhThucThanhToan;        // Hình thức thanh toán
    private boolean trangThai;                // Trạng thái hóa đơn
    private KhachHang khachHang;             // Khách hàng
    private NhanVien nhanVien;               // Nhân viên
    private LoaiHoaDon loaiHoaDon;           // Loại hóa đơn
    private String ghiChu;                    // Ghi chú

    // Constructor mặc định
    public HoaDon() {
    }

    // Constructor với đầy đủ tham số
    public HoaDon(String maHD, LocalDate ngayLapHD, double tongTien,
                  double tienKhachTra, String hinhThucThanhToan,
                  boolean trangThai, KhachHang khachHang,
                  NhanVien nhanVien, LoaiHoaDon loaiHoaDon, String ghiChu) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.tongTien = tongTien;
        this.tienKhachTra = tienKhachTra;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.trangThai = trangThai;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.loaiHoaDon = loaiHoaDon;
        this.ghiChu = ghiChu;
    }

    // Getter và Setter cho maHD
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    // Getter và Setter cho ngayLapHD
    public LocalDate getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(LocalDate ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    // Getter và Setter cho tongTien
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    // Getter và Setter cho tienKhachTra
    public double getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(double tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }

    // Getter và Setter cho hinhThucThanhToan
    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    // Getter và Setter cho trangThai
    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Getter và Setter cho khachHang
    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    // Getter và Setter cho nhanVien
    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    // Getter và Setter cho loaiHoaDon
    public LoaiHoaDon getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(LoaiHoaDon loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    // Getter và Setter cho ghiChu
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD='" + maHD + '\'' +
                ", ngayLapHD=" + ngayLapHD +
                ", tongTien=" + tongTien +
                ", tienKhachTra=" + tienKhachTra +
                ", hinhThucThanhToan='" + hinhThucThanhToan + '\'' +
                ", trangThai=" + trangThai +
                ", khachHang=" + khachHang +
                ", nhanVien=" + nhanVien +
                ", loaiHoaDon=" + loaiHoaDon +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }

    // Tính thuế (giả sử thuế là 10% của tổng tiền)
    public double tinhThue() {
        return this.tongTien * 0.1;
    }

    // Tính thành tiền
    public double tinhThanhTien() {
        return this.tongTien + tinhThue();
    }

    // Tính tổng tiền (đã bao gồm thuế)
    public double tinhTongTien() {
        return tinhThanhTien();
    }

    // Tính điểm thưởng (giả sử mỗi 100,000 VNĐ được 1 điểm)
    public double tinhDiemThuong() {
        return this.tongTien / 100000;
    }

    // Tính tiền thừa (tiền khách trả - tổng tiền)
    public double tinhTienThua() {
        return this.tienKhachTra - tinhTongTien();
    }
}
