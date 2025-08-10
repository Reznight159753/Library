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
    public String listDocGia(Model model, @RequestParam(required = false) String search) {
        List<DocGia> docGiaList;
        if (search != null && !search.trim().isEmpty()) {
            docGiaList = docGiaService.findByTenDgContainingIgnoreCase(search.trim());
        } else {
            docGiaList = docGiaService.findAll();
        }
        model.addAttribute("docGiaList", docGiaList);
        model.addAttribute("search", search);
        return "docgia/list";
    }

    @GetMapping("/edit/{id}")
    public String editDocGiaForm(@PathVariable Integer id, Model model) {
        model.addAttribute("docGia", docGiaService.findById(id).orElse(new DocGia()));
        return "docgia/edit"; // Cần tạo file edit.html
    }

    @PostMapping("/edit")
    public String editDocGia(@ModelAttribute DocGia docGia, RedirectAttributes redirectAttributes) {
        docGiaService.save(docGia);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật độc giả thành công!");
        return "redirect:/docgia/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDocGia(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        docGiaService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa độc giả thành công!");
        return "redirect:/docgia/list";
    }

    @GetMapping("/dangky")
    public String dangKyForm(Model model) {
        model.addAttribute("docGia", new DocGia());
        return "docgia/dangky";
    }

    @PostMapping("/dangky")
    public String dangKy(@ModelAttribute DocGia docGia, RedirectAttributes redirectAttributes) {
        docGiaService.save(docGia);
        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký độc giả thành công!");
        return "redirect:/docgia/list";
    }
}