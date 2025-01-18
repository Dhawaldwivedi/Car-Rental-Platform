package com.hexaware.CarRentalPlatform.dto;


public class JWTAuthResponse {

	private String accessToken;
	private String tokenType = "Bearer";
	private UsersDTO usersDto;//appending user details and JWT Token in response
	private AdminDTO adminDto;
	public JWTAuthResponse() {	}
	
	

	



	public JWTAuthResponse(String accessToken, AdminDTO adminDto) {
		super();
		this.accessToken = accessToken;
		this.adminDto = adminDto;
	}







	public JWTAuthResponse(String accessToken, UsersDTO usersDto) {
		super();
		this.accessToken = accessToken;
		this.usersDto = usersDto;
	}







	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public UsersDTO getUsersDto() {
		return usersDto;
	}
	public void setUsersDto(UsersDTO usersDto) {
		this.usersDto = usersDto;
	}







	public AdminDTO getAdminDto() {
		return adminDto;
	}







	public void setAdminDto(AdminDTO adminDto) {
		this.adminDto = adminDto;
	}



	
	

}
