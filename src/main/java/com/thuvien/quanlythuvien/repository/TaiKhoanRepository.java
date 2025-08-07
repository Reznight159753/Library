package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    // Tìm tài khoản theo tên đăng nhập
    Optional<TaiKhoan> findByTenTK(String tenTK);
    
    // Kiểm tra tồn tại tài khoản
    boolean existsByTenTK(String tenTK);
}