package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.Sach;
import com.thuvien.quanlythuvien.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SachService {

    @Autowired
    private SachRepository sachRepository;

    public List<Sach> findAll() {
        return sachRepository.findAll();
    }

    public Optional<Sach> findById(Integer id) {
        return sachRepository.findById(id);
    }

    public Sach save(Sach sach) {
        return sachRepository.save(sach);
    }

    public void deleteById(Integer id) {
        sachRepository.deleteById(id);
    }

    public List<Sach> findByTenSach(String tenSach) {
        return sachRepository.findByTenSachContainingIgnoreCase(tenSach);
    }

    public List<Sach> findByTacGia(String tacGia) {
        return sachRepository.findByTacGiaContainingIgnoreCase(tacGia);
    }

    public List<Sach> findByMaLoai(Integer maLoai) {
        return sachRepository.findByMaLoai(maLoai);
    }

    public Long countTotalBooks() {
        return sachRepository.countTotalBooks();
    }

    public Long sumTotalQuantity() {
        Long total = sachRepository.sumTotalQuantity();
        return total != null ? total : 0L;
    }
}