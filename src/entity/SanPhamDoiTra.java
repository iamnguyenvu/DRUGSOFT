package entity;
import java.time.LocalDate;
import java.util.Date;

public class SanPhamDoiTra {
    private String maSP;       // Mã sản phẩm
    private String maHD;       // Mã hóa đơn
    private int soLuong;       // Số lượng đổi trả
    private String vanDe;      // Vấn đề đổi trả
    private LocalDate ngayDoiTra;   // Ngày đổi trả
    private boolean trangThai; // Trạng thái đổi trả (true: Hoàn thành, false: Chưa xử lý)

    // Constructor không tham số
    public SanPhamDoiTra() {}

    // Constructor đầy đủ tham số
    public SanPhamDoiTra(String maSP, String maHD, int soLuong, String vanDe, LocalDate ngayDoiTra, boolean trangThai) {
        this.maSP = maSP;
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.vanDe = vanDe;
        this.ngayDoiTra = ngayDoiTra;
        this.trangThai = trangThai;
    }

    // Getter và Setter cho các thuộc tính
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getVanDe() {
        return vanDe;
    }

    public void setVanDe(String vanDe) {
        this.vanDe = vanDe;
    }

    public LocalDate getNgayDoiTra() {
        return ngayDoiTra;
    }

    public void setNgayDoiTra(LocalDate ngayDoiTra) {
        this.ngayDoiTra = ngayDoiTra;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Override phương thức toString để hiển thị thông tin đối tượng
    @Override
    public String toString() {
        return "SanPhamDoiTra{" +
                "maSP='" + maSP + '\'' +
                ", maHD='" + maHD + '\'' +
                ", soLuong=" + soLuong +
                ", vanDe='" + vanDe + '\'' +
                ", ngayDoiTra=" + ngayDoiTra +
                ", trangThai=" + trangThai +
                '}';
    }

	public Object getHinhAnh() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
