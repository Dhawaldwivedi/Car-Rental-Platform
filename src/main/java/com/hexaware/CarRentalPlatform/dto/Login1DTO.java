package com.hexaware.CarRentalPlatform.dto;

public class Login1DTO {
	
	private String adminName;
	private String password;
	public Login1DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Login1DTO(String adminName, String password) {
		super();
		this.adminName = adminName;
		this.password = password;
	}
	

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login1DTO [adminName=" + adminName + ", password=" + password + "]";
	}
	
	
	

}
