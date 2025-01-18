package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Admin;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.dto.AdminDTO;
import com.hexaware.CarRentalPlatform.dto.JWTAuthResponse;
import com.hexaware.CarRentalPlatform.dto.Login1DTO;
import com.hexaware.CarRentalPlatform.dto.LoginDTO;
import com.hexaware.CarRentalPlatform.dto.UsersDTO;
import com.hexaware.CarRentalPlatform.repository.AdminRepository;
import com.hexaware.CarRentalPlatform.repository.UsersRepository;
import com.hexaware.CarRentalPlatform.secutiry.JwtTokenProvider;

@Service
public class AuthServiceImpl implements IAuthService{
	
	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private UsersRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private UsersRepository usersRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public JWTAuthResponse login(LoginDTO dto) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Users user = userRepository.findByUsername(dto.getUsername());

        UsersDTO userDTO = new UsersDTO(user.getUserId(),user.getUsername(), user.getEmail(),user.getRole(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(),user.getUpdatedAt());

        return new JWTAuthResponse(token, userDTO);
	}


	@Override
	public String userregister(UsersDTO dto) {
	    // Check if username or email already exists
	    if (userRepository.existsByUsername(dto.getUsername())) {
	        throw new RuntimeException("Username already exists");
	    }
	    if (userRepository.existsByEmail(dto.getEmail())) {
	        throw new RuntimeException("Email already exists");
	    }

	    // Create and populate a new User object
	    Users user = new Users();
	    user.setUsername(dto.getUsername());
	    user.setEmail(dto.getEmail());
	    user.setPassword(passwordEncoder.encode(dto.getPassword())); // Securely hash password
	    user.setRole(Users.ROLE_USER); // Default role for user
	    user.setFirstName(dto.getFirstName());
	    user.setLastName(dto.getLastName());
	    user.setPhoneNumber(dto.getPhoneNumber());
	    user.setCreatedAt(LocalDateTime.now()); // Optional, if not handled by @CreationTimestamp

	    // Save the user
	    userRepository.save(user);

	    return "User registered successfully";
	}


	@Override
	public JWTAuthResponse adminlogin(Login1DTO dto) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getAdminName(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Admin admin = adminRepository.findByAdminName(dto.getAdminName());

        AdminDTO adminDTO = new AdminDTO(admin.getAdminId(),admin.getAdminName(), admin.getEmail(),admin.getRole(),admin.getFirstName(),admin.getLastName(),admin.getPhoneNumber(),admin.getUpdatedAt());

        return new JWTAuthResponse(token, adminDTO); 
	}


	@Override
	public String adminregister(AdminDTO dto) {
		if (adminRepository.existsByAdminname(dto.getAdminName())) {
	        throw new RuntimeException("Adminname already exists");
	    }
	    if (adminRepository.existsByEmail(dto.getEmail())) {
	        throw new RuntimeException("Email already exists");
	    }

	    // Create and populate a new admin object
	    Admin admin = new Admin();
	    admin.setAdminName(dto.getAdminName());
	    admin.setEmail(dto.getEmail());
	    admin.setPassword(passwordEncoder.encode(dto.getPassword())); // Securely hash password
	    admin.setRole(Admin.ROLE_USER); // Default role for admin
	    admin.setFirstName(dto.getFirstName());
	    admin.setLastName(dto.getLastName());
	    admin.setPhoneNumber(dto.getPhoneNumber());
	    admin.setCreatedAt(LocalDateTime.now()); // Optional, if not handled by @CreationTimestamp

	    // Save the admin
	    adminRepository.save(admin);

	    return "Admin registered successfully";
	}


}
