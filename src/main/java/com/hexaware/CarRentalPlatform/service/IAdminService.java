package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.hexaware.CarRentalPlatform.Models.Admin;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.AdminDTO;

public interface IAdminService {
	
	public AdminDTO createAdmin(AdminDTO u);
	
	public void updateAdminDetails(Long adminId, String firstName, String lastName, String adminName,String password, String phoneNumber,LocalDateTime updatedAt)throws ResourceNotFoundException;

	public void deleteAdminById(Long adminId)throws ResourceNotFoundException;

	List<Admin> getAllAdmins();
	
    Optional<Admin> getAdminByEmail(String email)throws ResourceNotFoundException;
    
    Optional<Admin> getAdminById(Long adminId)throws ResourceNotFoundException;


}
