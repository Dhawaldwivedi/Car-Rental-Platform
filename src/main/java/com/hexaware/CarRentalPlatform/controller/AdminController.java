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

import com.hexaware.CarRentalPlatform.Models.Admin;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.AdminDTO;
import com.hexaware.CarRentalPlatform.service.AdminServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admindataapi/v1/")
@CrossOrigin("*")
public class AdminController {
	
	private AdminServiceImpl adminServiceImpl;

	@Autowired
	public AdminController(AdminServiceImpl adminServiceImpl) {
		super();
		this.adminServiceImpl = adminServiceImpl;
	}
	
	@PostMapping("/createadmin")
	ResponseEntity<AdminDTO>createAdmin(@Valid @RequestBody AdminDTO u)
	{
		return ResponseEntity.ok(this.adminServiceImpl.createAdmin(u));
	}
	
	@PutMapping("/updateadmin/{id}") 
	ResponseEntity<String>updateAdminDetails(@Valid @PathVariable("id")Long adminId,@RequestBody AdminDTO adminDTO)throws ResourceNotFoundException
	{
			this.adminServiceImpl.updateAdminDetails(adminId,adminDTO.getFirstName(),adminDTO.getLastName(), adminDTO.getAdminName(),adminDTO.getPassword(),adminDTO.getPhoneNumber(),adminDTO.getUpdatedAt());		
			return ResponseEntity.ok("Admin details updated successfully");
	}
	
	@GetMapping("/getAdminByEmail/{email}")
	ResponseEntity<Optional<Admin>> getAdminByEmail(@PathVariable("email") String email)throws ResourceNotFoundException {
	    return ResponseEntity.ok(this.adminServiceImpl.getAdminByEmail(email));
	}
	
	@DeleteMapping("/deleteAdminById/{id}")
	public ResponseEntity<String> deleteAdminById(@RequestBody @PathVariable("id") long id)throws ResourceNotFoundException
	{
		this.adminServiceImpl.deleteAdminById(id);
    	return ResponseEntity.ok("deleted successfully");	
    }
	
	@GetMapping("/getAllAdmins")
	public ResponseEntity<List<Admin>> getAllAdmins() {
	    List<Admin> admin = adminServiceImpl.getAllAdmins();
	    return ResponseEntity.ok(admin);
	}
	
	@GetMapping("/getAdminById/{adminId}")
	ResponseEntity<Optional<Admin>> findVehicleById(@PathVariable("adminId") long adminId) throws ResourceNotFoundException{
	    return ResponseEntity.ok(this.adminServiceImpl.getAdminById(adminId));
	} 
	

}
