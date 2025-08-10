package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query("SELECT COUNT(h) FROM HoaDon h WHERE h.loai = ?1")
    Long countByLoai(String loai);
}