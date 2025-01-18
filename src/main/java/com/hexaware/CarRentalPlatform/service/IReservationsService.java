package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.ReservationsDTO;

public interface IReservationsService {
	
	public ReservationsDTO createReservations(Long userId,Long vehicleId,ReservationsDTO r)throws ResourceNotFoundException;
	
	List<Reservations> findByUserUserId(Long userId)throws ResourceNotFoundException;

    List<Reservations> findByPickupDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Reservations> findByDropOffDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<Reservations> findByReservationId(Long reservationId)throws ResourceNotFoundException;
    
    public void updateReservations(Long reservationId,Vehicles vehicleId, LocalDateTime pickupDateTime, LocalDateTime dropOffDateTime, double totalCost,LocalDateTime updatedAt)throws ResourceNotFoundException;
    
    public List<Reservations> getAllReservations();
}
