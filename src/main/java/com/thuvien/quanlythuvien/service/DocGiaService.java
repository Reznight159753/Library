package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.DocGia;
import com.thuvien.quanlythuvien.repository.DocGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocGiaService {

    @Autowired
    private DocGiaRepository docGiaRepository;

    public List<DocGia> findAll() {
        return docGiaRepository.findAll();
    }

    public Optional<DocGia> findById(Integer id) {
        return docGiaRepository.findById(id);
    }

    public DocGia save(DocGia docGia) {
        return docGiaRepository.save(docGia);
    }

    public void deleteById(Integer id) {
        docGiaRepository.deleteById(id);
    }

    public List<DocGia> findByTenDg(String tenDg) {
        return docGiaRepository.findByTenDgContainingIgnoreCase(tenDg);
    }

    public List<DocGia> findBySdt(String sdt) {
        return docGiaRepository.findBySdtContainingIgnoreCase(sdt);
    }

    public Long countTotalReaders() {
        return docGiaRepository.countTotalReaders();
    }
}