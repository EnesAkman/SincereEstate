package com.EEY.SincereEstate.repos;

import com.EEY.SincereEstate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
