package com.thuvien.quanlythuvien.config;

import com.thuvien.quanlythuvien.entity.NhanVien;
import com.thuvien.quanlythuvien.entity.TaiKhoan;
import com.thuvien.quanlythuvien.entity.LoaiSach;
import com.thuvien.quanlythuvien.repository.NhanVienRepository;
import com.thuvien.quanlythuvien.repository.TaiKhoanRepository;
import com.thuvien.quanlythuvien.repository.LoaiSachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private LoaiSachRepository loaiSachRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ FIX LỖI NULL

    @Override
    public void run(String... args) throws Exception {
        if (taiKhoanRepository.count() > 0) {
            return;
        }

        System.out.println("Đang khởi tạo dữ liệu mẫu...");

        // Nhân viên quản lý
        NhanVien quanLy = new NhanVien();
        quanLy.setTenNV("Nguyễn Văn Quản lý");
        quanLy.setNgaySinh(LocalDate.of(1980, 1, 15));
        quanLy.setGioiTinh("Nam");
        quanLy.setDiaChi("Hà Nội");
        quanLy.setSdt("0901234567");
        quanLy.setChucVu("Quản lý");
        quanLy = nhanVienRepository.save(quanLy);

        // Nhân viên thủ thư
        NhanVien thuThu = new NhanVien();
        thuThu.setTenNV("Trần Thị Thủ thư");
        thuThu.setNgaySinh(LocalDate.of(1990, 6, 20));
        thuThu.setGioiTinh("Nữ");
        thuThu.setDiaChi("TP.HCM");
        thuThu.setSdt("0987654321");
        thuThu.setChucVu("Thủ thư");
        thuThu = nhanVienRepository.save(thuThu);

        // Tài khoản quản lý
        TaiKhoan tkQuanLy = new TaiKhoan();
        tkQuanLy.setTenTK("admin");
        tkQuanLy.setMatKhau(passwordEncoder.encode("admin123")); // dùng encode để đồng bộ
        tkQuanLy.setVaiTro("QUANLY");
        tkQuanLy.setNhanVien(quanLy);
        taiKhoanRepository.save(tkQuanLy);

        // Tài khoản thủ thư
        TaiKhoan tkThuThu = new TaiKhoan();
        tkThuThu.setTenTK("thuthu");
        tkThuThu.setMatKhau(passwordEncoder.encode("thuthu123")); // ✅ Bắt buộc encode
        tkThuThu.setVaiTro("THUTHU");
        tkThuThu.setNhanVien(thuThu);
        taiKhoanRepository.save(tkThuThu);

        // Loại sách
        loaiSachRepository.save(new LoaiSach("Khoa học"));
        loaiSachRepository.save(new LoaiSach("Tiểu thuyết"));
        loaiSachRepository.save(new LoaiSach("Lịch sử"));
        loaiSachRepository.save(new LoaiSach("Kỹ năng sống"));
        loaiSachRepository.save(new LoaiSach("Công nghệ"));

        System.out.println("Dữ liệu mẫu đã được khởi tạo thành công!");
        System.out.println("Tài khoản quản lý: admin / admin123");
        System.out.println("Tài khoản thủ thư: thuthu / thuthu123");
    }
}
