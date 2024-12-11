package entity;

import java.time.LocalDateTime;

public class HoaDonDoiTra_entity {
	private String maDT;
	private String maHD;
	private LocalDateTime ngayDoiTra;
	private double tienTraLai;
	private double tienKhachtraThem;
        
	public HoaDonDoiTra_entity(String maDT, String maHD, LocalDateTime ngayDoiTra, double tienTraLai,
			double tienKhachtraThem) {
		super();
		this.maDT = maDT;
		this.maHD = maHD;
		this.ngayDoiTra = ngayDoiTra;
		this.tienTraLai = tienTraLai;
		this.tienKhachtraThem = tienKhachtraThem;
	}
	public String getMaDT() {
		return maDT;
	}
	public void setMaDT(String maDT) {
		this.maDT = maDT;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDateTime getNgayDoiTra() {
		return ngayDoiTra;
	}
	public void setNgayDoiTra(LocalDateTime ngayDoiTra) {
		this.ngayDoiTra = ngayDoiTra;
	}
	public double getTienTraLai() {
		return tienTraLai;
	}
	public void setTienTraLai(double tienTraLai) {
		this.tienTraLai = tienTraLai;
	}
	public double getTienKhachtraThem() {
		return tienKhachtraThem;
	}
	public void setTienKhachtraThem(double tienKhachtraThem) {
		this.tienKhachtraThem = tienKhachtraThem;
	}
	
	
}