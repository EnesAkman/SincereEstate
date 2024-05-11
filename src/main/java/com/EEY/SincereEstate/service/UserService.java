package com.EEY.SincereEstate.service;


import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Property> getPropertiesByUser(int id);
    void register(User user);
    Optional<User> getUserByEmail(String email);
}
