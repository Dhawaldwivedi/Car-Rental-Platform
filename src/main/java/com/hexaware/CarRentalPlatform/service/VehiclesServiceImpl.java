package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.VehiclesDTO.AvailabilityStatus;
import com.hexaware.CarRentalPlatform.dto.VehiclesDTO;
import com.hexaware.CarRentalPlatform.repository.VehiclesRepository;

@Service
public class VehiclesServiceImpl implements IVehiclesService{

	private VehiclesRepository vehiclesRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	public VehiclesServiceImpl(VehiclesRepository vehiclesRepository) {
		super();
		this.vehiclesRepository = vehiclesRepository;
	}

	@Override
	public VehiclesDTO createVehicle(VehiclesDTO vehicle) {
		Vehicles Vehicle = mapper.map(vehicle, Vehicles.class);
		Vehicles savedVehicle = this.vehiclesRepository.save(Vehicle);
		VehiclesDTO vehiclesDTO = mapper.map(savedVehicle, VehiclesDTO.class);
		return vehiclesDTO;
	}

	@Override
	public void updateVehicle(Long vehicleId, String make, String model,String imageURL,String location, int year, int capacity, double pricePerDay,
			AvailabilityStatus availabilityStatus, LocalDateTime updatedAt)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = vehiclesRepository.existsById(vehicleId);

        if (!exists) {
            throw new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId);
        }
		this.vehiclesRepository.updateVehicleDetails(vehicleId, make, model,imageURL, location,year, capacity, pricePerDay,availabilityStatus, updatedAt);
	}

	@Override
	public Optional<Vehicles> getVehicleById(Long vehicleId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = vehiclesRepository.existsById(vehicleId);

        if (!exists) {
            throw new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId);
        }
		return this.vehiclesRepository.findById(vehicleId);
	}

	@Override
	public List<Vehicles> getVehiclesByAvailabilityStatus(String AvailabilityStatus){
		// TODO Auto-generated method stub
		return this.vehiclesRepository.findByAvailabilityStatus(AvailabilityStatus);
	}

	@Override
	public List<Vehicles> getAllVehicles() {
		// TODO Auto-generated method stub
		return this.vehiclesRepository.findAll();
	}

	@Override
	public void deleteVehicleById(Long vehicleId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = vehiclesRepository.existsById(vehicleId);

        if (!exists) {
            throw new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId);
        }
		this.vehiclesRepository.deleteById(vehicleId);
	}

	@Override
	public List<Vehicles> getVehiclesBymake(String make) {
		// TODO Auto-generated method stub
		return this.vehiclesRepository.findBymake(make);
	}

	@Override
	public List<Vehicles> getVehiclesBymodel(String model) {
		// TODO Auto-generated method stub
		return this.vehiclesRepository.findBymodel(model);
	}

	@Override
	public List<Vehicles> getVehiclesBylocation(String location) {
		// TODO Auto-generated method stub
		return this.vehiclesRepository.findBylocation(location);
	}

	@Override
	public List<Vehicles> getVehiclesBycapacity(int capacity) {
		// TODO Auto-generated method stub
		return this.vehiclesRepository.findBycapacity(capacity);
	}

	@Override
	public List<Vehicles> getVehiclesBypricePerDay(double pricePerDay) {
		// TODO Auto-generated method stub
		return this.vehiclesRepository.findBypricePerDay(pricePerDay);
	}
	
	@Override
	public void updateVehicleStatusToBooked(Long vehicleId) throws ResourceNotFoundException {
	    // Check if the vehicle exists
	    boolean exists = vehiclesRepository.existsById(vehicleId);

	    if (!exists) {
	        throw new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId);
	    }

	    // Update the availability status to "Booked"
	    this.vehiclesRepository.updateVehicleStatusToReserved(vehicleId); 
	}


}
