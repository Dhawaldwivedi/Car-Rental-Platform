package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Admin;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.AdminDTO;
import com.hexaware.CarRentalPlatform.repository.AdminRepository;

@Service
public class AdminServiceImpl implements IAdminService{
	
	private AdminRepository adminRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public AdminDTO createAdmin(AdminDTO a) {
		if (adminRepository.existsByAdminname(a.getAdminName())) {
	        throw new RuntimeException("Adminname already exists");
	    }
	    if (adminRepository.existsByEmail(a.getEmail())) {
	        throw new RuntimeException("Email already exists");
	    }
		// TODO Auto-generated method stub

		Admin admin = mapper.map(a, Admin.class);
		Admin savedadmin = this.adminRepository.save(admin);
		AdminDTO adminDTO = mapper.map(savedadmin, AdminDTO.class);
		return adminDTO;
	}

	@Override
	public void updateAdminDetails(Long adminId, String firstName, String lastName, String adminName, String password,
			String phoneNumber, LocalDateTime updatedAt) throws ResourceNotFoundException 
	{
		boolean exists = adminRepository.existsById(adminId);

        if (!exists) {
            // Throw exception if the admin is not found
            throw new ResourceNotFoundException("Admin", "adminId", adminId);
        }
		this.adminRepository.updateAdminDetails(adminId, firstName, lastName, adminName, password,phoneNumber,updatedAt);
	}

	@Override
	public void deleteAdminById(Long adminId) throws ResourceNotFoundException {
		boolean exists = adminRepository.existsById(adminId);

        if (!exists) {
            throw new ResourceNotFoundException("Admin", "adminId", adminId);
        }
		this.adminRepository.deleteById(adminId);
		
	}

	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return (List<Admin>) this.adminRepository.findAll();
	}

	@Override
	public Optional<Admin> getAdminByEmail(String email) throws ResourceNotFoundException {
		boolean exists = adminRepository.existsByEmail(email);

        if (!exists) {
            throw new ResourceNotFoundException("Admin", "email", email);
        }
		return adminRepository.findByEmail(email);
	}

	@Override
	public Optional<Admin> getAdminById(Long adminId) throws ResourceNotFoundException {
		boolean exists = adminRepository.existsById(adminId);

        if (!exists) {
            throw new ResourceNotFoundException("Admin", "adminId", adminId);
        }
		return this.adminRepository.findById(adminId);
	}
	
	
	
	

}
