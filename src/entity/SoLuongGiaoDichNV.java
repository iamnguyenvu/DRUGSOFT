package entity;

public class SoLuongGiaoDichNV {
	private String maNV;
	private String hotenNV;
	private int soLuongDD;
	public SoLuongGiaoDichNV(String maNV, String hotenNV, int soLuongDD) {
		super();
		this.maNV = maNV;
		this.hotenNV = hotenNV;
		this.soLuongDD = soLuongDD;
	}
	public SoLuongGiaoDichNV() {
		super();
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
	public int getSoLuongDD() {
		return soLuongDD;
	}
	public void setSoLuongDD(int soLuongDD) {
		this.soLuongDD = soLuongDD;
	}
	

}
