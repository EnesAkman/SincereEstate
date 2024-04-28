package com.EEY.SincereEstate.service;


import com.EEY.SincereEstate.Entity.Property;

import java.util.List;

public interface UserService {

    List<Property> getPropertiesByUser(int id);
}
