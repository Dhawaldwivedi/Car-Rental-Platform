package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.VehiclesDTO.AvailabilityStatus;
import com.hexaware.CarRentalPlatform.dto.VehiclesDTO;

public interface IVehiclesService {
	
	public VehiclesDTO createVehicle(VehiclesDTO vehicle);

    public void updateVehicle(Long vehicleId, String make, String model,String imageURL, String location,int year, int capacity, double pricePerDay,
    		AvailabilityStatus availabilityStatus, LocalDateTime updatedAt)throws ResourceNotFoundException;

    Optional<Vehicles> getVehicleById(Long vehicleId)throws ResourceNotFoundException;

    List<Vehicles> getVehiclesByAvailabilityStatus(String AvailabilityStatus);

    List<Vehicles> getAllVehicles();

    void deleteVehicleById(Long vehicleId)throws ResourceNotFoundException;
    
    List<Vehicles> getVehiclesBymake(String make);
    
    List<Vehicles> getVehiclesBymodel(String model);
    
    List<Vehicles> getVehiclesBylocation(String location);
    
    List<Vehicles> getVehiclesBycapacity(int capacity);
    
    List<Vehicles> getVehiclesBypricePerDay(double pricePerDay);

    void updateVehicleStatusToBooked(Long vehicleId) throws ResourceNotFoundException;
    


	

}
