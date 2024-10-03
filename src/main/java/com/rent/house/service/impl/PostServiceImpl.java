package com.rent.house.service.impl;

import com.rent.house.dto.BuildingDto;
import com.rent.house.security.AppUser;
import com.rent.house.service.PostService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

JdbcTemplate jdbcTemplate;

    public PostServiceImpl(JdbcTemplate jdbcTemplate){
        //this.postRepository = postRepository;
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public void addDataInPosts(BuildingDto buildingDto, AppUser appUser) {
        String sql = "INSERT INTO posts (user_id, building_id, username, date, last_updated) VALUES (?, ?, ?, ?, ?)";
        System.out.println(new Date());
        jdbcTemplate.update(sql,
                appUser.getId(),
                buildingDto.getId(),
                appUser.getUsername(),
                new Date(buildingDto.getDate()),
                new Date(buildingDto.getDate())
        );
    }
}
