package com.EEY.SincereEstate.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull(message = "First name is required!")
    @Size(min = 1, message ="First name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name is required!")
    @Size(min = 1, message ="Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email is required!")
    @Size(min = 1, message ="Email is required")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "Password is required!")
    @Size(min = 1, message ="Password is required")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Phone is required!")
    @Size(min = 1, message ="Phone is required")
    @Column(name = "phone")
    private String phone;

    @Column(name = "enabled")
    private int enabled;

    @OneToMany(mappedBy = "owner")
    private List<Property> propertyList;


}
