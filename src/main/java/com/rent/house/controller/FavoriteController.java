package com.rent.house.controller;

import com.rent.house.dto.BuildingDto;
import com.rent.house.service.FavoriteService;
import com.rent.house.service.impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class FavoriteController {
    public FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @DeleteMapping("/favorites/delete/{id}/{username}")
    public void deleteBuilding(@PathVariable Long id, @PathVariable String username){
        favoriteService.deleteFavorite(id, username);
    }

    @PostMapping("/favorites/add")
    public void addBuilding(@RequestBody Map<String, String> requestBody){
        Long buildingId = Long.parseLong(requestBody.get("buildingId"));
        String username = requestBody.get("username");

        favoriteService.addToFavorites(buildingId, username);
    }


}
