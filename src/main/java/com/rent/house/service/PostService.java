package com.rent.house.service;

import com.rent.house.dto.BuildingDto;
import com.rent.house.security.AppUser;

public interface PostService {
    public void addDataInPosts(BuildingDto buildingDto, AppUser appUser);
}
