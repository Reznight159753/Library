package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.TaiKhoan;
import com.thuvien.quanlythuvien.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {
    
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    
    public TaiKhoan findByTenTk(String tenTk) {
        return taiKhoanRepository.findByTenTk(tenTk);
    }
    
    public boolean kiemTraDangNhap(String tenTk, String matKhau) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenTkAndMatKhau(tenTk, matKhau);
        return taiKhoan != null;
    }
    
    public TaiKhoan dangNhap(String tenTk, String matKhau) {
        return taiKhoanRepository.findByTenTkAndMatKhau(tenTk, matKhau);
    }
}