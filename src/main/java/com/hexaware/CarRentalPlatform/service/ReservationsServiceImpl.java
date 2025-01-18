package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.Models.Reviews;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.ReservationsDTO;
import com.hexaware.CarRentalPlatform.repository.ReservationsRepository;
import com.hexaware.CarRentalPlatform.repository.VehiclesRepository;
import com.hexaware.CarRentalPlatform.repository.UsersRepository;

@Service
public class ReservationsServiceImpl implements IReservationsService{
	
	@Autowired
	private ReservationsRepository reservationsRepository;
	@Autowired
	private VehiclesRepository vehiclesRepository;
	@Autowired
	private UsersRepository usersRepository;
	

	@Autowired
	private ModelMapper mapper;

	@Autowired
	public ReservationsServiceImpl(ReservationsRepository reservationsRepository, VehiclesRepository vehiclesRepository,
			UsersRepository usersRepository) {
		super();
		this.reservationsRepository = reservationsRepository;
		this.vehiclesRepository = vehiclesRepository;
		this.usersRepository = usersRepository;
	}
	
	@Override
	public ReservationsDTO createReservations(Long userId,Long vehicleId,ReservationsDTO r) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		Optional<Vehicles> optionalVehicle = this.vehiclesRepository.findById(vehicleId);
		Optional<Users> optionaluser = this.usersRepository.findById(userId);

	    if (!optionalVehicle.isPresent()) {
	        throw new ResourceNotFoundException("Vehicle", "vehicleId", vehicleId);
	    }
	    
	    if (!optionaluser.isPresent()) {
	        throw new ResourceNotFoundException("user", "userId", userId);
	    }

	    Vehicles vehicle = optionalVehicle.get();
	    Users user = optionaluser.get();
		Reservations reservation = mapper.map(r, Reservations.class);
	    reservation.setVehicle(vehicle);
	    reservation.setUser(user);

		Reservations savedreservations = this.reservationsRepository.save(reservation);
		ReservationsDTO reservationsDTO = mapper.map(savedreservations, ReservationsDTO.class);
		return reservationsDTO;	
	}



	@Override
	public List<Reservations> findByUserUserId(Long userId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = reservationsRepository.existsByUserId(userId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("Reservations", "userId", userId);
        }
		return reservationsRepository.findByUserId(userId);
	}


	@Override
	public List<Reservations> findByPickupDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return this.reservationsRepository.findByPickupDate(startDate,endDate);
	}

	@Override
	public List<Reservations> findByDropOffDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return this.reservationsRepository.findByDropOffDate(startDate,endDate);
	}

	@Override
	public Optional<Reservations> findByReservationId(Long reservationId) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean exists = reservationsRepository.existsById(reservationId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("Reservations", "reservationId", reservationId);
        }
		return this.reservationsRepository.findById(reservationId);
	}


	@Override
	public void updateReservations(Long reservationId,Vehicles vehicles, LocalDateTime pickupDateTime, LocalDateTime dropOffDateTime,double totalCost, LocalDateTime updatedAt) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean exists = reservationsRepository.existsById(reservationId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("Reservations", "reservationId", reservationId);
        }
		Long vehicleId = vehicles.getVehicleId();
		this.reservationsRepository.updatereservationDetails(reservationId,vehicleId, pickupDateTime, dropOffDateTime,totalCost, updatedAt);
		
	}
	
	@Override
	public List<Reservations> getAllReservations() {
		// TODO Auto-generated method stub
		return this.reservationsRepository.findAll();
	}

	

}
