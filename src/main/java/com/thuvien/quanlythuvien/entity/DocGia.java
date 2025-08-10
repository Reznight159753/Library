package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DOCGIA")
public class DocGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADG")
    private Integer maDg;

    @Column(name = "TENDG")
    private String tenDg;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "NGAYSINH")
    private LocalDate ngaySinh;

    @Column(name = "GIOITINH")
    private String gioiTinh;

    @Column(name = "DIACHI")
    private String diaChi;

    // Constructors
    public DocGia() {
    }

    public DocGia(String tenDg, String sdt, LocalDate ngaySinh, String gioiTinh, String diaChi) {
        this.tenDg = tenDg;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
    }

    // Getters and Setters
    public Integer getMaDg() {
        return maDg;
    }

    public void setMaDg(Integer maDg) {
        this.maDg = maDg;
    }

    public String getTenDg() {
        return tenDg;
    }

    public void setTenDg(String tenDg) {
        this.tenDg = tenDg;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

}