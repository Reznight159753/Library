package com.thuvien.quanlythuvien.controller;

import com.thuvien.quanlythuvien.entity.TaiKhoan;
import com.thuvien.quanlythuvien.service.TaiKhoanService;
import com.thuvien.quanlythuvien.service.SachService;
import com.thuvien.quanlythuvien.service.DocGiaService;
import com.thuvien.quanlythuvien.service.PhieuMuonService;
import com.thuvien.quanlythuvien.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DangNhapController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private SachService sachService;

    @Autowired
    private DocGiaService docGiaService;

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/")
    public String trangChu() {
        return "redirect:/dangnhap";
    }

    @GetMapping("/dangnhap")
    public String hienThiTrangDangNhap(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
            !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/home";
        }
        return "dangnhap";
    }

    @GetMapping("/home")
    public String trangChuChinh(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
            authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/dangnhap";
        }

        String username = authentication.getName();
        TaiKhoan taiKhoan = taiKhoanService.findByTenTk(username);
        if (taiKhoan == null) {
            return "redirect:/dangnhap";
        }

        Long totalBooks = sachService.countTotalBooks();
        Long totalQuantity = sachService.sumTotalQuantity();
        Long totalReaders = docGiaService.countTotalReaders();
        Long totalBorrowed = phieuMuonService.countBorrowed();
        Long totalSold = hoaDonService.countSold();

        model.addAttribute("taikhoan", taiKhoan);
        model.addAttribute("totalBooks", totalBooks != null ? totalBooks : 0);
        model.addAttribute("totalQuantity", totalQuantity != null ? totalQuantity : 0);
        model.addAttribute("totalReaders", totalReaders != null ? totalReaders : 0);
        model.addAttribute("totalBorrowed", totalBorrowed != null ? totalBorrowed : 0);
        model.addAttribute("totalSold", totalSold != null ? totalSold : 0);

        return "home";
    }

    @GetMapping("/dangxuat")
    public String dangXuat(HttpSession session) {
        session.invalidate();
        return "redirect:/dangnhap?logout";
    }
}