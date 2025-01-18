package com.hexaware.CarRentalPlatform.customexceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
	
	private LocalDateTime timestamp;
	private String errormessage;
	private String apipath;
	private String errorCode;
	
	
	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ErrorDetails(LocalDateTime timestamp, String errormessage, String apipath, String errorCode) {
		super();
		this.timestamp = timestamp;
		this.errormessage = errormessage;
		this.apipath = apipath;
		this.errorCode = errorCode;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getErrormessage() {
		return errormessage;
	}


	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}


	public String getApipath() {
		return apipath;
	}


	public void setApipath(String apipath) {
		this.apipath = apipath;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", errormessage=" + errormessage + ", apipath=" + apipath
				+ ", errorCode=" + errorCode + "]";
	}


	
	
	

}
