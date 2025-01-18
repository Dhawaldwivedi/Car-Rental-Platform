package com.hexaware.CarRentalPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CarRentalPlatform.Models.Reviews;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.ReviewsDTO;
import com.hexaware.CarRentalPlatform.service.ReviewsServiceImpl;

@RestController
@RequestMapping("/reviewsapi/v1/")
@CrossOrigin("*")

public class ReviewsController {
	
	private ReviewsServiceImpl reviewsServiceImpl;

	@Autowired
	public ReviewsController(ReviewsServiceImpl reviewsServiceImpl) {
		super();
		this.reviewsServiceImpl = reviewsServiceImpl;
	}
	
	@PostMapping("/createreview/{userId}/{vehicleId}")
	ResponseEntity<ReviewsDTO>createReviews(@PathVariable("userId") Long userId,@PathVariable("vehicleId") Long vehicleId,@RequestBody ReviewsDTO r)throws ResourceNotFoundException 
	{
		return ResponseEntity.ok(this.reviewsServiceImpl.createReviews(userId,vehicleId,r));
	}
	
	@GetMapping("/getReviewsByvehicleId/{id}")
	ResponseEntity<List<Reviews>> findReviewsByVehicleId(@PathVariable("id") long id) throws ResourceNotFoundException
	{
	    return ResponseEntity.ok(this.reviewsServiceImpl.findByVehicleVehicleId(id));
	}
	
	@GetMapping("/getReviewsByuserId/{id}")
	ResponseEntity<List<Reviews>> findReviewsByUserId(@PathVariable("id") long id) throws ResourceNotFoundException
	{
	    return ResponseEntity.ok(this.reviewsServiceImpl.findByUserUserId(id));
	}
	
	@GetMapping("/getAvgRatingsByvehicleId/{id}") 
	ResponseEntity<Double> findRatingByVehicleId(@PathVariable("id") long id) throws ResourceNotFoundException
	{
	    return ResponseEntity.ok(this.reviewsServiceImpl.findAvgRatingByVehicleId(id));
	}
	
	@GetMapping("/getReviewsByreviewtext/{text}")
	ResponseEntity<List<Reviews>> findReviewsByReviewtext(@PathVariable("text") String text) 
	{
	    return ResponseEntity.ok(this.reviewsServiceImpl.findByReviewTextContainingIgnoreCase(text));
	}
	
	@DeleteMapping("/deleteByUserUserId/{id}")
	public ResponseEntity<String> deleteUserById(@RequestBody @PathVariable("id") long id)throws ResourceNotFoundException
	{
		this.reviewsServiceImpl.deleteByUserUserId(id);
    	return ResponseEntity.ok("deleted successfully");	
    }
	
	@GetMapping("/getallreviews")
	public ResponseEntity<List<Reviews>> getallreviews() {
	    List<Reviews> reviews = reviewsServiceImpl.getAllReviews();
	    return ResponseEntity.ok(reviews);
	}
	
	
	@GetMapping("/getReviewById/{id}")
	ResponseEntity<Optional<Reviews>> getReviewById(@PathVariable("id") long id) throws ResourceNotFoundException{
	    return ResponseEntity.ok(this.reviewsServiceImpl.getReviewById(id));
	} 

}
