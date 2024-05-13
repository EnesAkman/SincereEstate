package com.EEY.SincereEstate.repos;

import com.EEY.SincereEstate.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findByNameContaining(String name);

}
