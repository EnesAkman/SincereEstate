package com.EEY.SincereEstate.controller;

import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;
import com.EEY.SincereEstate.service.PropertyService;
import com.EEY.SincereEstate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PageController {

    private final UserService userService;
    private final PropertyService propertyService;

    @Autowired
    public PageController(UserService userService, PropertyService propertyService){
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

    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<Property> properties = propertyService.findByName(name);
        model.addAttribute("properties", properties);
        return "homepage";
    }
    @GetMapping("/searchByFilter")
    public String searchByFilter(@RequestParam(name = "country", required = false) String country,
                                   @RequestParam(name = "state", required = false) String state,
                                   @RequestParam(name = "city", required = false) String city,
                                   Model model) {
        List<Property> properties;
        System.out.println("test");
        if (country != null && !country.isEmpty()) {
            if (state != null && !state.isEmpty()) {
                if (city != null && !city.isEmpty()) {
                    // All parameters provided
                    properties = propertyService.findByCountryAndStateAndCity(country, state, city);
                } else {
                    // Country and state provided, city not provided
                    properties = propertyService.findByCountryAndState(country, state);
                }
            } else {
                // Only country provided
                properties = propertyService.findByCountry(country);
            }
        } else {
            // No country provided
            System.out.println("test");
            properties = propertyService.findAll();
        }
        model.addAttribute("properties", properties);
        return "homepage";
    }

    @GetMapping("/admin/users")
    public String findAllUsers(Model model){
        List<User> users=userService.findAll();
        model.addAttribute("users",users);
        return "admin-page-1";
    }

    @GetMapping("/admin/properties")
    public String findAllProperties(Model model){
        List<Property> properties=propertyService.findAll();
        model.addAttribute("properties",properties);
        return "admin-page-2";
    }



}