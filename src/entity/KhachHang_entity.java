package entity;

public class KhachHang_entity {
    private String tenKH; // Tên khách hàng
    private String sdtKH; // Số điện thoại
    private int diemThuong; // Điểm thưởng
    private String gioiTinh; // Giới tính

    // Constructor mặc định
    // Constructor với đầy đủ tham số
    public KhachHang_entity( String tenKH, String sdtKH, int diemThuong, String gioiTinh) {
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.diemThuong = diemThuong;
        this.gioiTinh = gioiTinh;
    }
    

    public KhachHang_entity(String sdt) {
		super();
		this.sdtKH = sdtKH;
	}

	// Getter và Setter cho maKH
    

    // Getter và Setter cho tenKH
    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    // Getter và Setter cho SDT
    public String getsdtKH() {
        return sdtKH;
    }

    public void setSDT(String SDT) {
        this.sdtKH = SDT;
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
                
                ", tenKH='" + tenKH + '\'' +
                ", SDT='" + sdtKH + '\'' +
                ", diemThuong=" + diemThuong +
                ", gioiTinh='" + gioiTinh + '\'' +
                '}';
    }
}

