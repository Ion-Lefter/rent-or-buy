package com.rent.house.repository;

import com.rent.house.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BuildingRepository extends JpaRepository <Building, Long>{
    List<Building> findAll();
 @Query(value = "SELECT b.* FROM building b " +
        "JOIN posts p ON b.id = p.building_id " +
        "JOIN users u ON p.user_id = u.id " +
        "WHERE u.username = :username", nativeQuery = true)
    List<Building> findAllBuildingsByUsername(@Param("username") String username);

}
