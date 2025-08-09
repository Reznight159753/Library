package com.thuvien.quanlythuvien.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/dangnhap", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/dangnhap")
                .loginProcessingUrl("/dangnhap")
                .usernameParameter("tenTk") // Ánh xạ trường tenTk
                .passwordParameter("matKhau") // Ánh xạ trường matKhau
                .defaultSuccessUrl("/home", true)
                .failureUrl("/dangnhap?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/dangxuat")
                .logoutSuccessUrl("/dangnhap?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Tạm thời vô hiệu hóa CSRF để test

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Sử dụng NoOpPasswordEncoder để hỗ trợ mật khẩu plain text
    }
}