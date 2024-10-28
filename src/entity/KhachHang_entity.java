package entity;

public class KhachHang_entity {
    private String sdtKH; // Mã khách hàng
    private String tenKH; // Tên khách hàng
    private int diemThuong; // Điểm thưởng
    private String gioiTinh; // Giới tính

    // Constructor mặc định
    public KhachHang_entity() {
    }

	public KhachHang_entity(String sdtKH) {
		super();
		this.sdtKH = sdtKH;
	}

	public KhachHang_entity(String sdtKH, String tenKH, int diemThuong, String gioiTinh) {
		super();
		this.sdtKH = sdtKH;
		this.tenKH = tenKH;
		this.diemThuong = diemThuong;
		this.gioiTinh = gioiTinh;
	}

	public String getSdtKH() {
		return sdtKH;
	}

	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public int getDiemThuong() {
		return diemThuong;
	}

	public void setDiemThuong(int diemThuong) {
		this.diemThuong = diemThuong;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public String toString() {
		return "KhachHang_entity [sdtKH=" + sdtKH + ", tenKH=" + tenKH + ", diemThuong=" + diemThuong + ", gioiTinh="
				+ gioiTinh + "]";
	}
	
    
}

