package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "LOAISACH")
public class LoaiSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALOAI")
    private Integer maLoai;

    @Column(name = "TENLOAI")
    private String tenLoai;

    // Constructors
    public LoaiSach() {}

    public LoaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    // Getters and Setters
    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}