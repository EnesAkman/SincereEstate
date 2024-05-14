package com.EEY.SincereEstate.service;

import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.User;
import com.EEY.SincereEstate.repos.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Property> findByName(String name) {
        return propertyRepository.findByNameContaining(name);
    }


    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> findByStatus(String status) {
        return propertyRepository.findByPropertyStatus(status);
    }

    @Override
    public List<Property> findByCountry(String country) {
        return propertyRepository.findByCountry(country);
    }

    @Override
    public List<Property> findByCountryAndState(String country, String state) {
        return propertyRepository.findByCountryAndState(country, state);
    }

    @Override
    public List<Property> findByCountryAndStateAndCity(String country, String state,String city) {
        return propertyRepository.findByCountryAndStateAndCity(country,state,city);
    }


    @Override
    public void deleteById(int id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public void save(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public Property findById(int id) {
        return propertyRepository.findById(id).orElseThrow();

    }

}
