package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SachRepository extends JpaRepository<Sach, Integer> {
    List<Sach> findByTacGiaContainingIgnoreCase(String tacGia);

    List<Sach> findByMaLoai(Integer maLoai);

    @Query("SELECT COUNT(s) FROM Sach s")
    Long countTotalBooks();

    @Query("SELECT SUM(s.soLuong) FROM Sach s")
    Long sumTotalQuantity();

    @Query("SELECT s FROM Sach s WHERE s.tenSach LIKE %:keyword% AND s.trangThai = 'Available' ORDER BY s.tenSach ASC")
    List<Sach> findByTenSachContainingIgnoreCase(String keyword);

}
