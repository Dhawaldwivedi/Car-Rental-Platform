package com.hexaware.CarRentalPlatform.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;


public class ReservationsDTO {

    private Long reservationId;
    private Users user;
    private Vehicles vehicle;
    private LocalDateTime pickupDateTime;
    private LocalDateTime dropOffDateTime;
    private double totalCost;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

	public ReservationsDTO() {
		this.updatedAt = LocalDateTime.now();
		// TODO Auto-generated constructor stub
	}

	

	public ReservationsDTO(Long reservationId, Users user, Vehicles vehicle, LocalDateTime pickupDateTime,
			LocalDateTime dropOffDateTime, double totalCost, LocalDateTime updatedAt) {
		super();
		this.reservationId = reservationId;
		this.user = user;
		this.vehicle = vehicle;
		this.pickupDateTime = pickupDateTime;
		this.dropOffDateTime = dropOffDateTime;
		this.totalCost = totalCost;
		this.updatedAt = updatedAt;
	}



	
	public Long getReservationId() {
		return reservationId;
	}



	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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

	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}

	public void setPickupDateTime(LocalDateTime pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public LocalDateTime getDropOffDateTime() {
		return dropOffDateTime;
	}

	public void setDropOffDateTime(LocalDateTime dropOffDateTime) {
		this.dropOffDateTime = dropOffDateTime;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	@Override
	public String toString() {
		return "ReservationsDTO [reservationId=" + reservationId + ", user=" + user + ", vehicle=" + vehicle
				+ ", pickupDateTime=" + pickupDateTime + ", dropOffDateTime=" + dropOffDateTime + ", totalCost="
				+ totalCost + ", updatedAt=" + updatedAt + "]";
	}

	
	
    
    
}
