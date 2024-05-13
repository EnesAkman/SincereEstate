package com.EEY.SincereEstate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @Column(name = "property_status")
    private String propertyStatus;

    @Column(name = "price")
    private double price;

    @Column(name = "is_furnished")
    private String isFurnished;

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

    private final static List<String> houseTypes = List.of(
            "Single-Family Home",
            "Duplex",
            "Townhouse",
            "Condominium (Condo)",
            "Apartment",
            "Bungalow",
            "Cottage",
            "Ranch-style",
            "Victorian",
            "Craftsman"
    );

    public List<String> getHouseTypes() {
        return houseTypes;
    }
}
