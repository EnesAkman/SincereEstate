package com.EEY.SincereEstate.service;

import com.EEY.SincereEstate.entity.Property;
import com.EEY.SincereEstate.entity.Role;
import com.EEY.SincereEstate.entity.User;
import com.EEY.SincereEstate.repos.RoleRepository;
import com.EEY.SincereEstate.repos.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder=encoder;
    }


    @Override
    public List<Property> getPropertiesByUser(int id) {
        User user=userRepository.findById(id).orElseThrow();
        return null;
    }

    @Transactional
    @Override
    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(1);
        userRepository.save(user);

        Role role=new Role(user.getEmail(),"ROLE_user");
        roleRepository.save(role);

    }


}
