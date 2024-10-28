package entity;

public class ChiTietHoaDon {
    private String maCTHD; // Mã chi tiết hóa đơn
    private int soLuongSanPham; // Số lượng sản phẩm
    private double thanhTien; // Tổng tiền
    private HoaDon_entity hoaDon; // Hóa đơn liên kết
    private SanPham_entity sanPham; // Sản phẩm liên kết

    // Constructor mặc định
    public ChiTietHoaDon() {
    }

    // Constructor với đầy đủ tham số
    public ChiTietHoaDon(String maCTHD, int soLuongSanPham, double thanhTien, HoaDon_entity hoaDon, SanPham_entity sanPham) {
        this.maCTHD = maCTHD;
        this.soLuongSanPham = soLuongSanPham;
        this.thanhTien = thanhTien;
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
    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    // Getter và Setter cho hoaDon
    public HoaDon_entity getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon_entity hoaDon) {
        this.hoaDon = hoaDon;
    }

    // Getter và Setter cho sanPham
    public SanPham_entity getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham_entity sanPham) {
        this.sanPham = sanPham;
    }

	@Override
	public String toString() {
		return "ChiTietHoaDon [maCTHD=" + maCTHD + ", soLuongSanPham=" + soLuongSanPham + ", thanhTien=" + thanhTien
				+ ", hoaDon=" + hoaDon + ", sanPham=" + sanPham + "]";
	}


}
