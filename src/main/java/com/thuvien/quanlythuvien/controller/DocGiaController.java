package com.thuvien.quanlythuvien.controller;

import com.thuvien.quanlythuvien.entity.DocGia;
import com.thuvien.quanlythuvien.service.DocGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/docgia")
public class DocGiaController {

    @Autowired
    private DocGiaService docGiaService;

    @GetMapping("/list")
    public String danhSachDocGia(Model model, @RequestParam(required = false) String search) {
        List<DocGia> docGiaList;
        if (search != null && !search.trim().isEmpty()) {
            docGiaList = docGiaService.findByTenDg(search.trim());
        } else {
            docGiaList = docGiaService.findAll();
        }
        model.addAttribute("docGiaList", docGiaList);
        model.addAttribute("search", search);
        return "docgia/list";
    }

    @GetMapping("/dangky")
    public String dangKyThanhVienForm(Model model) {
        model.addAttribute("docGia", new DocGia());
        return "docgia/dangky";
    }

    @PostMapping("/dangky")
    public String dangKyThanhVien(@ModelAttribute DocGia docGia, RedirectAttributes redirectAttributes) {
        try {
            docGiaService.save(docGia);
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi đăng ký thành viên!");
        }
        return "redirect:/docgia/list";
    }
}