package com.hexaware.CarRentalPlatform.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminDTO {
	
	public static final String ROLE_USER = "ADMIN";
    private Long adminId;

	
    @NotEmpty(message = "First name cannot be null")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
	private String firstName;
    @NotEmpty(message = "Last name cannot be null")
    @Size(min = 1, max = 20, message = "Last name must be between 1 and 20 characters")
    private String lastName;
    @NotEmpty(message = "Adminname cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,20}$", message = "Adminname must be 3-20 characters long and can include letters, numbers, dots, underscores, and hyphens")
    private String adminName;
    @NotEmpty(message = "Email cannot be null")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotEmpty(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotEmpty(message = "Phone number cannot be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a 10-digit number")
    private String phoneNumber;
    private String role;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
	public AdminDTO() {
		this.updatedAt = LocalDateTime.now();
		// TODO Auto-generated constructor stub
	}
	
	public AdminDTO(Long adminId, String firstName, String lastName, String adminName, String email, String password,
			String phoneNumber, String role, LocalDateTime updatedAt) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adminName = adminName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.updatedAt = LocalDateTime.now();
	}
	
	public AdminDTO(Long adminId,String firstName, String lastName, String adminName, String email,
			String phoneNumber, String role,LocalDateTime updatedAt) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adminName = adminName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.updatedAt = LocalDateTime.now();
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "AdminDTO [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", adminName="
				+ adminName + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber + ", role="
				+ role + ", updatedAt=" + updatedAt + "]";
	}
	
	
    

}
