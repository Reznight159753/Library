package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.LoaiSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiSachRepository extends JpaRepository<LoaiSach, Integer> {
    // Tìm loại sách theo tên
    List<LoaiSach> findByTenLoaiContaining(String tenLoai);
}