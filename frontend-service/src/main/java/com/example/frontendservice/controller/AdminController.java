package com.example.frontendservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
@Controller
@RequestMapping("/admin/page")  // 기본 경로 prefix 추가
public class AdminController {

    private final RestTemplate restTemplate;

    public AdminController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")  // /admin/page/login
    public String showLoginForm() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            // FRONT-ADMIN API URL (api 경로로 변경)
            String url = "http://FRONT-ADMIN/admin/api/login";

            // 요청 데이터 생성
            var request = new org.springframework.http.HttpEntity<>(new LoginRequest(email, password));

            // FrontAdmin 서비스로 로그인 요청 전송
            var response = restTemplate.postForEntity(url, request, LoginResponse.class);

            // 로그인 성공 처리
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                model.addAttribute("user", response.getBody().getUser());
                return "redirect:/admin/page/dashboard"; // 경로 수정
            }
        } catch (Exception e) {
            model.addAttribute("error", "로그인 실패: " + e.getMessage());
        }

        return "users/login";
    }

    // 내부 클래스들은 그대로 유지
    static class LoginRequest {
        private String email;
        private String password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }

    static class LoginResponse {
        private User user;
        public User getUser() { return user; }
        public void setUser(User user) { this.user = user; }
    }

    static class User {
        private String name;
        private String email;

        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
