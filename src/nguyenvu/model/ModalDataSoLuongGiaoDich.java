package nguyenvu.model;

public class ModalDataSoLuongGiaoDich {
	private String hoTenNV;
	private int soLuongGiaoDich;
	public ModalDataSoLuongGiaoDich(String hoTenNV, int soLuongGiaoDich) {
		super();
		this.hoTenNV = hoTenNV;
		this.soLuongGiaoDich = soLuongGiaoDich;
	}
	public ModalDataSoLuongGiaoDich() {
		super();
	}
	public String getHoTenNV() {
		return hoTenNV;
	}
	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}
	public int getSoLuongGiaoDich() {
		return soLuongGiaoDich;
	}
	public void setSoLuongGiaoDich(int soLuongGiaoDich) {
		this.soLuongGiaoDich = soLuongGiaoDich;
	}
	@Override
	public String toString() {
		return "ModalDataSoLuongGiaoDich [hoTenNV=" + hoTenNV + ", soLuongGiaoDich=" + soLuongGiaoDich + "]";
	}
	

}
