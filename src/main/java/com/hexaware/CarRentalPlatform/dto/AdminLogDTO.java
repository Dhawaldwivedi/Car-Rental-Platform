package com.hexaware.CarRentalPlatform.dto;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.hexaware.CarRentalPlatform.Models.Users;

public class AdminLogDTO {
	
	private Users user;
    private String actionDescription;
    @CreationTimestamp
    private LocalDateTime actionDate;
	public AdminLogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminLogDTO(Users user, String actionDescription, LocalDateTime actionDate) {
		super();
		this.user = user;
		this.actionDescription = actionDescription;
		this.actionDate = actionDate;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getActionDescription() {
		return actionDescription;
	}
	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}
	public LocalDateTime getActionDate() {
		return actionDate;
	}
	public void setActionDate(LocalDateTime actionDate) {
		this.actionDate = actionDate;
	}
	@Override
	public String toString() {
		return "AdminLogDTO [user=" + user + ", actionDescription=" + actionDescription + ", actionDate=" + actionDate
				+ "]";
	} 

}
