package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Integer> {
    @Query("SELECT COUNT(p) FROM PhieuMuon p WHERE p.trangThai = ?1")
    Long countByTrangThai(String trangThai);
}