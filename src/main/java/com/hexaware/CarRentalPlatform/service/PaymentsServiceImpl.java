package com.hexaware.CarRentalPlatform.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Payments;
import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.PaymentsDTO;
import com.hexaware.CarRentalPlatform.repository.PaymentsRepository;
import com.hexaware.CarRentalPlatform.repository.ReservationsRepository;
import com.hexaware.CarRentalPlatform.repository.UsersRepository;
import com.hexaware.CarRentalPlatform.repository.VehiclesRepository;

@Service
public class PaymentsServiceImpl implements IPaymentsService{
	
	private PaymentsRepository paymentsRepository;
	private ReservationsRepository reservationsRepository;
	private VehiclesRepository VehiclesRepository;
	private UsersRepository usersRepository;


	@Autowired
	private ModelMapper mapper;

	

	public PaymentsServiceImpl(PaymentsRepository paymentsRepository, ReservationsRepository reservationsRepository,
			com.hexaware.CarRentalPlatform.repository.VehiclesRepository vehiclesRepository,
			UsersRepository usersRepository) {
		super();
		this.paymentsRepository = paymentsRepository;
		this.reservationsRepository = reservationsRepository;
		VehiclesRepository = vehiclesRepository;
		this.usersRepository = usersRepository;
	}


	@Override
	public PaymentsDTO createPayments(Long reservationId,Long vehicleId,Long userId,PaymentsDTO p)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Reservations> optionalreservation = this.reservationsRepository.findById(reservationId);

	    if (!optionalreservation.isPresent()) {
	        throw new ResourceNotFoundException("Reservation", "reservationId", reservationId);
	    }
	    
	    Optional<Vehicles> optionalvehicle = this.VehiclesRepository.findById(vehicleId);

	    if (!optionalvehicle.isPresent()) {
	        throw new ResourceNotFoundException("Vehicles", "vehicleId", vehicleId);
	    }
	    
	    Optional<Users> optionaluser = this.usersRepository.findById(userId);

	    if (!optionaluser.isPresent()) {
	        throw new ResourceNotFoundException("Users", "userId", userId);
	    }
	    
	    Reservations reservation = optionalreservation.get();
	    Vehicles vehicle = optionalvehicle.get();
	    Users user = optionaluser.get();


		Payments payments = mapper.map(p, Payments.class);
		payments.setReservation(reservation);
		payments.setVehicle(vehicle);
		payments.setUser(user);
		Payments savedpayments = this.paymentsRepository.save(payments);
		PaymentsDTO paymentsDTO = mapper.map(savedpayments, PaymentsDTO.class);
		return paymentsDTO;		
	}


	@Override
	public List<Payments> findByReservation_ReservationId(Long reservationId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = paymentsRepository.existsByReservationId(reservationId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("Payments", "reservationId", reservationId);
        }
		return this.paymentsRepository.findByReservationId(reservationId);
	}

	@Override
	public Optional<Payments> findByPaymentId(Long paymentId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = paymentsRepository.existsById(paymentId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("Payments", "paymentId", paymentId);
        }
		return this.paymentsRepository.findById(paymentId);
	}

	


	@Override
	public List<Payments> findByUserUserId(Long userId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = paymentsRepository.existsByUserId(userId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("Payments", "userId", userId);
        }
		return this.paymentsRepository.findByUserId(userId);
	}

	@Override
	public List<Payments> getAllPayments() {
		// TODO Auto-generated method stub
		return this.paymentsRepository.findAll();
	}
	
	public List<Payments> getPaymentsByReservationAndVehicle(Long reservationId, Long vehicleId)throws ResourceNotFoundException {
	    return paymentsRepository.findByReservationIdAndVehicleId(reservationId, vehicleId);
	}


}
