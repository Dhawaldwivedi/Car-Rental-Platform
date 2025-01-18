package com.hexaware.CarRentalPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.CarRentalPlatform.Models.Reviews;

import jakarta.transaction.Transactional;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    // Native query to find reviews by vehicle ID
    @Query(value = "SELECT * FROM reviews WHERE vehicle_id = :vehicleId", nativeQuery = true)
    List<Reviews> findByVehicleId(@Param("vehicleId") Long vehicleId);
    
    @Query(value = "SELECT * FROM reviews WHERE user_id = :userId", nativeQuery = true)
    List<Reviews> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT AVG(r.rating) FROM Reviews r WHERE r.vehicle.vehicleId = :vehicleId")
    Double findRatingByVehicleId(Long vehicleId);

    @Query("SELECT r FROM Reviews r WHERE LOWER(r.reviewText) LIKE LOWER(CONCAT('%', :text, '%'))")
	List<Reviews> findByReviewText(String text);

    @Modifying
    @Transactional
    @Query("DELETE FROM Reviews r WHERE r.user.userId = :userId")
	void deleteByUserId(Long userId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reviews r WHERE r.vehicle.vehicleId = :vehicleId")
    boolean existsByVehicleId(@Param("vehicleId") Long vehicleId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reviews r WHERE r.user.userId = :userId")
	boolean existsByUserId(Long userId);

}