package entity;

public class ChiTietHoaDon {
    private String maHD; // Mã chi tiết hóa đơn
    private String maSP;
    private int soLuongSanPham; // Số lượng sản phẩm
    private double thanhTien;

    // Constructor mặc định
    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maHD, String maSP, int soLuongSanPham, double thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuongSanPham = soLuongSanPham;
        this.thanhTien = thanhTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "maHD=" + maHD + ", maSP=" + maSP + ", soLuongSanPham=" + soLuongSanPham + ", thanhTien=" + thanhTien + '}';
    }
}
