package com.EEY.SincereEstate.controller;

import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;
import com.EEY.SincereEstate.service.PropertyService;
import com.EEY.SincereEstate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/my-profile")
    public String profile(Model model, Principal principal) {
        String activeEmail=principal.getName();

        Optional<User> activeUser=userService.getUserByEmail(activeEmail);

        activeUser.ifPresent(user->model.addAttribute("properties",userService.getPropertiesByUser(activeUser.get().getId())));
        activeUser.ifPresent(user -> model.addAttribute("activeUser", user));
        System.out.println(userService.getPropertiesByUser(activeUser.get().getId()).size());

        return "profile.html";
    }

    @GetMapping("/new-property")
    public String newProperty(Model model) {
        model.addAttribute("property",new Property());


        return "new-property.html";
    }
    @PostMapping("/processProperty")
    public String processProperty(
            @Valid @ModelAttribute("property") Property newProperty,
            BindingResult result,
            Principal principal) {
        String activeEmail=principal.getName();
        Optional<User> activeUser=userService.getUserByEmail(activeEmail);
        activeUser.ifPresent(newProperty::setOwner);
        propertyService.save(newProperty);
        if (result.hasErrors()) {
            return "new-property.html";
        }
        return "redirect:/my-profile";
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
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(user);

        return "redirect:/login";
    }

}
