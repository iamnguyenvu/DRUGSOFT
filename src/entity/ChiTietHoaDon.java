package entity;

public class ChiTietHoaDon {
    private String maCTHD; // Mã chi tiết hóa đơn
    private int soLuongSanPham; // Số lượng sản phẩm
    private double tongTien; // Tổng tiền
    private HoaDon hoaDon; // Hóa đơn liên kết
    private SanPham sanPham; // Sản phẩm liên kết

    // Constructor mặc định
    public ChiTietHoaDon() {
    }

    // Constructor với đầy đủ tham số
    public ChiTietHoaDon(String maCTHD, int soLuongSanPham, double tongTien, HoaDon hoaDon, SanPham sanPham) {
        this.maCTHD = maCTHD;
        this.soLuongSanPham = soLuongSanPham;
        this.tongTien = tongTien;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }

    // Getter và Setter cho maCTHD
    public String getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(String maCTHD) {
        this.maCTHD = maCTHD;
    }

    // Getter và Setter cho soLuongSanPham
    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    // Getter và Setter cho tongTien
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    // Getter và Setter cho hoaDon
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    // Getter và Setter cho sanPham
    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "maCTHD='" + maCTHD + '\'' +
                ", soLuongSanPham=" + soLuongSanPham +
                ", tongTien=" + tongTien +
                ", hoaDon=" + (hoaDon != null ? hoaDon.getMaHD() : "null") +
                ", sanPham=" + (sanPham != null ? sanPham.getTenSP() : "null") +
                '}';
    }
}
