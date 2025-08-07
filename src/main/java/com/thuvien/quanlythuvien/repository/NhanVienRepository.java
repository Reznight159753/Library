package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    // Tìm nhân viên theo tên
    List<NhanVien> findByTenNVContaining(String tenNV);
    
    // Tìm nhân viên theo chức vụ
    List<NhanVien> findByChucVu(String chucVu);
}