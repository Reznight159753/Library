package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.ChiTietPhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietPhieuMuonRepository extends JpaRepository<ChiTietPhieuMuon, Integer> {
    List<ChiTietPhieuMuon> findByPhieuMuonMaPhieuMuon(Integer maPhieuMuon);
}