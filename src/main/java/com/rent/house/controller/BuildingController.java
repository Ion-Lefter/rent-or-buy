package com.rent.house.controller;

import com.rent.house.dto.BuildingDto;

import com.rent.house.model.Building;
import com.rent.house.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api") // Root path for all endpoints in this controller
public class BuildingController {

    public BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService){
        this.buildingService = buildingService;
    }

    @GetMapping("/buildings")
    public List<BuildingDto> all(){return buildingService.findAllBuildings();}

    @GetMapping("/user")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("/buildings/{id}")
    public BuildingDto getById(@PathVariable Long id) throws Exception {
     return buildingService.findById(id);
}

    @PostMapping("/buildings/add")
    public void addBuilding(@RequestBody BuildingDto buildingDto){
        buildingService.addBuilding(buildingDto);
    }

    @PutMapping("/buildings/edit/{id}")
    public void editBuilding(@PathVariable Long id, @RequestBody BuildingDto updatedBuildingDto){
        buildingService.editBuilding(id, updatedBuildingDto);
    }

    @DeleteMapping("buildings/delete/{id}")
    public void deleteBuilding(@PathVariable Long id){
        buildingService.deleteBuilding(id);
    }


    @PostMapping("/myposts")
    public List<BuildingDto> myPosts(@RequestBody Map<String, String> requestBody){
        String username = requestBody.get("username");
        return buildingService.findMyPosts(username);
    }

    @PostMapping("/allposts")
    public List<BuildingDto> allPosts(@RequestBody Map<String, String> requestBody){
        String username = requestBody.get("username");
        return buildingService.findAllPosts(username);
    }

}
