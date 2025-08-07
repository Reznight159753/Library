package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan {
    @Id
    @Column(name = "TENTK", length = 50)
    private String tenTK;
    
    @Column(name = "MATKHAU", length = 100)
    private String matKhau;
    
    @Column(name = "VAITRO", length = 20)
    private String vaiTro;
    
    // Quan hệ với NhanVien
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANV", referencedColumnName = "MANV")
    private NhanVien nhanVien;
    
    // Constructors
    public TaiKhoan() {}
    
    public TaiKhoan(String tenTK, String matKhau, String vaiTro, NhanVien nhanVien) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.nhanVien = nhanVien;
    }
    
    // Getters and Setters
    public String getTenTK() { return tenTK; }
    public void setTenTK(String tenTK) { this.tenTK = tenTK; }
    
    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }
    
    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }
    
    public NhanVien getNhanVien() { return nhanVien; }
    public void setNhanVien(NhanVien nhanVien) { this.nhanVien = nhanVien; }
}