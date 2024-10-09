package com.rent.house.service;

import com.rent.house.dto.BuildingDto;

import java.util.List;

public interface BuildingService {

    public List<BuildingDto> findAllBuildings();

    public BuildingDto findById(Long id) throws Exception;

    public void addBuilding(BuildingDto buildingDto);

    public void editBuilding(Long id, BuildingDto buildingDto);

    public void deleteBuilding(Long id);

    public List<BuildingDto> findMyPosts(String username);

    public List<BuildingDto> findAllPosts(String username);

}
