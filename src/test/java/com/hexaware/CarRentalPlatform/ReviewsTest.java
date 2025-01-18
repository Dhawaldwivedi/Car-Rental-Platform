package com.hexaware.CarRentalPlatform;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.CarRentalPlatform.Models.Reviews;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.repository.ReviewsRepository;

@SpringBootTest
public class ReviewsTest {
	
	@Autowired
	private ReviewsRepository reviewsRepository;
	
	@Test
	void saveMethodTest()
	{
		Users user = new Users();
	    user.setUserId(4L);
	    Vehicles vehicle = new Vehicles();
	    vehicle.setVehicleId(2L);
	    Reviews review = new Reviews();
	    review.setUser(user); 
	    review.setVehicle(vehicle); 
	    review.setRating(Reviews.Rating.TWO);
	    review.setReviewText("Amazing vehicle, comfortable!");
	    
	    Reviews savedreview=reviewsRepository.save(review);
		System.out.println(savedreview.getReviewId()+" created "+savedreview.getReviewDate());
	}
	
	@Test
	void updateUsingsaveMethodTest()
	{
		//find entity by id
		Reviews existing=  reviewsRepository.findById(1L).get(); 
		//update
		existing.setRating(Reviews.Rating.ONE);
		//Entitymanager.merge()
		reviewsRepository.save(existing);
	}
	
	@Test
	void findByIdMethodTest()
	{
		//find entity by id
		Reviews existing=  reviewsRepository.findById(1L).get();
	}
	
	@Test
	void findAllTest()
	{
		List<Reviews> productList=reviewsRepository.findAll();
		System.out.println(productList);
	}
	
	@Test
	void deleteByIdTest()
	{
		reviewsRepository.deleteById(1L);
	}

}
