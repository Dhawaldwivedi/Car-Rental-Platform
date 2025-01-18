package com.hexaware.CarRentalPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.UsersDTO;
import com.hexaware.CarRentalPlatform.service.UsersServiceImpl;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/userapi/v1/")
@CrossOrigin("*")
//@CrossOrigin("http://localhost:3000")
public class UsersController {
	
	private UsersServiceImpl usersServiceImpl;

	@Autowired
	public UsersController(UsersServiceImpl usersServiceImpl) {
		super();
		this.usersServiceImpl = usersServiceImpl;
	}
	
	@PostMapping("/createuser")
	ResponseEntity<UsersDTO>createUser(@Valid @RequestBody UsersDTO u)
	{
		return ResponseEntity.ok(this.usersServiceImpl.createUser(u));
	}
	
	@PutMapping("/updateuser/{id}") 
	ResponseEntity<String>updateUser(@Valid @PathVariable("id")Long userId,@RequestBody UsersDTO usersDTO)throws ResourceNotFoundException
	{
			this.usersServiceImpl.updateUserDetails(userId,usersDTO.getFirstName(),usersDTO.getLastName(), usersDTO.getUsername(),usersDTO.getPassword(),usersDTO.getPhoneNumber(),usersDTO.getUpdatedAt());		
			return ResponseEntity.ok("User details updated successfully");
	}
	
	@GetMapping("/getUserById/{id}")
	ResponseEntity<Optional<Users>> findUserById(@PathVariable("id") long id)throws ResourceNotFoundException {
	    return ResponseEntity.ok(this.usersServiceImpl.getUserById(id));
	}
	
	@GetMapping("/getUserByEmail/{email}")
	ResponseEntity<Optional<Users>> findUserByEmail(@PathVariable("email") String email)throws ResourceNotFoundException {
	    return ResponseEntity.ok(this.usersServiceImpl.getUserByEmail(email));
	}
	
	@DeleteMapping("/deleteuserbyId/{id}")
	public ResponseEntity<String> deleteUserById(@RequestBody @PathVariable("id") long id)throws ResourceNotFoundException
	{
		this.usersServiceImpl.deleteUserById(id);
    	return ResponseEntity.ok("deleted successfully");	
    }
	
	@GetMapping("/getallUsers")
	public ResponseEntity<List<Users>> getAllUsers() {
	    List<Users> users = usersServiceImpl.getAllUsers();
	    return ResponseEntity.ok(users);
	}
	
	@GetMapping("/isemailregistered/{email}")
	ResponseEntity<String> isemailregistered(@PathVariable("email")String email)throws ResourceNotFoundException
	{
		boolean emailExists=this.usersServiceImpl.isEmailRegistered(email);
	    return ResponseEntity.ok("Email ID exists");
	}
	

}
