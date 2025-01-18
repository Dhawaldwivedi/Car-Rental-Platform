package com.hexaware.CarRentalPlatform.service;

import com.hexaware.CarRentalPlatform.dto.AdminDTO;
import com.hexaware.CarRentalPlatform.dto.JWTAuthResponse;
import com.hexaware.CarRentalPlatform.dto.Login1DTO;
import com.hexaware.CarRentalPlatform.dto.LoginDTO;
import com.hexaware.CarRentalPlatform.dto.UsersDTO;

public interface IAuthService {
	JWTAuthResponse login(LoginDTO dto);
	String userregister(UsersDTO dto);
	
	JWTAuthResponse adminlogin(Login1DTO dto);
	String adminregister(AdminDTO dto);
}  
