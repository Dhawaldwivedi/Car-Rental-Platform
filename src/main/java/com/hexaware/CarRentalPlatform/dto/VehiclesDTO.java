package com.hexaware.CarRentalPlatform.dto;


import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class VehiclesDTO {
	
	private String make;
    private String model;
	private String imageURL;
	private String location;
    private int year;
    private int capacity;
    private double pricePerDay;
  //Available,Reserved,Out Of Service
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
	public VehiclesDTO() {
		this.updatedAt = LocalDateTime.now();
		// TODO Auto-generated constructor stub
	}

	public enum AvailabilityStatus
    {
		dummy("dummy"),
    	Available("Available"),  
    	Reserved("Reserved"), 
    	Unavailable("Unavailable");
    	
    	private final String status;

        AvailabilityStatus(String status) {
            this.status = status;
        } 

        public String getStatus() {
            return status;
        }
    }
	

	

	public VehiclesDTO(String make, String model, String imageURL, String location, int year, int capacity,
			double pricePerDay, AvailabilityStatus availabilityStatus, LocalDateTime updatedAt) {
		super();
		this.make = make;
		this.model = model;
		this.imageURL = imageURL;
		this.location = location;
		this.year = year;
		this.capacity = capacity;
		this.pricePerDay = pricePerDay;
		this.availabilityStatus = availabilityStatus;
		this.updatedAt = LocalDateTime.now();
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "VehiclesDTO [make=" + make + ", model=" + model + ", imageURL=" + imageURL + ", location=" + location
				+ ", year=" + year + ", capacity=" + capacity + ", pricePerDay=" + pricePerDay + ", availabilityStatus="
				+ availabilityStatus + ", updatedAt=" + updatedAt + "]";
	}

	

}
