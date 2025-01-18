package com.hexaware.CarRentalPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.VehiclesDTO;
import com.hexaware.CarRentalPlatform.dto.VehiclesDTO.AvailabilityStatus;
import com.hexaware.CarRentalPlatform.service.VehiclesServiceImpl;

@RestController
@RequestMapping("/vehicleapi/v1/")
@CrossOrigin("*")

public class VehiclesController {
	
	private VehiclesServiceImpl vehiclesServiceImpl;

	@Autowired
	public VehiclesController(VehiclesServiceImpl vehiclesServiceImpl) {
		super();
		this.vehiclesServiceImpl = vehiclesServiceImpl;
	}
	
	@PostMapping("/createvehicle") 
	ResponseEntity<VehiclesDTO>createUser(@RequestBody VehiclesDTO u)
	{
		return ResponseEntity.ok(this.vehiclesServiceImpl.createVehicle(u));
	}
	
	@PutMapping("/updatevehicle/{id}")
	ResponseEntity<String>updateUser(@PathVariable("id")Long vehicleId,@RequestBody VehiclesDTO vehiclesDTO)throws ResourceNotFoundException
	{
		this.vehiclesServiceImpl.updateVehicle(vehicleId,vehiclesDTO.getMake(),vehiclesDTO.getModel(), vehiclesDTO.getImageURL(),vehiclesDTO.getLocation(),
				vehiclesDTO.getYear(),vehiclesDTO.getCapacity(),vehiclesDTO.getPricePerDay(),vehiclesDTO.getAvailabilityStatus(),vehiclesDTO.getUpdatedAt());		
		return ResponseEntity.ok("vehicle details updated successfully");
	}
	
	@PutMapping("/updateVehicleStatus/{id}")
	public ResponseEntity<String> updateVehicleStatusToBooked(@PathVariable("id") Long vehicleId) throws ResourceNotFoundException {
	    this.vehiclesServiceImpl.updateVehicleStatusToBooked(vehicleId);
	    return ResponseEntity.ok("Vehicle status updated to 'Booked'");
	}
	
	@GetMapping("/getVehicldById/{id}")
	ResponseEntity<Optional<Vehicles>> findVehicleById(@PathVariable("id") long id) throws ResourceNotFoundException{
	    return ResponseEntity.ok(this.vehiclesServiceImpl.getVehicleById(id));
	} 
	
	@GetMapping("/getVehicldByStatus/{status}")
	ResponseEntity<List<Vehicles>> findVehicleByStatus(@PathVariable("status") String status){
	    return ResponseEntity.ok(this.vehiclesServiceImpl.getVehiclesByAvailabilityStatus(status));
	}
	
	@GetMapping("/getallVehicles")
	public ResponseEntity<List<Vehicles>> getAllVehicles() {
	    List<Vehicles> vehicles = vehiclesServiceImpl.getAllVehicles();
	    return ResponseEntity.ok(vehicles);
	}
	
	@DeleteMapping("/deletevehiclebyId/{id}")
	public ResponseEntity<String> deleteVehicleById(@RequestBody @PathVariable("id") long id)throws ResourceNotFoundException
	{
		this.vehiclesServiceImpl.deleteVehicleById(id);
    	return ResponseEntity.ok("deleted successfully");	
    }
	
	@GetMapping("/getVehicldByMake/{make}")
	ResponseEntity<List<Vehicles>> findVehicleByMake(@PathVariable("make") String make){
	    return ResponseEntity.ok(this.vehiclesServiceImpl.getVehiclesBymake(make));
	}
	
	@GetMapping("/getVehicldBymodel/{model}")
	ResponseEntity<List<Vehicles>> findVehicleByModel(@PathVariable("model") String model){
	    return ResponseEntity.ok(this.vehiclesServiceImpl.getVehiclesBymodel(model));
	}
	
	@GetMapping("/getVehicldBylocation/{location}")
	ResponseEntity<List<Vehicles>> findVehicleBylocation(@PathVariable("location") String location){
	    return ResponseEntity.ok(this.vehiclesServiceImpl.getVehiclesBylocation(location));
	}
	
	@GetMapping("/getVehicldBycapacity/{capacity}")
	ResponseEntity<List<Vehicles>> findVehicleBycapacity(@PathVariable("capacity") int capacity){
	    return ResponseEntity.ok(this.vehiclesServiceImpl.getVehiclesBycapacity(capacity));
	}
	
	@GetMapping("/getVehicldBypricePerDay/{pricePerDay}")
	ResponseEntity<List<Vehicles>> findVehicleBypricePerDay(@PathVariable("pricePerDay") int pricePerDay){
	    return ResponseEntity.ok(this.vehiclesServiceImpl.getVehiclesBycapacity(pricePerDay));
	}
	
	


}
