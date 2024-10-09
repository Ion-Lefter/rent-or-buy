package com.rent.house.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Data
@Table(name = "favorites")
public class Favorite {
    private Long userId;
    private Long buildingId;
    private String username;
    private Date date;

    public Favorite(long userId, long buildingId, String username, Date date) {
        this.userId = userId;
        this.buildingId = buildingId;
        this.username = username;
        this.date = date;
    }


}
