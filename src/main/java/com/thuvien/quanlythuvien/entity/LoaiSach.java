package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "LOAISACH")
public class LoaiSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALOAI")
    private Integer maLoai;
    
    @Column(name = "TENLOAI", length = 100)
    private String tenLoai;
    
    // Quan hệ với Sach
    @OneToMany(mappedBy = "loaiSach", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sach> sachs;
    
    // Constructors
    public LoaiSach() {}
    
    public LoaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    
    // Getters and Setters
    public Integer getMaLoai() { return maLoai; }
    public void setMaLoai(Integer maLoai) { this.maLoai = maLoai; }
    
    public String getTenLoai() { return tenLoai; }
    public void setTenLoai(String tenLoai) { this.tenLoai = tenLoai; }
    
    public List<Sach> getSachs() { return sachs; }
    public void setSachs(List<Sach> sachs) { this.sachs = sachs; }
}