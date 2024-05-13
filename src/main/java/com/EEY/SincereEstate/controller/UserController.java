package com.EEY.SincereEstate.controller;


import com.EEY.SincereEstate.entity.User;
import com.EEY.SincereEstate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());

        return "register";
    }

    @GetMapping("/my-profile/edit")
    public String showEditProfile(Model model, Principal principal) {
        String activeEmail=principal.getName();
        Optional<User> activeUser=userService.getUserByEmail(activeEmail);
        activeUser.ifPresent(user->model.addAttribute("activeUser",user));
        return "edit-profile";
    }

    @PostMapping("/my-profile/edit")
    public String editProfile( @Valid @ModelAttribute User user){
        return "redirect:/my-profile";
    }

    @PostMapping("/processRegister")
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(user);

        return "redirect:/login";
    }
    @GetMapping("/my-profile")
    public String profile(Model model, Principal principal) {
        String activeEmail=principal.getName();

        Optional<User> activeUser=userService.getUserByEmail(activeEmail);

        activeUser.ifPresent(user->model.addAttribute("properties",userService.getPropertiesByUser(activeUser.get().getId())));
        activeUser.ifPresent(user -> model.addAttribute("activeUser", user));

        return "profile.html";
    }
}
