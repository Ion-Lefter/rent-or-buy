package com.rent.house.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String seller;
    private String type;
    private String region;
    private String street_name;
    private int street_number;
    private int floor;
    private int area;
    private int year;
    private int rooms;
    private String date;
    private int price;
    private String image_url;
}
