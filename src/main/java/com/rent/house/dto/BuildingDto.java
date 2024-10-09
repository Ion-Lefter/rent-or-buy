package com.rent.house.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {
    private Long id;
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String seller;
    @NotEmpty
    private String type;
    @NotEmpty
    private String region;
    @NotEmpty
    private String street_name;
    @NotEmpty
    private int street_number;
    @NotEmpty
    private int floor;
    @NotEmpty
    private int area;
    @NotEmpty
    private int year;
    @NotEmpty
    private int rooms;
    @NotEmpty
    private String date;
    @NotEmpty
    private int price;
    @NotEmpty
    private String image_url;
    private String username;
    private boolean favorite;
}
