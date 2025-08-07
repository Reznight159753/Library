package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DOCGIA")
public class DocGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADG")
    private Integer maDG;
    
    @Column(name = "TENDG", length = 100)
    private String tenDG;
    
    @Column(name = "NGAYSINH")
    private LocalDate ngaySinh;
    
    @Column(name = "GIOITINH", length = 10)
    private String gioiTinh;
    
    @Column(name = "DIACHI", length = 255)
    private String diaChi;
    
    @Column(name = "SDT", length = 20)
    private String sdt;
    
    // Constructors
    public DocGia() {}
    
    public DocGia(String tenDG, LocalDate ngaySinh, String gioiTinh, String diaChi, String sdt) {
        this.tenDG = tenDG;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }
    
    // Getters and Setters
    public Integer getMaDG() { return maDG; }
    public void setMaDG(Integer maDG) { this.maDG = maDG; }
    
    public String getTenDG() { return tenDG; }
    public void setTenDG(String tenDG) { this.tenDG = tenDG; }
    
    public LocalDate getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }
    
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
}