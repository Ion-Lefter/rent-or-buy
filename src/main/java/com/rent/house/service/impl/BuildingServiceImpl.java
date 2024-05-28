package com.rent.house.service.impl;

import com.rent.house.dto.BuildingDto;
import com.rent.house.model.Building;
import com.rent.house.repository.BuildingRepository;
import com.rent.house.service.BuildingService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    private BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository){
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<BuildingDto> findAllBuildings() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream().map((building) -> convertEntityToDto(building)).collect(Collectors.toList());

    }

    @Override
    public BuildingDto findById(Long id) throws Exception {
        Optional<Building> optional = buildingRepository.findById(id);
        if (optional.isPresent()){
            return convertEntityToDto(optional.get());
        }else {
            return null;
        }
    }

    @Override
    public void addBuilding(BuildingDto buildingDto) {
        Building building;
        building = convertDtoToEntity(buildingDto);
        buildingRepository.save(building);
    }

    public void editBuilding(Long id, BuildingDto buildingDto) {
        Optional<Building> optional = buildingRepository.findById(id);
        if(optional.isPresent()){
            Building building = optional.get();
            building.setCountry(buildingDto.getCountry());
            building.setCity(buildingDto.getCity());
            building.setSeller(buildingDto.getSeller());
            building.setType(buildingDto.getType());
            building.setRegion(buildingDto.getRegion());
            building.setStreet_name(buildingDto.getStreet_name());
            building.setStreet_number(buildingDto.getStreet_number());
            building.setFloor(buildingDto.getFloor());
            building.setArea(buildingDto.getArea());
            building.setYear(buildingDto.getYear());
            building.setRooms(buildingDto.getRooms());
            building.setDate(buildingDto.getDate());
            building.setPrice(buildingDto.getPrice());
            building.setImage_url(buildingDto.getImage_url());
            buildingRepository.save(building);
        }
    }

    public void deleteBuilding(Long id){
        buildingRepository.deleteById(id);
    }

    private BuildingDto convertEntityToDto(Building building){
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setId(building.getId());
        buildingDto.setCountry(building.getCountry());
        buildingDto.setCity(building.getCity());
        buildingDto.setSeller(building.getSeller());
        buildingDto.setType(building.getType());
        buildingDto.setRegion(building.getRegion());
        buildingDto.setStreet_name(building.getStreet_name());
        buildingDto.setStreet_number(building.getStreet_number());
        buildingDto.setFloor(building.getFloor());
        buildingDto.setArea(building.getArea());
        buildingDto.setYear(building.getYear());
        buildingDto.setRooms(building.getRooms());
        buildingDto.setDate(building.getDate());
        buildingDto.setPrice(building.getPrice());
        buildingDto.setImage_url(building.getImage_url());
        return buildingDto;
    }

    private Building convertDtoToEntity(BuildingDto buildingDto){
        Building building = new Building();
        //building.setId(buildingDto.getId());
        building.setCountry(buildingDto.getCountry());
        building.setCity(buildingDto.getCity());
        building.setSeller(buildingDto.getSeller());
        building.setType(buildingDto.getType());
        building.setRegion(buildingDto.getRegion());
        building.setStreet_name(buildingDto.getStreet_name());
        building.setStreet_number(buildingDto.getStreet_number());
        building.setFloor(buildingDto.getFloor());
        building.setArea(buildingDto.getArea());
        building.setYear(buildingDto.getYear());
        building.setRooms(buildingDto.getRooms());
        building.setDate(buildingDto.getDate());
        building.setPrice(buildingDto.getPrice());
        building.setImage_url(buildingDto.getImage_url());
        return building;
    }

}
