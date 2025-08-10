package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia, Integer> {

    List<DocGia> findBySdtContainingIgnoreCase(String sdt);

    @Query("SELECT COUNT(d) FROM DocGia d")
    Long countTotalReaders();

    @Query("SELECT dg FROM DocGia dg WHERE dg.tenDg LIKE %:keyword%")
    List<DocGia> findByTenDgContainingIgnoreCase(String keyword);
}