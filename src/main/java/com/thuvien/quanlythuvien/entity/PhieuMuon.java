package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PHIEUMUON")
public class PhieuMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUMUON")
    private Integer maPhieuMuon;

    @Column(name = "NGAYMUON")
    private LocalDate ngayMuon;

    @Column(name = "NGAYTRADUKIEN")
    private LocalDate ngayTraDuKien;

    @Column(name = "NGAYTRATHUCTE")
    private LocalDate ngayTraThucTe;

    @Column(name = "TRANGTHAI")
    private String trangThai;
    
    @Column(name = "PHIPHAT")
    private BigDecimal phiPhat = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "MADG")
    private DocGia docGia;

    @ManyToOne
    @JoinColumn(name = "MANV")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "phieuMuon", cascade = CascadeType.ALL)
    private List<ChiTietPhieuMuon> chiTietPhieuMuons;

    public PhieuMuon() {
    }

    public Integer getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(Integer maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public LocalDate getNgayTraDuKien() {
        return ngayTraDuKien;
    }

    public void setNgayTraDuKien(LocalDate ngayTraDuKien) {
        this.ngayTraDuKien = ngayTraDuKien;
    }

    public LocalDate getNgayTraThucTe() {
        return ngayTraThucTe;
    }

    public void setNgayTraThucTe(LocalDate ngayTraThucTe) {
        this.ngayTraThucTe = ngayTraThucTe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getPhiPhat() {
        return phiPhat;
    }

    public void setPhiPhat(BigDecimal phiPhat) {
        this.phiPhat = phiPhat;
    }

    public DocGia getDocGia() {
        return docGia;
    }

    public void setDocGia(DocGia docGia) {
        this.docGia = docGia;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public List<ChiTietPhieuMuon> getChiTietPhieuMuons() {
        return chiTietPhieuMuons;
    }

    public void setChiTietPhieuMuons(List<ChiTietPhieuMuon> chiTietPhieuMuons) {
        this.chiTietPhieuMuons = chiTietPhieuMuons;
    }
}