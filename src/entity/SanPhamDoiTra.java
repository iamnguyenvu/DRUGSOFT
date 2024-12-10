package entity;
import java.time.LocalDate;

public class SanPhamDoiTra {
    private String maSP;          // Mã sản phẩm
    private String maDT;          // Mã hóa đơn
    private int soLuong;          // Số lượng đổi trả
//    private String vanDe;         // Vấn đề đổi trả
    private String tenSP;         // Tên sản phẩm
    private LocalDate ngayDoiTra; // Ngày đổi trả
    private String trangThai;    // Trạng thái đổi trả (true: Hoàn thành, false: Chưa xử lý)
    private double chietKhau;     // Chiết khấu (lựa chọn từ combobox)
   // private String tinhTrang;     // Tình trạng đổi trả (lựa chọn từ combobox)
    private double thanhTien;     // Thành tiền
    private String loaiDoiTra;			//-1 là đổi , 0 là trả. 1 là mua 
	public SanPhamDoiTra(String maSP, String maDT, int soLuong, String tenSP, LocalDate ngayDoiTra, String trangThai,
			double chietKhau, double thanhTien, String loaiDoiTra) {
		super();
		this.maSP = maSP;
		this.maDT = maDT;
		this.soLuong = soLuong;
		this.tenSP = tenSP;
		this.ngayDoiTra = ngayDoiTra;
		this.trangThai = trangThai;
		this.chietKhau = chietKhau;
		this.thanhTien = thanhTien;
		this.loaiDoiTra = loaiDoiTra;
	}
	public SanPhamDoiTra() {
		super();
	}
	
	
	public SanPhamDoiTra(String maDT, int soLuong, String tenSP, String trangThai, double chietKhau, double thanhTien,
			String loaiDoiTra) {
		super();
		this.maDT = maDT;
		this.soLuong = soLuong;
		this.tenSP = tenSP;
		this.trangThai = trangThai;
		this.chietKhau = chietKhau;
		this.thanhTien = thanhTien;
		this.loaiDoiTra = loaiDoiTra;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getMaDT() {
		return maDT;
	}
	public void setMaDT(String maDT) {
		this.maDT = maDT;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public LocalDate getNgayDoiTra() {
		return ngayDoiTra;
	}
	public void setNgayDoiTra(LocalDate ngayDoiTra) {
		this.ngayDoiTra = ngayDoiTra;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public double getChietKhau() {
		return chietKhau;
	}
	public void setChietKhau(double chietKhau) {
		this.chietKhau = chietKhau;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public String getLoaiDoiTra() {
		return loaiDoiTra;
	}
	public void setLoaiDoiTra(String loaiDoiTra) {
		this.loaiDoiTra = loaiDoiTra;
	}
	@Override
	public String toString() {
		return "SanPhamDoiTra [maSP=" + maSP + ", maDT=" + maDT + ", soLuong=" + soLuong + ", tenSP=" + tenSP
				+ ", ngayDoiTra=" + ngayDoiTra + ", trangThai=" + trangThai + ", chietKhau=" + chietKhau
				+ ", thanhTien=" + thanhTien + ", loaiDoiTra=" + loaiDoiTra + "]";
	}

	
    
    
} 