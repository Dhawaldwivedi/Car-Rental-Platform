package com.hexaware.CarRentalPlatform.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_log")
public class AdminLog {
	   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    private String actionDescription;
    @CreationTimestamp
    private LocalDateTime actionDate;
	public AdminLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminLog(Long actionId, Users user, String actionDescription, LocalDateTime actionDate) {
		super();
		this.actionId = actionId;
		this.user = user;
		this.actionDescription = actionDescription;
		this.actionDate = actionDate;
	}
	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
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
		return "AdminLog [actionId=" + actionId + ", user=" + user + ", actionDescription=" + actionDescription
				+ ", actionDate=" + actionDate + "]";
	}
    
    
	
 
}

