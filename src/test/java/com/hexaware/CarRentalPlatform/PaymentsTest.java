package com.hexaware.CarRentalPlatform;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.CarRentalPlatform.Models.Payments;
import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.repository.PaymentsRepository;


@SpringBootTest
public class PaymentsTest {

	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@Test
	void saveMethodTest()
	{
	    Reservations reservations = new Reservations();
	    reservations.setReservationId(2L);
	    Payments payments = new Payments();
	    payments.setReservation(reservations); 
	    payments.setPaymentMethod(Payments.PaymentMethod.CASH);
	    payments.setAmount(1500.0);
	    
	    Payments savedpayments=paymentsRepository.save(payments);
		System.out.println(savedpayments.getPaymentId()+" payment at "+savedpayments.getPaymentDate());
	}
	
	@Test
	void updateUsingsaveMethodTest()
	{
		//find entity by id
		Payments existing=  paymentsRepository.findById(1L).get(); 
		//update
		existing.setAmount(2000.0);
		//Entitymanager.merge()
		paymentsRepository.save(existing);
	}
	
	@Test
	void findByIdMethodTest()
	{
		//find entity by id
		Payments existing=  paymentsRepository.findById(1L).get();
	}
	
	@Test
	void findAllTest()
	{
		List<Payments> productList=paymentsRepository.findAll();
		System.out.println(productList);
	}
	
	@Test
	void deleteByIdTest()
	{
		paymentsRepository.deleteById(1L);
	}
}
