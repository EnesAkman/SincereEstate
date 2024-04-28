package com.EEY.SincereEstate.service;

import com.EEY.SincereEstate.Entity.Property;
import com.EEY.SincereEstate.Entity.User;
import com.EEY.SincereEstate.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public List<Property> getPropertiesByUser(int id) {
        User user=userRepository.findById(id).orElseThrow();
        return user.getPropertyList();
    }
}
