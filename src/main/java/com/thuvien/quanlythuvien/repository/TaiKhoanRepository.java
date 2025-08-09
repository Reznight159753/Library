package com.thuvien.quanlythuvien.repository;

import com.thuvien.quanlythuvien.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    TaiKhoan findByTenTk(String tenTk);
    TaiKhoan findByTenTkAndMatKhau(String tenTk, String matKhau);
}