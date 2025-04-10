package com.example.demo.controller;

import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 로그인 폼 보여주는 GET 요청
    @GetMapping("/login.html")
    public String loginPage() {
        return "login"; // 로그인 페이지 보여줌
    }


}
