package com.hexaware.CarRentalPlatform.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hexaware.CarRentalPlatform.dto.VehiclesDTO.AvailabilityStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

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

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
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
    
    
    
	public Vehicles() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Vehicles(Long vehicleId, String make, String model, String imageURL, String location, int year, int capacity,
			double pricePerDay, AvailabilityStatus availabilityStatus, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.vehicleId = vehicleId;
		this.make = make;
		this.model = model;
		this.imageURL = imageURL;
		this.location = location;
		this.year = year;
		this.capacity = capacity;
		this.pricePerDay = pricePerDay;
		this.availabilityStatus = availabilityStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public Vehicles(String make, String model, String imageURL, String location, int year, int capacity, double pricePerDay,
			AvailabilityStatus availabilityStatus) {
		super();
		this.make = make;
		this.model = model;
		this.imageURL = imageURL;
		this.location = location;
		this.year = year;
		this.capacity = capacity;
		this.pricePerDay = pricePerDay;
		this.availabilityStatus = availabilityStatus;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	@Override
	public String toString() {
		return "Vehicles [vehicleId=" + vehicleId + ", make=" + make + ", model=" + model + ", imageURL=" + imageURL
				+ ", location=" + location + ", year=" + year + ", capacity=" + capacity + ", pricePerDay="
				+ pricePerDay + ", availabilityStatus=" + availabilityStatus + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	
	
	
}

