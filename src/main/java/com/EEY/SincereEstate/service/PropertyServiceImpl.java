package com.EEY.SincereEstate.service;

import com.EEY.SincereEstate.Entity.Property;
import com.EEY.SincereEstate.Entity.User;
import com.EEY.SincereEstate.repos.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public User getOwner(int id) {

        Property property = propertyRepository.findById(id).orElseThrow();
        return property.getOwner();
    }

    @Override
    public Property findById(int id) {
        return propertyRepository.findById(id).orElseThrow();
    }
}
