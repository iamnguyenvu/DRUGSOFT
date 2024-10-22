package entity;

public class KhachHang_entity {
    private String maKH; // Mã khách hàng
    private String tenKH; // Tên khách hàng
    private String SDT; // Số điện thoại
    private int diemThuong; // Điểm thưởng
    private String gioiTinh; // Giới tính

    // Constructor mặc định
    public KhachHang_entity() {
    }

    // Constructor với đầy đủ tham số
    public KhachHang_entity(String maKH, String tenKH, String SDT, int diemThuong, String gioiTinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.diemThuong = diemThuong;
        this.gioiTinh = gioiTinh;
    }
    

    public KhachHang_entity(String maKH) {
		super();
		this.maKH = maKH;
	}

	// Getter và Setter cho maKH
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    // Getter và Setter cho tenKH
    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    // Getter và Setter cho SDT
    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    // Getter và Setter cho diemThuong
    public int getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(int diemThuong) {
        this.diemThuong = diemThuong;
    }

    // Getter và Setter cho gioiTinh
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH='" + maKH + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", SDT='" + SDT + '\'' +
                ", diemThuong=" + diemThuong +
                ", gioiTinh='" + gioiTinh + '\'' +
                '}';
    }
}

