package com.EEY.SincereEstate.service;

import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;

import java.util.List;

public interface PropertyService {
    Property findById(int id);
    User getOwner(int id);
    List<Property> findAll();
}
