package com.EEY.SincereEstate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "property")
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "property_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "price")
    private double price;

    @Column(name = "is_furnished")
    private boolean isFurnished;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @Column(name = "building_age")
    private int buildingAge;

    @Column(name = "square_meters")
    private int squareMeters;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "image_url")
    private String imageUrl;
}
