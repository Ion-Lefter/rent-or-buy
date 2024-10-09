package com.rent.house.repository;

import com.rent.house.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface FavoriteRepository{
     List<Favorite> findAllByUsername(@Param("username") String username);

     void deleteFromFavorites(@Param("id") Long id, @Param("username") String username);

     void addBuildingToFavorites(@Param("id") Long id, @Param("username") String username);
}
