package com.rent.house.service.impl;

import com.rent.house.dto.BuildingDto;
import com.rent.house.model.Building;
import com.rent.house.model.Favorite;
import com.rent.house.repository.BuildingRepository;
import com.rent.house.repository.FavoriteRepository;
import com.rent.house.security.AppUser;
import com.rent.house.security.AppUserRepository;
import com.rent.house.service.BuildingService;
import com.rent.house.service.FavoriteService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    private static final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);

    private final BuildingRepository buildingRepository;
    private final JdbcTemplate jdbcTemplate;
    private final AppUserRepository appUserRepository;

    //private final FavoriteRepository favoriteRepository;
    private final FavoriteServiceImpl favoriteService;
    private final PostServiceImpl postService;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository,
                               AppUserRepository appUserRepository,
                               JdbcTemplate jdbcTemplate,
                               PostServiceImpl postService,
                               FavoriteServiceImpl favoriteService) {
        this.buildingRepository = buildingRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.appUserRepository = appUserRepository;
        this.postService = postService;
        this.favoriteService = favoriteService;
    }

    @Override
    public List<BuildingDto> findAllBuildings() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream().map((building) -> convertEntityToDto(building)).collect(Collectors.toList());

    }

    //    @Override
//    public BuildingDto findById(Long id) throws Exception {
//        Optional<Building> optional = buildingRepository.findById(id);
//        if (optional.isPresent()){
//            return convertEntityToDto(optional.get());
//        }else {
//            return null;
//        }
//    }
    @Override
    public BuildingDto findById(Long id) throws Exception {
        return buildingRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElseThrow(() -> new Exception("Building not found with id: " + id));
    }

    @Override
    @Transactional
    public void addBuilding(BuildingDto buildingDto) {
        Building building = convertDtoToEntity(buildingDto);
        Building savedBuilding = buildingRepository.save(building);
        buildingDto.setId(savedBuilding.getId());

        AppUser appUser = appUserRepository.findByUsername(buildingDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));


        postService.addDataInPosts(buildingDto, appUser);
        logger.info("Building added: {}", buildingDto.getId());
    }


    public void editBuilding(Long id, BuildingDto buildingDto) {
        Optional<Building> optional = buildingRepository.findById(id);
        if (optional.isPresent()) {
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
            logger.info("Building edited with id: {}", id);
        }
    }


    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
        logger.info("Building deleted with id: {}", id);
    }

    @Override
    public List<BuildingDto> findMyPosts(String username) {

//        List<Building> buildings = buildingRepository.findAllBuildingsByUsername(username);
//        List<BuildingDto> buildingDtoList = buildings.stream().map((building) -> convertEntityToDto(building)).collect(Collectors.toList());
//        buildingDtoList.forEach(buildingDto -> {
//            buildingDto.setUsername(username);
//        });
        List<Building> buildings = buildingRepository.findAllBuildingsByUsername(username);
        List<BuildingDto> buildingDtoList = buildings.stream()
                .map(this::convertEntityToDto) // Use method reference for clarity
                .peek(buildingDto -> buildingDto.setUsername(username)) // Use peek instead of forEach for side effects
                .collect(Collectors.toList());
        return buildingDtoList;

    }

    @Override
    public List<BuildingDto> findAllPosts(String username) {

        List<BuildingDto> buildingDtoList = findAllBuildings();
        List<Favorite> favoriteList = favoriteService.findAllFavoriteBuildings(username);
        markFavorites(buildingDtoList, favoriteList);
        logger.info(favoriteList.toString());
        buildingDtoList.forEach(buildingDto -> buildingDto.setUsername(username));

        return buildingDtoList;
    }

    private BuildingDto convertEntityToDto(Building building) {
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

    private Building convertDtoToEntity(BuildingDto buildingDto) {
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

    public void markFavorites(List<BuildingDto> buildingsDtoList, List<Favorite> favoriteBuildingsList) {
        List<Long> favoriteBuildingsIds = favoriteBuildingsList.stream()
                .map(Favorite::getBuildingId)
                .collect(Collectors.toList()); // Collect favorite IDs into a list

        for (BuildingDto buildingDto : buildingsDtoList) {
            // Check if the current building ID is in the favorites list
            buildingDto.setFavorite(favoriteBuildingsIds.contains(buildingDto.getId())); // Set the favorite status
        }
    }


}
