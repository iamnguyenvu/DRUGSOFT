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
    private double tienHoan;
    private String loaiDoiTra;
    private String lyDo;
    private String maHD;
    private InputStream qrcode;
    private List<FieldBillDT> listFB;

    public ParameterBillDT() {
    }

    public ParameterBillDT(String ngayLapHD, String tenNV, String tenKH, String SDT, double tienHoan, String loaiDoiTra, String lyDo, String maHD, InputStream qrcode, List<FieldBillDT> listFB) {
        this.ngayLapHD = ngayLapHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.tienHoan = tienHoan;
        this.loaiDoiTra = loaiDoiTra;
        this.lyDo = lyDo;
        this.maHD = maHD;
        this.qrcode = qrcode;
        this.listFB = listFB;
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

    public String getLoaiDoiTra() {
        return loaiDoiTra;
    }

    public void setLoaiDoiTra(String loaiDoiTra) {
        this.loaiDoiTra = loaiDoiTra;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
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

    public List<FieldBillDT> getListFB() {
        return listFB;
    }

    public void setListFB(List<FieldBillDT> listFB) {
        this.listFB = listFB;
    }
    
    

}
