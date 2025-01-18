package com.hexaware.CarRentalPlatform.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservations reservation;
    
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicles vehicle;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @CreationTimestamp
    private LocalDateTime paymentDate;
    private double amount;
    
    
    public enum PaymentMethod {
    	CASH,              
        CREDIT_CARD,        
        DEBIT_CARD,         
        NET_BANKING,       
        UPI               
    }
    
    
	public Payments() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	





	public Payments(Long paymentId, Reservations reservation, Vehicles vehicle, Users user, PaymentMethod paymentMethod,
			LocalDateTime paymentDate, double amount) {
		super();
		this.paymentId = paymentId;
		this.reservation = reservation;
		this.vehicle = vehicle;
		this.user = user;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}











	public Long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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


	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}











	@Override
	public String toString() {
		return "Payments [paymentId=" + paymentId + ", reservation=" + reservation + ", vehicle=" + vehicle + ", user="
				+ user + ", paymentMethod=" + paymentMethod + ", paymentDate=" + paymentDate + ", amount=" + amount
				+ "]";
	}





	


	
	

   
}

