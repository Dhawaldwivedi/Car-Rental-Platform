package com.hexaware.CarRentalPlatform.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.Models.Reviews;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.ReservationsDTO;
import com.hexaware.CarRentalPlatform.service.ReservationsServiceImpl;

@RestController
@RequestMapping("/reservationapi/v1/")
@CrossOrigin("*")

public class ReservationsController {
	
	private ReservationsServiceImpl reservationsServiceImpl;

	@Autowired
	public ReservationsController(ReservationsServiceImpl reservationsServiceImpl) {
		super();
		this.reservationsServiceImpl = reservationsServiceImpl;
	}
	
	@PostMapping("/createreservations/{userId}/{vehicleId}")
	ResponseEntity<ReservationsDTO>createUser(@PathVariable("userId") Long userId,@PathVariable("vehicleId") Long vehicleId, @RequestBody ReservationsDTO r)throws ResourceNotFoundException 
	{
		return ResponseEntity.ok(this.reservationsServiceImpl.createReservations(userId,vehicleId,r));
	}
	
	@GetMapping("/getReservationsByuserId/{id}")
	ResponseEntity<List<Reservations>> findReservationsByUserId(@PathVariable("id") long id)throws ResourceNotFoundException 
	{
	    return ResponseEntity.ok(this.reservationsServiceImpl.findByUserUserId(id));
	}
	
	@GetMapping("/findByPickupDateTime/{startDate}/{endDate}")
	ResponseEntity<List<Reservations>> findByPickupDateTime(@PathVariable("startDate")LocalDateTime startDate,@PathVariable("endDate") LocalDateTime endDate) 
	{
	    return ResponseEntity.ok(this.reservationsServiceImpl.findByPickupDateTimeBetween(startDate,endDate));
	}
	
	@GetMapping("/findByDropOffDateTimeBetween/{startDate}/{endDate}")
	ResponseEntity<List<Reservations>> findByDropOffDateTimeBetween(@PathVariable("startDate")LocalDateTime startDate,@PathVariable("endDate") LocalDateTime endDate) 
	{
	    return ResponseEntity.ok(this.reservationsServiceImpl.findByDropOffDateTimeBetween(startDate,endDate));
	}
	
	@GetMapping("/getReservationsByreservationId/{id}")
	ResponseEntity<Optional<Reservations>> findReservationsByReservationId(@PathVariable("id") long id) throws ResourceNotFoundException
	{
	    return ResponseEntity.ok(this.reservationsServiceImpl.findByReservationId(id));
	}	
	
	@PutMapping("/updatereservation/{id}")
	ResponseEntity<String>updateReservation(@PathVariable("id")Long reservationId,@RequestBody ReservationsDTO reservationsDTO)throws ResourceNotFoundException
	{
		this.reservationsServiceImpl.updateReservations(reservationId,reservationsDTO.getVehicle(),reservationsDTO.getPickupDateTime(),reservationsDTO.getDropOffDateTime(),reservationsDTO.getTotalCost(),reservationsDTO.getUpdatedAt());		
		return ResponseEntity.ok("vehicle details updated successfully");
	}

	@GetMapping("/getallreservations")
	public ResponseEntity<List<Reservations>> getallreservations() {
	    List<Reservations> reservations = reservationsServiceImpl.getAllReservations();
	    return ResponseEntity.ok(reservations);
	}

}
