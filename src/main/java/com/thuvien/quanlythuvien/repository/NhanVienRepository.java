package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    
    List<NhanVien> findByTenNvContainingIgnoreCase(String tenNv);
    
    List<NhanVien> findByChucVu(String chucVu);
    
    List<NhanVien> findBySdtContainingIgnoreCase(String sdt);
    
    @Query("SELECT COUNT(nv) FROM NhanVien nv")
    Long countTotalEmployees();
}