package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan {
    
    @Id
    @Column(name = "TENTK")
    private String tenTk;
    
    @Column(name = "MATKHAU")
    private String matKhau;
    
    @Column(name = "MANV")
    private Integer maNv;
    
    @Column(name = "VAITRO")
    private String vaiTro;
    
    @ManyToOne
    @JoinColumn(name = "MANV", insertable = false, updatable = false)
    private NhanVien nhanVien;
    
    // Constructors
    public TaiKhoan() {}
    
    public TaiKhoan(String tenTk, String matKhau, Integer maNv, String vaiTro) {
        this.tenTk = tenTk;
        this.matKhau = matKhau;
        this.maNv = maNv;
        this.vaiTro = vaiTro;
    }
    
    // Getters and Setters
    public String getTenTk() {
        return tenTk;
    }
    
    public void setTenTk(String tenTk) {
        this.tenTk = tenTk;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public Integer getMaNv() {
        return maNv;
    }
    
    public void setMaNv(Integer maNv) {
        this.maNv = maNv;
    }
    
    public String getVaiTro() {
        return vaiTro;
    }
    
    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
    public NhanVien getNhanVien() {
        return nhanVien;
    }
    
    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}