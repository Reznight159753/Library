package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANV")
    private Integer maNv;
    
    @Column(name = "TENNV")
    private String tenNv;
    
    @Column(name = "NGAYSINH")
    private LocalDate ngaySinh;
    
    @Column(name = "GIOITINH")
    private String gioiTinh;
    
    @Column(name = "DIACHI")
    private String diaChi;
    
    @Column(name = "SDT")
    private String sdt;
    
    @Column(name = "CHUCVU")
    private String chucVu;
    
    // Constructors
    public NhanVien() {}
    
    public NhanVien(String tenNv, LocalDate ngaySinh, String gioiTinh, String diaChi, String sdt, String chucVu) {
        this.tenNv = tenNv;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.chucVu = chucVu;
    }
    
    // Getters and Setters
    public Integer getMaNv() {
        return maNv;
    }
    
    public void setMaNv(Integer maNv) {
        this.maNv = maNv;
    }
    
    public String getTenNv() {
        return tenNv;
    }
    
    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }
    
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }
    
    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public String getGioiTinh() {
        return gioiTinh;
    }
    
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getSdt() {
        return sdt;
    }
    
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    public String getChucVu() {
        return chucVu;
    }
    
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}