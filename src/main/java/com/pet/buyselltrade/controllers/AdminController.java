package com.pet.buyselltrade.controllers;

import com.pet.buyselltrade.enums.Role;
import com.pet.buyselltrade.models.UserModel;
import com.pet.buyselltrade.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.list());
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user")UserModel userModel,Model model){
        model.addAttribute("user",userModel);
        model.addAttribute("roles", Role.values());
        return "user_edit";
    }
    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") UserModel userModel, @RequestParam Map<String,String> form){
        userService.changeUserRoles(userModel,form);
        return "redirect:/admin";
    }
}
