package com.EEY.SincereEstate.Controller;

import com.EEY.SincereEstate.Entity.Property;
import com.EEY.SincereEstate.Entity.User;
import com.EEY.SincereEstate.repos.UserRepository;
import com.EEY.SincereEstate.service.PropertyService;
import com.EEY.SincereEstate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String index() {
        return "index";
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

}
