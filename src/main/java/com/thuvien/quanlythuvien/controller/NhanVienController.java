package com.thuvien.quanlythuvien.controller;

import com.thuvien.quanlythuvien.entity.NhanVien;
import com.thuvien.quanlythuvien.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/list")
    public String danhSachNhanVien(Model model, @RequestParam(required = false) String search) {
        List<NhanVien> nhanVienList;
        if (search != null && !search.trim().isEmpty()) {
            nhanVienList = nhanVienService.findByTenNv(search.trim());
        } else {
            nhanVienList = nhanVienService.findAll();
        }
        model.addAttribute("nhanVienList", nhanVienList);
        model.addAttribute("search", search);
        return "nhanvien/list";
    }

    @GetMapping("/add")
    public String themNhanVienForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien/add";
    }

    @PostMapping("/add")
    public String themNhanVien(@ModelAttribute NhanVien nhanVien, RedirectAttributes redirectAttributes) {
        try {
            nhanVienService.save(nhanVien);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm nhân viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm nhân viên!");
        }
        return "redirect:/nhanvien/list";
    }

    @GetMapping("/edit/{id}")
    public String suaNhanVienForm(@PathVariable Integer id, Model model) {
        Optional<NhanVien> nhanVien = nhanVienService.findById(id);
        if (nhanVien.isPresent()) {
            model.addAttribute("nhanVien", nhanVien.get());
            return "nhanvien/edit";
        }
        return "redirect:/nhanvien/list";
    }

    @PostMapping("/edit")
    public String suaNhanVien(@ModelAttribute NhanVien nhanVien, RedirectAttributes redirectAttributes) {
        try {
            nhanVienService.save(nhanVien);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật nhân viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật nhân viên!");
        }
        return "redirect:/nhanvien/list";
    }

    @GetMapping("/delete/{id}")
    public String xoaNhanVien(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            nhanVienService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa nhân viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa nhân viên! " + e);
        }
        return "redirect:/nhanvien/list";
    }
}