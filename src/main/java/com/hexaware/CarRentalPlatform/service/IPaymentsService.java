package com.hexaware.CarRentalPlatform.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.CarRentalPlatform.Models.Payments;
import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.PaymentsDTO;

public interface IPaymentsService {
	
	public PaymentsDTO createPayments(Long reservationId,Long vehicleId,Long userId,PaymentsDTO p)throws ResourceNotFoundException;
	
	List<Payments> findByReservation_ReservationId(Long reservationId)throws ResourceNotFoundException;

    Optional<Payments> findByPaymentId(Long paymentId)throws ResourceNotFoundException;

    List<Payments> findByUserUserId(Long userId)throws ResourceNotFoundException;
    
    public List<Payments> getAllPayments();
    
	public List<Payments> getPaymentsByReservationAndVehicle(Long reservationId, Long vehicleId)throws ResourceNotFoundException ;

    
}
