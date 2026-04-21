package com.max.MyProject.controllers;

import com.max.MyProject.dto.UserDto;
import com.max.MyProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
     model.addAttribute("userDto", new UserDto());
     return "signup";
    }
    @PostMapping("/signup")
    public String signup(@ModelAttribute("userDto") UserDto dto) {
        userService.createUser(dto);
        return "redirect:/";
    }
}
