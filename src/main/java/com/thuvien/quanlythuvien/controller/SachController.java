package com.thuvien.quanlythuvien.controller;

import com.thuvien.quanlythuvien.entity.Sach;
import com.thuvien.quanlythuvien.entity.LoaiSach;
import com.thuvien.quanlythuvien.service.SachService;
import com.thuvien.quanlythuvien.service.LoaiSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sach")
public class SachController {

    @Autowired
    private SachService sachService;

    @Autowired
    private LoaiSachService loaiSachService;

    @GetMapping("/list")
    public String danhSachSach(Model model, @RequestParam(required = false) String search) {
        List<Sach> sachList;
        
        if (search != null && !search.trim().isEmpty()) {
            sachList = sachService.findByTenSach(search.trim());
        } else {
            sachList = sachService.findAll();
        }
        
        model.addAttribute("sachList", sachList);
        model.addAttribute("search", search);
        return "sach/list";
    }

    @GetMapping("/add")
    public String themSachForm(Model model) {
        model.addAttribute("sach", new Sach());
        model.addAttribute("loaiSachList", loaiSachService.findAll());
        return "sach/add";
    }

    @PostMapping("/add")
    public String themSach(@ModelAttribute Sach sach, RedirectAttributes redirectAttributes) {
        try {
            sachService.save(sach);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm sách!");
        }
        return "redirect:/sach/list";
    }

    @GetMapping("/edit/{id}")
    public String suaSachForm(@PathVariable Integer id, Model model) {
        Optional<Sach> sach = sachService.findById(id);
        if (sach.isPresent()) {
            model.addAttribute("sach", sach.get());
            model.addAttribute("loaiSachList", loaiSachService.findAll());
            return "sach/edit";
        }
        return "redirect:/sach/list";
    }

    @PostMapping("/edit")
    public String suaSach(@ModelAttribute Sach sach, RedirectAttributes redirectAttributes) {
        try {
            sachService.save(sach);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật sách!");
        }
        return "redirect:/sach/list";
    }

    @GetMapping("/delete/{id}")
    public String xoaSach(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            sachService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa sách!" + e);
        }
        return "redirect:/sach/list";
    }
}