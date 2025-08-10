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

    @Column(name = "TENSACH")
    private String tenSach;

    @Column(name = "TACGIA")
    private String tacGia;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "NAMXB")
    private Integer namXb;

    @Column(name = "NXB")
    private String nxb;

    @Column(name = "DONGIA")
    private BigDecimal donGia;

    @Column(name = "SOLUONG")
    private Integer soLuong;

    @Column(name = "MALOAI")
    private Integer maLoai;

    @ManyToOne
    @JoinColumn(name = "MALOAI", insertable = false, updatable = false)
    private LoaiSach loaiSach;

    @Column(name = "TRANGTHAI")
    private String trangThai = "Available";

    public Sach() {
    }

    public Sach(String tenSach, String tacGia, String image, Integer namXb, String nxb,
            BigDecimal donGia, Integer soLuong, Integer maLoai, String trangThai) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.image = image;
        this.namXb = namXb;
        this.nxb = nxb;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
        this.trangThai = trangThai;
    }

    public Integer getMaSach() {
        return maSach;
    }

    public void setMaSach(Integer maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNamXb() {
        return namXb;
    }

    public void setNamXb(Integer namXb) {
        this.namXb = namXb;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }

    public LoaiSach getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(LoaiSach loaiSach) {
        this.loaiSach = loaiSach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}