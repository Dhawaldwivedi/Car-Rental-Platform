package com.hexaware.CarRentalPlatform.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Reviews;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.ReviewsDTO;
import com.hexaware.CarRentalPlatform.repository.ReviewsRepository;
import com.hexaware.CarRentalPlatform.repository.UsersRepository;
import com.hexaware.CarRentalPlatform.repository.VehiclesRepository;

@Service
public class ReviewsServiceImpl implements IReviewsService{
	
	@Autowired
	private ReviewsRepository reviewsRepository;
	@Autowired
	private VehiclesRepository vehiclesRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	public ReviewsServiceImpl(ReviewsRepository reviewsRepository, VehiclesRepository vehiclesRepository,
			UsersRepository usersRepository) {
		super();
		this.reviewsRepository = reviewsRepository;
		this.vehiclesRepository = vehiclesRepository;
		this.usersRepository = usersRepository;
	}
	
	@Override
	public ReviewsDTO createReviews(Long userId,Long vehicleId,ReviewsDTO u)throws ResourceNotFoundException {
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
		Reviews review = mapper.map(u, Reviews.class);
		review.setVehicle(vehicle);
		review.setUser(user);
		Reviews savedReviews = this.reviewsRepository.save(review);
		ReviewsDTO ReviewsDTO = mapper.map(savedReviews, ReviewsDTO.class);
		return ReviewsDTO;	
	}
	
	@Override
	public List<Reviews> findByVehicleVehicleId(Long vehicleId) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean exists = reviewsRepository.existsByVehicleId(vehicleId);

        if (!exists) {
            throw new ResourceNotFoundException("Review", "vehicleId", vehicleId);
        }
		return reviewsRepository.findByVehicleId(vehicleId);
	}

	@Override
	public List<Reviews> findByUserUserId(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = reviewsRepository.existsByUserId(userId); 

        if (!exists) {
            throw new ResourceNotFoundException("Review", "userId", userId);
        }
		return reviewsRepository.findByUserId(userId);
	}

	@Override
	public Double findAvgRatingByVehicleId(Long vehicleId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = reviewsRepository.existsByVehicleId(vehicleId);

        if (!exists) {
            throw new ResourceNotFoundException("Rating", "vehicleId", vehicleId);
        }
		return reviewsRepository.findRatingByVehicleId(vehicleId);
	}

	@Override
	public List<Reviews> findByReviewTextContainingIgnoreCase(String text) {
		// TODO Auto-generated method stub
		return reviewsRepository.findByReviewText(text);
	}

	@Override
	public void deleteByUserUserId(Long userId) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean exists = reviewsRepository.existsByUserId(userId); 

        if (!exists) {
            throw new ResourceNotFoundException("Review", "userId", userId);
        }
		this.reviewsRepository.deleteByUserId(userId);
		
	}

	@Override
	public List<Reviews> getAllReviews() {
		// TODO Auto-generated method stub
		return this.reviewsRepository.findAll();
	}

	@Override
	public Optional<Reviews> getReviewById(Long reviewId) throws ResourceNotFoundException {
		boolean exists = reviewsRepository.existsById(reviewId);

        if (!exists) {
            throw new ResourceNotFoundException("Review", "reviewId", reviewId);
        }
		return this.reviewsRepository.findById(reviewId);
	}

}
