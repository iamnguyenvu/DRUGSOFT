/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

/**
 *
 * @author HP
 */
public class ModelTopKhachHang {
    private String sdtKH;
    private String tenKH;
    private double tongTienMua;

    public ModelTopKhachHang() {
    }

    public ModelTopKhachHang(String sdtKH, String tenKH, double tongTienMua) {
        this.sdtKH = sdtKH;
        this.tenKH = tenKH;
        this.tongTienMua = tongTienMua;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public double getTongTienMua() {
        return tongTienMua;
    }

    public void setTongTienMua(double tongTienMua) {
        this.tongTienMua = tongTienMua;
    }
    
    
}
