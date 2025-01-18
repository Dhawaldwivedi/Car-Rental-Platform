package com.hexaware.CarRentalPlatform.dto;

import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PaymentsDTO {
	
    private Long paymentId;

	private Reservations reservation;
	
    private Vehicles vehicle;
    
    private Users user;


	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;    
    private double amount;
    
    public enum PaymentMethod {
    	CASH,              
        CREDIT_CARD,        
        DEBIT_CARD,         
        NET_BANKING,       
        UPI               
    }
    
    
	public PaymentsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	





	










	public PaymentsDTO(Long paymentId, Reservations reservation, Vehicles vehicle, Users user,
			PaymentMethod paymentMethod, double amount) {
		super();
		this.paymentId = paymentId;
		this.reservation = reservation;
		this.vehicle = vehicle;
		this.user = user;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
	}






















	public Reservations getReservation() {
		return reservation;
	}


	public void setReservation(Reservations reservation) {
		this.reservation = reservation;
	}

	

	public Vehicles getVehicle() {
		return vehicle;
	}




	public void setVehicle(Vehicles vehicle) {
		this.vehicle = vehicle;
	}



	
	public Users getUser() {
		return user;
	}






















	public void setUser(Users user) {
		this.user = user;
	}






















	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	


	public Long getPaymentId() {
		return paymentId;
	}





	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}






















	@Override
	public String toString() {
		return "PaymentsDTO [paymentId=" + paymentId + ", reservation=" + reservation + ", vehicle=" + vehicle
				+ ", user=" + user + ", paymentMethod=" + paymentMethod + ", amount=" + amount + "]";
	}











	




	

	
    

}
