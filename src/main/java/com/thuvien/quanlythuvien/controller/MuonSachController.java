package com.thuvien.quanlythuvien.controller;

import com.thuvien.quanlythuvien.entity.PhieuMuon;
import com.thuvien.quanlythuvien.service.DocGiaService;
import com.thuvien.quanlythuvien.service.NhanVienService;
import com.thuvien.quanlythuvien.service.PhieuMuonService;
import com.thuvien.quanlythuvien.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/muonsach")
public class MuonSachController {

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private SachService sachService;

    @Autowired
    private DocGiaService docGiaService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/muon")
    public String showMuonSachForm(Model model,
                                   @RequestParam(value = "searchSach", required = false) String searchSach,
                                   @RequestParam(value = "searchDocGia", required = false) String searchDocGia,
                                   @RequestParam(value = "searchNhanVien", required = false) String searchNhanVien) {
        model.addAttribute("phieuMuon", new PhieuMuon());
        model.addAttribute("sachList", searchSach != null && !searchSach.isEmpty() ? 
                sachService.findByTenSach(searchSach) : sachService.findAll());
        model.addAttribute("docGiaList", searchDocGia != null && !searchDocGia.isEmpty() ? 
                docGiaService.findByTenDgContainingIgnoreCase(searchDocGia) : docGiaService.findAll());
        model.addAttribute("nhanVienList", searchNhanVien != null && !searchNhanVien.isEmpty() ? 
                nhanVienService.findByTenNv(searchNhanVien) : nhanVienService.findAll());
        model.addAttribute("phieuMuons", phieuMuonService.findDangMuon());
        model.addAttribute("searchSach", searchSach);
        model.addAttribute("searchDocGia", searchDocGia);
        model.addAttribute("searchNhanVien", searchNhanVien);
        return "muonsach/muon";
    }

    @PostMapping("/muon")
    public String muonSach(@ModelAttribute PhieuMuon phieuMuon, 
                           @RequestParam("sachIds") List<Integer> sachIds, 
                           @RequestParam("soLuongMuons") List<Integer> soLuongMuons,
                           @RequestParam(value = "searchSach", required = false) String searchSach,
                           @RequestParam(value = "searchDocGia", required = false) String searchDocGia,
                           @RequestParam(value = "searchNhanVien", required = false) String searchNhanVien,
                           Model model) {
        try {
            phieuMuonService.muonSach(phieuMuon, sachIds, soLuongMuons);
            model.addAttribute("successMessage", "Mượn sách thành công!");
            return "redirect:/muonsach/muon";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("sachList", searchSach != null && !searchSach.isEmpty() ? 
                    sachService.findByTenSach(searchSach) : sachService.findAll());
            model.addAttribute("docGiaList", searchDocGia != null && !searchDocGia.isEmpty() ? 
                    docGiaService.findByTenDgContainingIgnoreCase(searchDocGia) : docGiaService.findAll());
            model.addAttribute("nhanVienList", searchNhanVien != null && !searchNhanVien.isEmpty() ? 
                    nhanVienService.findByTenNv(searchNhanVien) : nhanVienService.findAll());
            model.addAttribute("phieuMuons", phieuMuonService.findDangMuon());
            model.addAttribute("searchSach", searchSach);
            model.addAttribute("searchDocGia", searchDocGia);
            model.addAttribute("searchNhanVien", searchNhanVien);
            return "muonsach/muon";
        }
    }

    @PostMapping("/tra/{id}")
    public String traSach(@PathVariable Integer id, Model model,
                          @RequestParam(value = "searchSach", required = false) String searchSach,
                          @RequestParam(value = "searchDocGia", required = false) String searchDocGia,
                          @RequestParam(value = "searchNhanVien", required = false) String searchNhanVien) {
        try {
            phieuMuonService.traSach(id);
            model.addAttribute("successMessage", "Trả sách thành công!");
            return "redirect:/muonsach/muon";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("phieuMuons", phieuMuonService.findDangMuon());
            model.addAttribute("sachList", searchSach != null && !searchSach.isEmpty() ? 
                    sachService.findByTenSach(searchSach) : sachService.findAll());
            model.addAttribute("docGiaList", searchDocGia != null && !searchDocGia.isEmpty() ? 
                    docGiaService.findByTenDgContainingIgnoreCase(searchDocGia) : docGiaService.findAll());
            model.addAttribute("nhanVienList", searchNhanVien != null && !searchNhanVien.isEmpty() ? 
                    nhanVienService.findByTenNv(searchNhanVien) : nhanVienService.findAll());
            model.addAttribute("searchSach", searchSach);
            model.addAttribute("searchDocGia", searchDocGia);
            model.addAttribute("searchNhanVien", searchNhanVien);
            return "muonsach/muon";
        }
    }

    @GetMapping("/list")
    public String listPhieuMuon(Model model) {
        model.addAttribute("phieuMuons", phieuMuonService.findAll());
        return "muonsach/list";
    }

    @GetMapping("/detail/{id}")
    public String showPhieuMuonDetail(@PathVariable Integer id, Model model) {
        try {
            PhieuMuon phieuMuon = phieuMuonService.findById(id);
            model.addAttribute("phieuMuon", phieuMuon);
            return "muonsach/detail";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/muonsach/list";
        }
    }
}