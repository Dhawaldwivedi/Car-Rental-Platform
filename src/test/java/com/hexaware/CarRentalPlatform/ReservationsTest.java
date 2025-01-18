package com.hexaware.CarRentalPlatform;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.Models.Reviews;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.repository.ReservationsRepository;

@SpringBootTest
public class ReservationsTest {
	
	@Autowired
	private ReservationsRepository reservationsRepository;
	
	
	
	
	@Test
	void findByIdMethodTest()
	{
		//find entity by id
		Reservations existing=  reservationsRepository.findById(1L).get();
	}
	
	@Test
	void findAllTest()
	{
		List<Reservations> productList=reservationsRepository.findAll();
		System.out.println(productList);
	}
	
	@Test
	void deleteByIdTest()
	{
		reservationsRepository.deleteById(1L);
	}


}
