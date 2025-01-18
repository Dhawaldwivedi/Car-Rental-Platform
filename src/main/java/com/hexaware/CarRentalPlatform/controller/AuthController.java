package com.hexaware.CarRentalPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CarRentalPlatform.dto.AdminDTO;
import com.hexaware.CarRentalPlatform.dto.JWTAuthResponse;
import com.hexaware.CarRentalPlatform.dto.Login1DTO;
import com.hexaware.CarRentalPlatform.dto.LoginDTO;
import com.hexaware.CarRentalPlatform.dto.UsersDTO;
import com.hexaware.CarRentalPlatform.service.AuthServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
//@CrossOrigin("http://localhost:3000") 

public class AuthController {
	
	@Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO) {
    	JWTAuthResponse token = authService.login(loginDTO);
        return ResponseEntity.ok(token);
    } 

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UsersDTO userDTO) {
        String response = authService.userregister(userDTO);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/adminlogin")
    public ResponseEntity<JWTAuthResponse> adminlogin(@RequestBody Login1DTO login1DTO) {
    	JWTAuthResponse token = authService.adminlogin(login1DTO);
        return ResponseEntity.ok(token);
    } 

    @PostMapping("/registeradmin")
    public ResponseEntity<String> registeradmin(@Valid @RequestBody AdminDTO adminDTO) {
        String response = authService.adminregister(adminDTO);
        return ResponseEntity.ok(response);
    }

}
