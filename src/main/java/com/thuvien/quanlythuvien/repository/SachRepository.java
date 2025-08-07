package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.Sach;
import com.thuvien.quanlythuvien.entity.LoaiSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SachRepository extends JpaRepository<Sach, Integer> {
    // Tìm sách theo tên
    List<Sach> findByTenSachContaining(String tenSach);
    
    // Tìm sách theo tác giả
    List<Sach> findByTacGiaContaining(String tacGia);
    
    // Tìm sách theo loại
    List<Sach> findByLoaiSach(LoaiSach loaiSach);
    
    // Tìm sách có số lượng > 0
    List<Sach> findBySoLuongGreaterThan(Integer soLuong);
}