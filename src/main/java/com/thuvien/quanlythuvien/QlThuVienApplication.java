
package com.thuvien.quanlythuvien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QlThuVienApplication {
    public static void main(String[] args) {
        SpringApplication.run(QlThuVienApplication.class, args);
        System.out.println("Hệ thống quản lý thư viện đã khởi động!");
        System.out.println("Truy cập: http://localhost:8080/thuvien");
    }
}
