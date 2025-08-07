package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SACH")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MASACH")
    private Integer maSach;
    
    @Column(name = "TENSACH", length = 255)
    private String tenSach;
    
    @Column(name = "TACGIA", length = 100)
    private String tacGia;
    
    @Column(name = "NAMXB")
    private Integer namXB;
    
    @Column(name = "NXB", length = 100)
    private String nxb;
    
    @Column(name = "DONGIA", precision = 10, scale = 2)
    private BigDecimal donGia;
    
    @Column(name = "SOLUONG")
    private Integer soLuong;
    
    // Quan hệ với LoaiSach
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MALOAI", referencedColumnName = "MALOAI")
    private LoaiSach loaiSach;
    
    // Constructors
    public Sach() {}
    
    public Sach(String tenSach, String tacGia, Integer namXB, String nxb, BigDecimal donGia, Integer soLuong, LoaiSach loaiSach) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.nxb = nxb;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.loaiSach = loaiSach;
    }
    
    // Getters and Setters
    public Integer getMaSach() { return maSach; }
    public void setMaSach(Integer maSach) { this.maSach = maSach; }
    
    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }
    
    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    
    public Integer getNamXB() { return namXB; }
    public void setNamXB(Integer namXB) { this.namXB = namXB; }
    
    public String getNxb() { return nxb; }
    public void setNxb(String nxb) { this.nxb = nxb; }
    
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
    
    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
    
    public LoaiSach getLoaiSach() { return loaiSach; }
    public void setLoaiSach(LoaiSach loaiSach) { this.loaiSach = loaiSach; }
}