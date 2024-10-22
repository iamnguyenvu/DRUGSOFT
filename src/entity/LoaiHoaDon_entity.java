package entity;

public class LoaiHoaDon_entity {
    private String maLoaiHD; // Mã loại hóa đơn
    private String tenLoaiHD; // Tên loại hóa đơn

    // Constructor mặc định
    public LoaiHoaDon_entity() {
    }

    // Constructor với đầy đủ tham số
    public LoaiHoaDon_entity(String maLoaiHD, String tenLoaiHD) {
        this.maLoaiHD = maLoaiHD;
        this.tenLoaiHD = tenLoaiHD;
    }

    // Getter và Setter cho maLoaiHD
    public String getMaLoaiHD() {
        return maLoaiHD;
    }

    public void setMaLoaiHD(String maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
    }

    // Getter và Setter cho tenLoaiHD
    public String getTenLoaiHD() {
        return tenLoaiHD;
    }

    public void setTenLoaiHD(String tenLoaiHD) {
        this.tenLoaiHD = tenLoaiHD;
    }

    @Override
    public String toString() {
        return "LoaiHoaDon{" +
                "maLoaiHD='" + maLoaiHD + '\'' +
                ", tenLoaiHD='" + tenLoaiHD + '\'' +
                '}';
    }
}
