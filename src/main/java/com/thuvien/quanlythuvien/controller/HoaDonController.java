package com.thuvien.quanlythuvien.controller;

import com.thuvien.quanlythuvien.entity.HoaDon;
import com.thuvien.quanlythuvien.service.DocGiaService;
import com.thuvien.quanlythuvien.service.HoaDonService;
import com.thuvien.quanlythuvien.service.NhanVienService;
import com.thuvien.quanlythuvien.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private SachService sachService;

    @Autowired
    private DocGiaService docGiaService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/ban")
    public String showBanSachForm(Model model,
                                  @RequestParam(value = "searchSach", required = false) String searchSach,
                                  @RequestParam(value = "searchDocGia", required = false) String searchDocGia,
                                  @RequestParam(value = "searchNhanVien", required = false) String searchNhanVien) {
        model.addAttribute("hoaDon", new HoaDon());
        model.addAttribute("sachList", searchSach != null && !searchSach.isEmpty() ? 
                sachService.findByTenSach(searchSach) : sachService.findAll());
        model.addAttribute("docGiaList", searchDocGia != null && !searchDocGia.isEmpty() ? 
                docGiaService.findByTenDgContainingIgnoreCase(searchDocGia) : docGiaService.findAll());
        model.addAttribute("nhanVienList", searchNhanVien != null && !searchNhanVien.isEmpty() ? 
                nhanVienService.findByTenNv(searchNhanVien) : nhanVienService.findAll());
        model.addAttribute("hoaDons", hoaDonService.findAll());
        model.addAttribute("searchSach", searchSach);
        model.addAttribute("searchDocGia", searchDocGia);
        model.addAttribute("searchNhanVien", searchNhanVien);
        return "hoadon/ban";
    }

    @PostMapping("/ban")
    public String banSach(@ModelAttribute HoaDon hoaDon, 
                          @RequestParam("sachIds") List<Integer> sachIds, 
                          @RequestParam("soLuongs") List<Integer> soLuongs,
                          @RequestParam(value = "searchSach", required = false) String searchSach,
                          @RequestParam(value = "searchDocGia", required = false) String searchDocGia,
                          @RequestParam(value = "searchNhanVien", required = false) String searchNhanVien,
                          Model model) {
        try {
            hoaDonService.banSach(hoaDon, sachIds, soLuongs);
            model.addAttribute("successMessage", "Bán sách thành công!");
            return "redirect:/hoadon/ban";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("sachList", searchSach != null && !searchSach.isEmpty() ? 
                    sachService.findByTenSach(searchSach) : sachService.findAll());
            model.addAttribute("docGiaList", searchDocGia != null && !searchDocGia.isEmpty() ? 
                    docGiaService.findByTenDgContainingIgnoreCase(searchDocGia) : docGiaService.findAll());
            model.addAttribute("nhanVienList", searchNhanVien != null && !searchNhanVien.isEmpty() ? 
                    nhanVienService.findByTenNv(searchNhanVien) : nhanVienService.findAll());
            model.addAttribute("hoaDons", hoaDonService.findAll());
            model.addAttribute("searchSach", searchSach);
            model.addAttribute("searchDocGia", searchDocGia);
            model.addAttribute("searchNhanVien", searchNhanVien);
            return "hoadon/ban";
        }
    }

    @GetMapping("/list")
    public String listHoaDon(Model model) {
        model.addAttribute("hoaDons", hoaDonService.findAll());
        return "hoadon/list";
    }

    @GetMapping("/detail/{id}")
    public String showHoaDonDetail(@PathVariable Integer id, Model model) {
        try {
            HoaDon hoaDon = hoaDonService.findById(id);
            model.addAttribute("hoaDon", hoaDon);
            return "hoadon/detail";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/hoadon/list";
        }
    }
}