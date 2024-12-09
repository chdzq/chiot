package org.chdzq.authentication.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author chdzq
 * @Date 2024/11/14
 */
@RestController
public class Test {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession session) {
        return "login";
    }
}
