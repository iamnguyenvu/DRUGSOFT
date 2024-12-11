package entity;
import java.time.LocalDate;

public class ChiTietHoaDonDoiTra_entity {
    private String maSP;          // Mã sản phẩm
    private String tenSP;
    private String maDT;          // Mã hóa đơn
    private int soLuong;          // Số lượng đổi trả
    private double chietKhau;     // Chiết khấu (lựa chọn từ combobox)
    private double thanhTien;     // Thành tiền
    private String loaiDoiTra;			//-1 là đổi , 0 là trả. 1 là mua 

	public ChiTietHoaDonDoiTra_entity() {
		super();
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

	public ChiTietHoaDonDoiTra_entity(String maSP, String tenSP, String maDT, int soLuong, double chietKhau, double thanhTien, String loaiDoiTra) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.maDT = maDT;
		this.soLuong = soLuong;
		this.chietKhau = chietKhau;
		this.thanhTien = thanhTien;
		this.loaiDoiTra = loaiDoiTra;
	}


	public ChiTietHoaDonDoiTra_entity(String maDT, String tenSP, int soLuong, double chietKhau, double thanhTien,
			String loaiDoiTra) {
		super();
		this.maDT = maDT;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.chietKhau = chietKhau;
		this.thanhTien = thanhTien;
		this.loaiDoiTra = loaiDoiTra;
	}

	
	
	
    
    
} 