package com.rent.house.service;

import com.rent.house.dto.BuildingDto;
import com.rent.house.model.Favorite;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findAllFavoriteBuildings(String username);

    void deleteFavorite(Long id, String username);

    void addToFavorites(Long buildingId, String username);
}
