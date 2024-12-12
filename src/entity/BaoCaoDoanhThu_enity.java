/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Vovan
 */
public class BaoCaoDoanhThu_enity {
    private String thoiGian;
    private double doanhThu;
    private double chiPhi;
    private double loiNhuan;
    private int soLuongGD;

    public BaoCaoDoanhThu_enity(String thoiGian, double doanhThu, double chiPhi, double loiNhuan, int soLuongGD) {
        this.thoiGian = thoiGian;
        this.doanhThu = doanhThu;
        this.chiPhi = chiPhi;
        this.loiNhuan = loiNhuan;
        this.soLuongGD = soLuongGD;
    }

    public BaoCaoDoanhThu_enity() {
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public double getChiPhi() {
        return chiPhi;
    }

    public double getLoiNhuan() {
        return loiNhuan;
    }

    public int getSoLuongGD() {
        return soLuongGD;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public void setChiPhi(double chiPhi) {
        this.chiPhi = chiPhi;
    }

    public void setLoiNhuan(double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

    public void setSoLuongGD(int soLuongGD) {
        this.soLuongGD = soLuongGD;
    }

    @Override
    public String toString() {
        return "BaoCaoDoanhThu_enity{" + "thoiGian=" + thoiGian + ", doanhThu=" + doanhThu + ", chiPhi=" + chiPhi + ", loiNhuan=" + loiNhuan + ", soLuongGD=" + soLuongGD + '}';
    }
    
    
}
