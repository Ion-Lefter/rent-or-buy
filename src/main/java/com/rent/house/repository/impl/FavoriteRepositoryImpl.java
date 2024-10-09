package com.rent.house.repository.impl;

import com.rent.house.model.Favorite;
import com.rent.house.repository.FavoriteRepository;
import com.rent.house.security.AppUser;
import com.rent.house.security.AppUserRepository;
import com.rent.house.service.impl.BuildingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository {

    private static final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;

    private final AppUserRepository appUserRepository;

    @Autowired
    public FavoriteRepositoryImpl(JdbcTemplate jdbcTemplate, AppUserRepository appUserRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.appUserRepository = appUserRepository;
    }
    @Override
    @Query(value = "SELECT user_id, building_id, username, date FROM favorites f where f.username =: username", nativeQuery = true)
    public List<Favorite> findAllByUsername(String username) {
        String sql = "SELECT user_id AS userId, building_id AS buildingId, username AS username, date AS date FROM favorites";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Favorite(rs.getLong("userId"), rs.getLong("buildingId"), rs.getString("username"), rs.getDate("date"))
        );
    }

    @Override
    public void deleteFromFavorites(Long id, String username) {
        String sql = "DELETE FROM favorites WHERE building_id = ? AND username = ?;";
        try {
            jdbcTemplate.update(sql, id, username);
        }catch (Exception e){
            logger.info("Exception on Inserting data");
        }
    }

    @Override
    public void addBuildingToFavorites(Long id, String username) {

        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Long userId = Long.valueOf(appUser.getId());

        String sql = "INSERT INTO favorites (user_id, building_id, username, date)\n" +
                "VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, userId, id, username, new Date());
        }catch (Exception e){
            logger.info("Exception on Inserting data");
        }
 }


}
