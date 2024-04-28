package com.EEY.SincereEstate.service;

import com.EEY.SincereEstate.Entity.Property;
import com.EEY.SincereEstate.Entity.User;

public interface PropertyService {
    Property findById(int id);
    User getOwner(int id);
}
