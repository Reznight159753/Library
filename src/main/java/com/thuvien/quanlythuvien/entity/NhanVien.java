package com.thuvien.quanlythuvien.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANV")
    private Integer maNV;
    
    @Column(name = "TENNV", length = 100)
    private String tenNV;
    
    @Column(name = "NGAYSINH")
    private LocalDate ngaySinh;
    
    @Column(name = "GIOITINH", length = 10)
    private String gioiTinh;
    
    @Column(name = "DIACHI", length = 255)
    private String diaChi;
    
    @Column(name = "SDT", length = 20)
    private String sdt;
    
    @Column(name = "CHUCVU", length = 50)
    private String chucVu;
    
    // Quan hệ với TaiKhoan
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaiKhoan> taiKhoans;
    
    // Constructors
    public NhanVien() {}
    
    public NhanVien(String tenNV, LocalDate ngaySinh, String gioiTinh, String diaChi, String sdt, String chucVu) {
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.chucVu = chucVu;
    }
    
    // Getters and Setters
    public Integer getMaNV() { return maNV; }
    public void setMaNV(Integer maNV) { this.maNV = maNV; }
    
    public String getTenNV() { return tenNV; }
    public void setTenNV(String tenNV) { this.tenNV = tenNV; }
    
    public LocalDate getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }
    
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    
    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }
    
    public List<TaiKhoan> getTaiKhoans() { return taiKhoans; }
    public void setTaiKhoans(List<TaiKhoan> taiKhoans) { this.taiKhoans = taiKhoans; }
}