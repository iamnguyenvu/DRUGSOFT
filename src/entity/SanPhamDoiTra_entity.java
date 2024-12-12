package entity;

public class SanPhamDoiTra_entity {
	private String maDT;
	private String maSP;
	private int soLuong;
	private double donGia;
	private String trangThai;
	private String tenSP;
	private String vanDe;
	private double tinhTrang;
	
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

    public SanPhamDoiTra_entity(String maDT, String maSP, int soLuong, double donGia, String trangThai, String tenSP, String vanDe, double tinhTrang) {
        this.maDT = maDT;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
        this.tenSP = tenSP;
        this.vanDe = vanDe;
        this.tinhTrang = tinhTrang;
    }
	
	
	public double getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(double tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getVanDe() {
		return vanDe;
	}
	public void setVanDe(String vanDe) {
		this.vanDe = vanDe;
	}
	public SanPhamDoiTra_entity() {
		super();
	}
	public SanPhamDoiTra_entity(String maDT, String maSP, int soLuong, double donGia, String trangThai,double tinhTrang,String vanDe) {
		super();
		this.maDT = maDT;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.trangThai = trangThai;
		this.tinhTrang = tinhTrang;
		this.vanDe = vanDe;
	}

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
	
	

}
