package com.EEY.SincereEstate.service;

import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;

import java.util.List;

public interface PropertyService {
    void deleteById(int id);
    void save(Property property);
    Property findById(int id);
    User getOwner(int id);
    List<Property> findByName(String name);
    List<Property> findByCountry(String country);
    List<Property> findByCountryAndState(String country, String state);
    List<Property> findByCountryAndStateAndCity(String country, String state,String city);
    List<Property> findAll();
    List<Property> findByStatus(String status);
}
