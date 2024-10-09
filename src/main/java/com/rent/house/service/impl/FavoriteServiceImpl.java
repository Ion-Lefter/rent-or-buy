package com.rent.house.service.impl;

import com.rent.house.model.Favorite;
import com.rent.house.repository.FavoriteRepository;
import com.rent.house.service.FavoriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private static final Logger logger = LoggerFactory.getLogger(FavoriteServiceImpl.class);

    private final FavoriteRepository favoriteRepository;
    FavoriteServiceImpl(FavoriteRepository favoriteRepository){
        this.favoriteRepository = favoriteRepository;
    }
    @Override
    public List<Favorite> findAllFavoriteBuildings(String username) {
        return favoriteRepository.findAllByUsername(username);
    }

    @Override
    public void deleteFavorite(Long id, String username) {
        favoriteRepository.deleteFromFavorites(id, username);
    }

    @Override
    public void addToFavorites(Long buildingId, String username) {
        favoriteRepository.addBuildingToFavorites(buildingId, username);
    }
}
