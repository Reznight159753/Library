package com.thuvien.quanlythuvien.service;

import com.thuvien.quanlythuvien.entity.ChiTietHoaDon;
import com.thuvien.quanlythuvien.entity.HoaDon;
import com.thuvien.quanlythuvien.entity.Sach;
import com.thuvien.quanlythuvien.repository.ChiTietHoaDonRepository;
import com.thuvien.quanlythuvien.repository.HoaDonRepository;
import com.thuvien.quanlythuvien.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    @Autowired
    private SachRepository sachRepository;

    public void banSach(HoaDon hoaDon, List<Integer> sachIds, List<Integer> soLuongs) {
        if (sachIds == null || soLuongs == null || sachIds.size() != soLuongs.size()) {
            throw new RuntimeException("Dữ liệu sách hoặc số lượng không hợp lệ");
        }
        if (hoaDon.getDocGia() == null || hoaDon.getNhanVien() == null) {
            throw new RuntimeException("Vui lòng chọn độc giả và nhân viên");
        }
        hoaDon.setNgayLap(LocalDate.now());
        hoaDon.setLoai("BAN_SACH");
        BigDecimal tongTien = BigDecimal.ZERO;

        for (int i = 0; i < sachIds.size(); i++) {
            Integer sachId = sachIds.get(i);
            Integer soLuong = soLuongs.get(i);
            if (sachId == null || soLuong == null || soLuong <= 0) {
                throw new RuntimeException("Sách hoặc số lượng không hợp lệ");
            }
            Sach sach = sachRepository.findById(sachId).orElseThrow(() -> new RuntimeException("Sách không tồn tại: ID " + sachId));
            if (sach.getSoLuong() < soLuong) {
                throw new RuntimeException("Số lượng sách không đủ: " + sach.getTenSach());
            }
            ChiTietHoaDon chiTiet = new ChiTietHoaDon();
            chiTiet.setHoaDon(hoaDon);
            chiTiet.setSach(sach);
            chiTiet.setSoLuong(soLuong);
            chiTiet.setDonGia(sach.getDonGia());
            chiTietHoaDonRepository.save(chiTiet);
            sach.setSoLuong(sach.getSoLuong() - soLuong);
            if (sach.getSoLuong() == 0) {
                sach.setTrangThai("Unavailable");
            }
            sachRepository.save(sach);
            tongTien = tongTien.add(sach.getDonGia().multiply(new BigDecimal(soLuong)));
        }

        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);
    }

    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    public HoaDon findById(Integer id) {
        return hoaDonRepository.findById(id).orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
    }
    // Thêm phương thức mới
    public Long countSold() {
        return hoaDonRepository.countByLoai("Sale");
    }
}