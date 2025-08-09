package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.LoaiSach;
import com.thuvien.quanlythuvien.repository.LoaiSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoaiSachService {

    @Autowired
    private LoaiSachRepository loaiSachRepository;

    public List<LoaiSach> findAll() {
        return loaiSachRepository.findAll();
    }
}