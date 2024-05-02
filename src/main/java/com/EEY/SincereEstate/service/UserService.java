package com.EEY.SincereEstate.service;


import com.EEY.SincereEstate.entity.Property;

import java.util.List;

public interface UserService {

    List<Property> getPropertiesByUser(int id);
}
