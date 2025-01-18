package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.UsersDTO;
import com.hexaware.CarRentalPlatform.repository.UsersRepository;

@Service
public class UsersServiceImpl implements IUsersService {

	private UsersRepository usersRepository;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	@Override
	public UsersDTO createUser(UsersDTO u) {
		if (usersRepository.existsByUsername(u.getUsername())) {
	        throw new RuntimeException("Username already exists");
	    }
	    if (usersRepository.existsByEmail(u.getEmail())) {
	        throw new RuntimeException("Email already exists");
	    }
		// TODO Auto-generated method stub

		Users user = mapper.map(u, Users.class);
		Users savedUsers = this.usersRepository.save(user);
		UsersDTO usersDTO = mapper.map(savedUsers, UsersDTO.class);
		return usersDTO;
	}

	@Override
	public void updateUserDetails(Long userId, String firstName, String lastName, String username,String password, String phoneNumber,LocalDateTime updatedAt)throws ResourceNotFoundException
	{
		boolean exists = usersRepository.existsById(userId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("User", "userId", userId);
        }
		this.usersRepository.updateUserDetails(userId, firstName, lastName, username, password,phoneNumber,updatedAt);
	}

	@Override
	public Optional<Users> getUserById(Long userId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = usersRepository.existsById(userId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("User", "userId", userId);
        }
		return usersRepository.findById(userId);
	}

	@Override
	public Optional<Users> getUserByEmail(String email) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean exists = usersRepository.existsByEmail(email);

        if (!exists) {
            throw new ResourceNotFoundException("User", "email", email);
        }
		return usersRepository.findByEmail(email);
	}

	@Override
	public void deleteUserById(Long userId)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = usersRepository.existsById(userId);

        if (!exists) {
            throw new ResourceNotFoundException("User", "userId", userId);
        }
		this.usersRepository.deleteById(userId);
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return (List<Users>) this.usersRepository.findAll();
	}

	@Override
	public boolean isEmailRegistered(String email) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean exists = usersRepository.existsByEmail(email);

        if (!exists) {
            throw new ResourceNotFoundException("User", "email", email);
        }
		return this.usersRepository.getByEmail(email)!=null;
	}

}
