package entity;

public class TaiKhoan_entity {
    private String tenDangNhap;  // Tên đăng nhập
    private String matKhau;      // Mật khẩu
    private boolean phanQuyen;   // Quyền truy cập (true = QuanLy, false = NhanVien)
    private boolean trangThai;    // Trạng thái (true = Online, false = Offline)

    // Constructor mặc định
    public TaiKhoan_entity() {
    }

    // Constructor với tham số
    public TaiKhoan_entity(String tenDangNhap, String matKhau, boolean phanQuyen, boolean trangThai) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.phanQuyen = phanQuyen;
        this.trangThai = trangThai;
    }

    // Getter và Setter cho tenDangNhap
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    // Getter và Setter cho matKhau
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    // Getter và Setter cho phanQuyen
    public boolean isPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(boolean phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    // Getter và Setter cho trangThai
    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", phanQuyen=" + (phanQuyen ? "QuanLy" : "NhanVien") +
                ", trangThai=" + (trangThai ? "Online" : "Offline") +
                '}';
    }
}
