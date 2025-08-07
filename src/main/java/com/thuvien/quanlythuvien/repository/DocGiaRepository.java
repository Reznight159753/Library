package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia, Integer> {
    // Tìm độc giả theo tên
    List<DocGia> findByTenDGContaining(String tenDG);
    
    // Tìm độc giả theo số điện thoại
    List<DocGia> findBySdt(String sdt);
}