package com.EEY.SincereEstate.repos;

import com.EEY.SincereEstate.entity.Role;
import com.EEY.SincereEstate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, User> {

}
