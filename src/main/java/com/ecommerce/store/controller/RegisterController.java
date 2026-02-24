package com.ecommerce.store.controller;


import com.ecommerce.store.entity.User;
import com.ecommerce.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String register(@ModelAttribute User user, Model model) {

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "❌ Email already registered!");
            return "register";
        }

        userService.registerUser(user);
        return "redirect:/login?registered";
    }
}
