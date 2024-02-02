package com.pet.buyselltrade.controllers;

import com.pet.buyselltrade.models.UserModel;
import com.pet.buyselltrade.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Неверное имя пользователя или пароль");
        }
        return "login";
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(UserModel user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с этим email" + user.getEmail() + "уже существует");


            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("user/{user}")
    public String userInfo(@PathVariable("user") UserModel userModel, Model model) {
        model.addAttribute("user",userModel);
        model.addAttribute("products", userModel.getProducts());
        return "user_info";
    }

    @GetMapping("/hello")
    public String hello(HttpServletResponse response) {
        response.setHeader("Refresh", "1; /");
        return "hello";
    }
}
