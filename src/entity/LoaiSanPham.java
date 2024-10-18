package entity;

public class LoaiSanPham {
    private String maLoaiSP;   // Mã loại sản phẩm
    private String tenLoaiSP;   // Tên loại sản phẩm

    // Constructor mặc định
    public LoaiSanPham() {
    }

    // Constructor với đầy đủ tham số
    public LoaiSanPham(String maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }

    // Constructor chỉ với maLoaiSP
    public LoaiSanPham(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    // Getter và Setter cho maLoaiSP
    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    // Getter và Setter cho tenLoaiSP
    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" +
                "maLoaiSP='" + maLoaiSP + '\'' +
                ", tenLoaiSP='" + tenLoaiSP + '\'' +
                '}';
    }
}
