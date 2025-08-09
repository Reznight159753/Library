package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.NhanVien;
import com.thuvien.quanlythuvien.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    public Optional<NhanVien> findById(Integer id) {
        return nhanVienRepository.findById(id);
    }

    public NhanVien save(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public void deleteById(Integer id) {
        nhanVienRepository.deleteById(id);
    }

    public List<NhanVien> findByTenNv(String tenNv) {
        return nhanVienRepository.findByTenNvContainingIgnoreCase(tenNv);
    }

    public List<NhanVien> findBySdt(String sdt) {
        return nhanVienRepository.findBySdtContainingIgnoreCase(sdt);
    }

    public List<NhanVien> findByChucVu(String chucVu) {
        return nhanVienRepository.findByChucVu(chucVu);
    }

    public Long countTotalEmployees() {
        return nhanVienRepository.countTotalEmployees();
    }
}