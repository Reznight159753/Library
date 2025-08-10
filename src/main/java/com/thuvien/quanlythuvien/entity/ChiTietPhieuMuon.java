package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CHITIETPHIEUMUON")
public class ChiTietPhieuMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MAPHIEUMUON")
    private PhieuMuon phieuMuon;

    @ManyToOne
    @JoinColumn(name = "MASACH")
    private Sach sach;

    @Column(name = "SOLUONGMUON")
    private Integer soLuongMuon;

    public ChiTietPhieuMuon() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public Integer getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(Integer soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }
}