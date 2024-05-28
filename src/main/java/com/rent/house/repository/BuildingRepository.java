package com.rent.house.repository;

import com.rent.house.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BuildingRepository extends JpaRepository <Building, Long>{
    List<Building> findAll();
}
