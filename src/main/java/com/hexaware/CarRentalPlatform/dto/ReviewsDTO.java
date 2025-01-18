package com.hexaware.CarRentalPlatform.dto;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.Models.Reviews.Rating;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ReviewsDTO {
	
    private Users user;
    private Vehicles vehicle;
    @Enumerated(EnumType.ORDINAL)
    private Rating rating;
    private String reviewText;
    
    public enum Rating {
    	ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);
    	
        private final int value;

        Rating(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

	public ReviewsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewsDTO(Users user, Vehicles vehicle, Rating rating, String reviewText) {
		super();
		this.user = user;
		this.vehicle = vehicle;
		this.rating = rating;
		this.reviewText = reviewText;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Vehicles getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicles vehicle) {
		this.vehicle = vehicle;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	@Override
	public String toString() {
		return "ReviewsDTO [user=" + user + ", vehicle=" + vehicle + ", rating=" + rating + ", reviewText=" + reviewText
				+ "]";
	}

	
    
    


}
