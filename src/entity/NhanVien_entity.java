package entity;

import java.time.LocalDate;

public class NhanVien_entity {
    private String maNV;                // Mã nhân viên
    private String hoTenNV;             // Họ nhân viên
    private String gioiTinh;            // Giới tính
    private String sdt;                 // Số điện thoại
    private String cccd;                // CCCD
    private String diaChi;              // Địa chỉ
    private LocalDate ngaySinh;         // Ngày sinh
    private boolean trangThai;          // Trạng thái
    private LocalDate ngayVaoLam;       // Ngày vào làm
    private TaiKhoan_entity taiKhoan;   // Tài khoản
    private LoaiNhanVien_entity loaiNhanVien; // Loại nhân viên
    private String hinhAnhNV;           // Hình ảnh nhân viên
    private String maLoaiNV;            // Mã loại nhân viên
    private String email;
    public NhanVien_entity() {
        super();
    }

    public NhanVien_entity(String maNV, String hoTenNV) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
	}

	public NhanVien_entity(String maNV) {
        super();
        this.maNV = maNV;
    }
    

    public NhanVien_entity(String maNV, String hoTenNV, String sdt) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.sdt = sdt;
	}

	public NhanVien_entity(String maNV, String hoTenNV, String gioiTinh, String sdt, String cccd, String diaChi,
			LocalDate ngaySinh, boolean trangThai, LocalDate ngayVaoLam, String hinhAnhNV, String maLoaiNV) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
		this.cccd = cccd;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.trangThai = trangThai;
		this.ngayVaoLam = ngayVaoLam;
		this.hinhAnhNV = hinhAnhNV;
		this.maLoaiNV = maLoaiNV;
		this.email = email;
	}



	public NhanVien_entity(String maNV, String hoTenNV, String gioiTinh, String sdt, String cccd, String diaChi,
			LocalDate ngaySinh, boolean trangThai, LocalDate ngayVaoLam, String hinhAnhNV, String maLoaiNV,
			String email) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
		this.cccd = cccd;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.trangThai = trangThai;
		this.ngayVaoLam = ngayVaoLam;
		this.hinhAnhNV = hinhAnhNV;
		this.maLoaiNV = maLoaiNV;
		this.email = email;
	}

	public NhanVien_entity(String maNV, String hoTenNV, String gioiTinh, String sdt, String cccd,
                           String diaChi, LocalDate ngaySinh, boolean trangThai, LocalDate ngayVaoLam,
                           TaiKhoan_entity taiKhoan, LoaiNhanVien_entity loaiNhanVien, String hinhAnhNV,
                           String maLoaiNV, String email) {
        super();
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.trangThai = trangThai;
        this.ngayVaoLam = ngayVaoLam;
        this.taiKhoan = taiKhoan;
        this.loaiNhanVien = loaiNhanVien;
        this.hinhAnhNV = hinhAnhNV;
        this.maLoaiNV = maLoaiNV; // Khởi tạo mã loại nhân viên
        this.email = email;
    }

    // Getter and Setter methods
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTenNV() {
		return hoTenNV;
	}

	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

	public TaiKhoan_entity getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan_entity taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public LoaiNhanVien_entity getLoaiNhanVien() {
		return loaiNhanVien;
	}

	public void setLoaiNhanVien(LoaiNhanVien_entity loaiNhanVien) {
		this.loaiNhanVien = loaiNhanVien;
	}

	public String getHinhAnhNV() {
		return hinhAnhNV;
	}

	public void setHinhAnhNV(String hinhAnhNV) {
		this.hinhAnhNV = hinhAnhNV;
	}

	public String getMaLoaiNV() {
		return maLoaiNV;
	}

	public void setMaLoaiNV(String maLoaiNV) {
		this.maLoaiNV = maLoaiNV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", gioiTinh=" + gioiTinh +
               ", sdt=" + sdt + ", cccd=" + cccd + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh +
               ", trangThai=" + trangThai + ", ngayVaoLam=" + ngayVaoLam + ", taiKhoan=" + taiKhoan +
               ", loaiNhanVien=" + loaiNhanVien + ", hinhAnhNV=" + hinhAnhNV + ", maLoaiNV=" + maLoaiNV + ",email=" + email + "]";
    }

	
	public void setChucVu(String string) {
		// TODO Auto-generated method stub
		
	}

	public Object isChucVu() {
		// TODO Auto-generated method stub
		return null;
	}
}
	