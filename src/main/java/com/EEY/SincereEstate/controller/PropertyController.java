package com.EEY.SincereEstate.controller;


import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;
import com.EEY.SincereEstate.service.PropertyService;
import com.EEY.SincereEstate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class PropertyController {

    private final PropertyService propertyService;

    private final UserService userService;

    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @GetMapping("/new-property")
    public String newProperty(Model model) {
        Property property=new Property();
        model.addAttribute("property",property);
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

    @GetMapping("/property/{propertyId}")
    public String showProperty(@PathVariable int propertyId, Model model, Principal principal) {
        Property property=propertyService.findById(propertyId);

        String activeEmail=principal.getName();
        Optional<User> activeUser=userService.getUserByEmail(activeEmail);
        activeUser.ifPresent(user -> model.addAttribute("activeUser", user));

        model.addAttribute("property", property);
        return "property";
    }

    @GetMapping("/property/{propertyId}/edit")
    public String showEditProperty(@PathVariable int propertyId, Model model) {
        model.addAttribute("property", propertyService.findById(propertyId));
        return "update-property";
    }

    @PostMapping("/property/edit")
    public String editProperty( @Valid @ModelAttribute Property property,
                                BindingResult result,
                                Principal principal){
        String activeEmail=principal.getName();
        Optional<User> activeUser=userService.getUserByEmail(activeEmail);
        activeUser.ifPresent(property::setOwner);
        propertyService.save(property);
        if (result.hasErrors()) {
            return "update-property";
        }
        return "redirect:/my-profile";
    }

    @PostMapping("/property/delete/{propertyId}")
    public String deleteProperty(@PathVariable int propertyId){
        propertyService.deleteById(propertyId);
        return "redirect:/my-profile";
    }

}
