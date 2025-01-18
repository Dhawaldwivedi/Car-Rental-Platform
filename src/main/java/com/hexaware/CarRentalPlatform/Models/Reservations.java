package com.hexaware.CarRentalPlatform.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicles vehicle;

    private LocalDateTime pickupDateTime;
    private LocalDateTime dropOffDateTime;
    
    
    private double totalCost;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    
	public Reservations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservations(Long reservationId, Users user, Vehicles vehicle, LocalDateTime pickupDateTime,
			LocalDateTime dropOffDateTime, double totalCost, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.reservationId = reservationId;
		this.user = user;
		this.vehicle = vehicle;
		this.pickupDateTime = pickupDateTime;
		this.dropOffDateTime = dropOffDateTime;
		this.totalCost = totalCost;
		this.createdAt = createdAt;
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
		return "Reservations [reservationId=" + reservationId + ", user=" + user + ", vehicle=" + vehicle
				+ ", pickupDateTime=" + pickupDateTime + ", dropOffDateTime=" + dropOffDateTime + ",totalCost=" + totalCost + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	

    
}


