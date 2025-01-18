package com.hexaware.CarRentalPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CarRentalPlatform.Models.Payments;
import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.PaymentsDTO;
import com.hexaware.CarRentalPlatform.service.PaymentsServiceImpl;

@RestController
@RequestMapping("/paymentsapi/v1/")
@CrossOrigin("*")

public class PaymentsController {
	
	private PaymentsServiceImpl paymentsServiceImpl;

	@Autowired
	public PaymentsController(PaymentsServiceImpl paymentsServiceImpl) {
		super();
		this.paymentsServiceImpl = paymentsServiceImpl;
	}
	
	@PostMapping("/createpayments/{reservationId}/{vehicleId}/{userId}")
	ResponseEntity<PaymentsDTO>createUser(@PathVariable("reservationId") Long reservationId,@PathVariable("vehicleId") Long vehicleId,@PathVariable("userId") Long userId,@RequestBody PaymentsDTO r)throws ResourceNotFoundException
	{
		return ResponseEntity.ok(this.paymentsServiceImpl.createPayments(reservationId,vehicleId,userId,r));
	}
	
	@GetMapping("/findByPaymentsByReservationId/{id}")
	ResponseEntity<List<Payments>> findByPaymentsByReservationId(@PathVariable("id") long id)throws ResourceNotFoundException  
	{
	    return ResponseEntity.ok(this.paymentsServiceImpl.findByReservation_ReservationId(id));
	}
	
	@GetMapping("/findByPaymentId/{id}")
	ResponseEntity<Optional<Payments>> findByPaymentId(@PathVariable("id") long id) throws ResourceNotFoundException 
	{
	    return ResponseEntity.ok(this.paymentsServiceImpl.findByPaymentId(id));
	}
	
	
	@GetMapping("/findpaymentsByUserId/{id}")
	ResponseEntity<List<Payments>> findpaymentsByUserId(@PathVariable("id") long id) throws ResourceNotFoundException 
	{
	    return ResponseEntity.ok(this.paymentsServiceImpl.findByUserUserId(id));
	}
	
	@GetMapping("/getallpayments") 
	public ResponseEntity<List<Payments>> getallpayments() {
	    List<Payments> payments = paymentsServiceImpl.getAllPayments();
	    return ResponseEntity.ok(payments);
	}
	
	@GetMapping("/findpaymentsByreservationIdvehicleId/{reservationId}/{vehicleId}")
	ResponseEntity<List<Payments>> findpaymentsByreservationIdvehicleId(@PathVariable("reservationId") long reservationId,@PathVariable("vehicleId") long vehicleId) throws ResourceNotFoundException 
	{
	    return ResponseEntity.ok(this.paymentsServiceImpl.getPaymentsByReservationAndVehicle(reservationId,vehicleId));
	}
	
}
