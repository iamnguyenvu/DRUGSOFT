package entity;

public class LoaiNhanVien {
    private String maLoaiNV;   // Mã loại nhân viên
    private String tenLoaiNV;   // Tên loại nhân viên

    // Constructor mặc định
    public LoaiNhanVien() {
    }

    // Constructor với tham số
    public LoaiNhanVien(String maLoaiNV, String tenLoaiNV) {
        this.maLoaiNV = maLoaiNV;
        this.tenLoaiNV = tenLoaiNV;
    }

    // Getter và Setter cho maLoaiNV
    public String getMaLoaiNV() {
        return maLoaiNV;
    }

    public void setMaLoaiNV(String maLoaiNV) {
        this.maLoaiNV = maLoaiNV;
    }

    // Getter và Setter cho tenLoaiNV
    public String getTenLoaiNV() {
        return tenLoaiNV;
    }

    public void setTenLoaiNV(String tenLoaiNV) {
        this.tenLoaiNV = tenLoaiNV;
    }

    @Override
    public String toString() {
        return "LoaiNhanVien{" +
                "maLoaiNV='" + maLoaiNV + '\'' +
                ", tenLoaiNV='" + tenLoaiNV + '\'' +
                '}';
    }
}
