package com.EEY.SincereEstate.controller;

import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;
import com.EEY.SincereEstate.service.PropertyService;
import com.EEY.SincereEstate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DemoController {

    private UserService userService;
    private PropertyService propertyService;

    @Autowired
    public DemoController(UserService userService,PropertyService propertyService){
        this.userService=userService;
        this.propertyService=propertyService;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        List<Property> properties=propertyService.findAll();
        model.addAttribute("properties", properties);
        return "homepage";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
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



    @GetMapping("/users/{userId}/properties")
    public String properties(@PathVariable int userId, Model model) {
        List<Property> properties=userService.getPropertiesByUser(userId);
        model.addAttribute("properties", properties);
        return "properties";
    }

    @GetMapping("/property/{propertyId}")
    public String showProperty(@PathVariable int propertyId, Model model) {
        Property property=propertyService.findById(propertyId);
        User user=propertyService.getOwner(propertyId);

        model.addAttribute("user", user);
        model.addAttribute("property", property);
        return "property";

    }

    @PostMapping("/processRegister")
    public String processRegister(@ModelAttribute("user") User user) {
        userService.register(user);

        return "redirect:/login";
    }

}
