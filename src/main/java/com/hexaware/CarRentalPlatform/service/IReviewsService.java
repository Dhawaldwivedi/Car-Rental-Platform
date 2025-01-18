package com.hexaware.CarRentalPlatform.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.CarRentalPlatform.Models.Reviews;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.ReviewsDTO;

public interface IReviewsService {
	
	public ReviewsDTO createReviews(Long userId,Long vehicleId,ReviewsDTO u)throws ResourceNotFoundException;
	
	List<Reviews> findByVehicleVehicleId(Long vehicleId)throws ResourceNotFoundException;

    List<Reviews> findByUserUserId(Long userId)throws ResourceNotFoundException;

    Double findAvgRatingByVehicleId(Long vehicleId)throws ResourceNotFoundException;

    List<Reviews> findByReviewTextContainingIgnoreCase(String text);

    void deleteByUserUserId(Long userId)throws ResourceNotFoundException;
    
    List<Reviews> getAllReviews();
    
    Optional<Reviews> getReviewById(Long reviewId)throws ResourceNotFoundException;


}
