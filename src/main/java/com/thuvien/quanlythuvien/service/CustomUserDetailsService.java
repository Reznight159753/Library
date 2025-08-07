package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.TaiKhoan;
import com.thuvien.quanlythuvien.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenTK(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + username));
        
        return new User(
                taiKhoan.getTenTK(),
                taiKhoan.getMatKhau(),
                mapRolesToAuthorities(taiKhoan.getVaiTro())
        );
    }
    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String vaiTro) {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + vaiTro));
    }
}