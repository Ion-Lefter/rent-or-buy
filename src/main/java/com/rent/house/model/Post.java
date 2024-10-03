package com.rent.house.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Data
@Table(name = "posts")
public class Post {

    private Long userId;
    private Long buildingId;

    private Date date;

    private Date lastUpdated;
}
