/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author HP
 */
public class DonTam_entity {
    private String sdtKH;
    private String tenKH;
    private List<SanPham_entity> listSP;
    private long thoiGianLuu;

    public DonTam_entity() {
    }

    public DonTam_entity(String sdtKH, String tenKH, List<SanPham_entity> listSP) {
        this.sdtKH = sdtKH;
        this.tenKH = tenKH;
        this.listSP = listSP;
        this.thoiGianLuu = System.currentTimeMillis();
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

    public List<SanPham_entity> getListSP() {
        return listSP;
    }

    public void setListSP(List<SanPham_entity> listSP) {
        this.listSP = listSP;
    }

    public long getThoiGianLuu() {
        return thoiGianLuu;
    }

    public void setThoiGianLuu(long thoiGianLuu) {
        this.thoiGianLuu = thoiGianLuu;
    }

    @Override
    public String toString() {
        return "DonTam_entity{" + "sdtKH=" + sdtKH + ", tenKH=" + tenKH + ", listSP=" + listSP + ", thoiGianLuu=" + thoiGianLuu + '}';
    }
    
    
    
}
