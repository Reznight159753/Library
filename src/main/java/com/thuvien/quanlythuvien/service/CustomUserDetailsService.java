package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Override
    public UserDetails loadUserByUsername(String tenTk) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanService.findByTenTk(tenTk);
        if (taiKhoan == null) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản: " + tenTk);
        }
        // Tạo UserDetails với mật khẩu dạng full text (không mã hóa)
        return new User(
            taiKhoan.getTenTk(),
            taiKhoan.getMatKhau(),
            Collections.emptyList() // Nếu bạn muốn thêm vai trò (vaiTro), có thể xử lý ở đây
        );
    }
}