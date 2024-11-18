/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

/**
 *
 * @author HP
 */
public class ModelTransaction {
    private String role;
    private String tenNV;
    private Double thanhTien;

    public ModelTransaction(String role, String tenNV, Double thanhTien) {
        this.role = role;
        this.tenNV = tenNV;
        this.thanhTien = thanhTien;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
    
}
