package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.ChiTietPhieuMuon;
import com.thuvien.quanlythuvien.entity.PhieuMuon;
import com.thuvien.quanlythuvien.entity.Sach;
import com.thuvien.quanlythuvien.repository.ChiTietPhieuMuonRepository;
import com.thuvien.quanlythuvien.repository.PhieuMuonRepository;
import com.thuvien.quanlythuvien.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhieuMuonService {

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private ChiTietPhieuMuonRepository chiTietPhieuMuonRepository;

    @Autowired
    private SachRepository sachRepository;

    public void muonSach(PhieuMuon phieuMuon, List<Integer> sachIds, List<Integer> soLuongMuons) {
        if (sachIds == null || soLuongMuons == null || sachIds.size() != soLuongMuons.size()) {
            throw new RuntimeException("Dữ liệu sách hoặc số lượng không hợp lệ");
        }
        if (phieuMuon.getDocGia() == null || phieuMuon.getNhanVien() == null) {
            throw new RuntimeException("Vui lòng chọn độc giả và nhân viên");
        }
        phieuMuon.setNgayMuon(LocalDate.now());
        phieuMuon.setNgayTraDuKien(LocalDate.now().plusDays(7));
        phieuMuon.setTrangThai("DANG_MUON");
        phieuMuonRepository.save(phieuMuon);

        for (int i = 0; i < sachIds.size(); i++) {
            Integer sachId = sachIds.get(i);
            Integer soLuongMuon = soLuongMuons.get(i);
            if (sachId == null || soLuongMuon == null || soLuongMuon <= 0) {
                throw new RuntimeException("Sách hoặc số lượng mượn không hợp lệ");
            }
            Sach sach = sachRepository.findById(sachId).orElseThrow(() -> new RuntimeException("Sách không tồn tại: ID " + sachId));
            if (sach.getSoLuong() < soLuongMuon) {
                throw new RuntimeException("Số lượng sách không đủ: " + sach.getTenSach());
            }
            ChiTietPhieuMuon chiTiet = new ChiTietPhieuMuon();
            chiTiet.setPhieuMuon(phieuMuon);
            chiTiet.setSach(sach);
            chiTiet.setSoLuongMuon(soLuongMuon);
            chiTietPhieuMuonRepository.save(chiTiet);
            sach.setSoLuong(sach.getSoLuong() - soLuongMuon);
            if (sach.getSoLuong() == 0) {
                sach.setTrangThai("Unavailable");
            }
            sachRepository.save(sach);
        }
    }

    public void traSach(Integer maPhieuMuon) {
        PhieuMuon phieuMuon = phieuMuonRepository.findById(maPhieuMuon).orElseThrow(() -> new RuntimeException("Phiếu mượn không tồn tại"));
        if (!phieuMuon.getTrangThai().equals("DANG_MUON")) {
            throw new RuntimeException("Phiếu mượn không ở trạng thái đang mượn");
        }
        phieuMuon.setNgayTraThucTe(LocalDate.now());
        phieuMuon.setTrangThai("DA_TRA");
        if (phieuMuon.getNgayTraThucTe().isAfter(phieuMuon.getNgayTraDuKien())) {
            long daysLate = phieuMuon.getNgayTraThucTe().toEpochDay() - phieuMuon.getNgayTraDuKien().toEpochDay();
            phieuMuon.setPhiPhat(phieuMuon.getPhiPhat().add(new java.math.BigDecimal(daysLate * 5000)));
        }
        phieuMuonRepository.save(phieuMuon);

        List<ChiTietPhieuMuon> chiTiets = chiTietPhieuMuonRepository.findByPhieuMuonMaPhieuMuon(maPhieuMuon);
        for (ChiTietPhieuMuon chiTiet : chiTiets) {
            Sach sach = chiTiet.getSach();
            sach.setSoLuong(sach.getSoLuong() + chiTiet.getSoLuongMuon());
            sach.setTrangThai("Available");
            sachRepository.save(sach);
        }
    }

    public List<PhieuMuon> findAll() {
        return phieuMuonRepository.findAll();
    }

    public List<PhieuMuon> findDangMuon() {
        return phieuMuonRepository.findAll().stream()
                .filter(pm -> "DANG_MUON".equals(pm.getTrangThai()))
                .collect(Collectors.toList());
    }

    public PhieuMuon findById(Integer id) {
        return phieuMuonRepository.findById(id).orElseThrow(() -> new RuntimeException("Phiếu mượn không tồn tại"));
    }
        public Long countBorrowed() {
        return phieuMuonRepository.countByTrangThai("Borrowed");
    }
}