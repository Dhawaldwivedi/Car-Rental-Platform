package com.hexaware.CarRentalPlatform.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicles vehicle;

    @Enumerated(EnumType.ORDINAL)
    private Rating rating;
    private String reviewText;
    @CreationTimestamp
    private LocalDateTime reviewDate;
    
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
    
    
    
	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reviews(Long reviewId, Users user, Vehicles vehicle, Rating rating, String reviewText,
			LocalDateTime reviewDate) {
		super();
		this.reviewId = reviewId;
		this.user = user;
		this.vehicle = vehicle;
		this.rating = rating;
		this.reviewText = reviewText;
		this.reviewDate = reviewDate;
	}
	
	
	public Reviews(Users user, Vehicles vehicle, Rating rating, String reviewText) {
		super();
		this.user = user;
		this.vehicle = vehicle;
		this.rating = rating;
		this.reviewText = reviewText;
	}
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
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
	public LocalDateTime getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}
	@Override
	public String toString() {
		return "Reviews [reviewId=" + reviewId + ", user=" + user + ", vehicle=" + vehicle + ", rating=" + rating
				+ ", reviewText=" + reviewText + ", reviewDate=" + reviewDate + "]";
	}

	
    
}

