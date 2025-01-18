package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.UsersDTO;

public interface IUsersService {
    
	public UsersDTO createUser(UsersDTO u);

	public void updateUserDetails(Long userId, String firstName, String lastName, String username,String password, String phoneNumber,LocalDateTime updatedAt)throws ResourceNotFoundException;
   
	Optional<Users> getUserById(Long userId)throws ResourceNotFoundException;

    Optional<Users> getUserByEmail(String email)throws ResourceNotFoundException;

    public void deleteUserById(Long userId)throws ResourceNotFoundException;

    List<Users> getAllUsers();

    boolean isEmailRegistered(String email)throws ResourceNotFoundException;

	

}
