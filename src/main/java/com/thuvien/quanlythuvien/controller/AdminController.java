package com.thuvien.quanlythuvien.controller;

import com.thuvien.quanlythuvien.entity.NhanVien;
import com.thuvien.quanlythuvien.entity.TaiKhoan;
import com.thuvien.quanlythuvien.entity.LoaiSach;
import com.thuvien.quanlythuvien.repository.NhanVienRepository;
import com.thuvien.quanlythuvien.repository.TaiKhoanRepository;
import com.thuvien.quanlythuvien.repository.LoaiSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/quanly")
@PreAuthorize("hasRole('QUANLY')")
public class AdminController {
    
    @Autowired
    private NhanVienRepository nhanVienRepository;
    
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    
    @Autowired
    private LoaiSachRepository loaiSachRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Dashboard quản lý
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("soNhanVien", nhanVienRepository.count());
        model.addAttribute("soTaiKhoan", taiKhoanRepository.count());
        model.addAttribute("soLoaiSach", loaiSachRepository.count());
        return "admin/dashboard";
    }
    
    // ====== QUẢN LÝ NHÂN VIÊN ======
    @GetMapping("/nhan-vien")
    public String danhSachNhanVien(Model model) {
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        model.addAttribute("nhanVienList", nhanVienList);
        return "admin/nhan-vien/danh-sach";
    }
    
    @GetMapping("/nhan-vien/them")
    public String formThemNhanVien(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "admin/nhan-vien/them";
    }
    
    @PostMapping("/nhan-vien/them")
    public String themNhanVien(@ModelAttribute NhanVien nhanVien, RedirectAttributes redirectAttributes) {
        try {
            nhanVienRepository.save(nhanVien);
            redirectAttributes.addFlashAttribute("success", "Thêm nhân viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm nhân viên!");
        }
        return "redirect:/quanly/nhan-vien";
    }
    
    @GetMapping("/nhan-vien/sua/{id}")
    public String formSuaNhanVien(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<NhanVien> nhanVien = nhanVienRepository.findById(id);
        if (nhanVien.isPresent()) {
            model.addAttribute("nhanVien", nhanVien.get());
            return "admin/nhan-vien/sua";
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy nhân viên!");
            return "redirect:/quanly/nhan-vien";
        }
    }
    
    @PostMapping("/nhan-vien/sua/{id}")
    public String suaNhanVien(@PathVariable Integer id, @ModelAttribute NhanVien nhanVien, RedirectAttributes redirectAttributes) {
        try {
            nhanVien.setMaNV(id);
            nhanVienRepository.save(nhanVien);
            redirectAttributes.addFlashAttribute("success", "Cập nhật nhân viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật nhân viên!");
        }
        return "redirect:/quanly/nhan-vien";
    }
    
    // ====== QUẢN LÝ TÀI KHOẢN ======
    @GetMapping("/tai-khoan")
    public String danhSachTaiKhoan(Model model) {
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
        model.addAttribute("taiKhoanList", taiKhoanList);
        return "admin/tai-khoan/danh-sach";
    }
    
    @GetMapping("/tai-khoan/them")
    public String formThemTaiKhoan(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("nhanVienList", nhanVienRepository.findAll());
        return "admin/tai-khoan/them";
    }
    
    @PostMapping("/tai-khoan/them")
    public String themTaiKhoan(@ModelAttribute TaiKhoan taiKhoan, RedirectAttributes redirectAttributes) {
        try {
            // Mã hóa mật khẩu
            taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
            taiKhoanRepository.save(taiKhoan);
            redirectAttributes.addFlashAttribute("success", "Tạo tài khoản thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi tạo tài khoản!");
        }
        return "redirect:/quanly/tai-khoan";
    }
    
    // ====== QUẢN LÝ LOẠI SÁCH ======
    @GetMapping("/loai-sach")
    public String danhSachLoaiSach(Model model) {
        List<LoaiSach> loaiSachList = loaiSachRepository.findAll();
        model.addAttribute("loaiSachList", loaiSachList);
        return "admin/loai-sach/danh-sach";
    }
    
    @GetMapping("/loai-sach/them")
    public String formThemLoaiSach(Model model) {
        model.addAttribute("loaiSach", new LoaiSach());
        return "admin/loai-sach/them";
    }
    
    @PostMapping("/loai-sach/them")
    public String themLoaiSach(@ModelAttribute LoaiSach loaiSach, RedirectAttributes redirectAttributes) {
        try {
            loaiSachRepository.save(loaiSach);
            redirectAttributes.addFlashAttribute("success", "Thêm loại sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm loại sách!");
        }
        return "redirect:/quanly/loai-sach";
    }
}