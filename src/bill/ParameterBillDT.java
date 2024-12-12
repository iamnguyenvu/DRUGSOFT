/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bill;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author HP
 */
public class ParameterBillDT {

    private String ngayLapHD;
    private String tenNV;
    private String tenKH;
    private String SDT;
    private double tongTienHangTra;
    private double tongPhiTraHang;
    private double tienHoan;
    private double tongTienDoi;
    private double giamTru;
    private double thanhToan;
    private String ghiChu;
    private String maHD;
    private InputStream qrcode;
    private List<FieldBillDoiTra> listFB;

    public ParameterBillDT() {
    }

    public ParameterBillDT(String ngayLapHD, String tenNV, String tenKH, String SDT,
            double tongTienHangTra, double tongPhiTraHang, double tienHoan,
            double tongTienDoi, double giamTru, double thanhToan, String ghiChu,
            String maHD, InputStream qrcode, List<FieldBillDoiTra> listFB) {
        this.ngayLapHD = ngayLapHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.tongTienHangTra = tongTienHangTra;
        this.tongPhiTraHang = tongPhiTraHang;
        this.tienHoan = tienHoan;
        this.tongTienDoi = tongTienDoi;
        this.giamTru = giamTru;
        this.thanhToan = thanhToan;
        this.ghiChu = ghiChu;
        this.maHD = maHD;
        this.qrcode = qrcode;
        this.listFB = listFB;
    }

    public double getTongTienHangTra() {
        return tongTienHangTra;
    }

    public void setTongTienHangTra(double tongTienHangTra) {
        this.tongTienHangTra = tongTienHangTra;
    }

    public double getTongPhiTraHang() {
        return tongPhiTraHang;
    }

    public void setTongPhiTraHang(double tongPhiTraHang) {
        this.tongPhiTraHang = tongPhiTraHang;
    }

    public String getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(String ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public double getTienHoan() {
        return tienHoan;
    }

    public void setTienHoan(double tienHoan) {
        this.tienHoan = tienHoan;
    }

    public double getTongTienDoi() {
        return tongTienDoi;
    }

    public void setTongTienDoi(double tongTienDoi) {
        this.tongTienDoi = tongTienDoi;
    }

    public double getGiamTru() {
        return giamTru;
    }

    public void setGiamTru(double giamTru) {
        this.giamTru = giamTru;
    }

    public double getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(double thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public InputStream getQrcode() {
        return qrcode;
    }

    public void setQrcode(InputStream qrcode) {
        this.qrcode = qrcode;
    }

    public List<FieldBillDoiTra> getListFB() {
        return listFB;
    }

    public void setListFB(List<FieldBillDoiTra> listFB) {
        this.listFB = listFB;
    }

}
