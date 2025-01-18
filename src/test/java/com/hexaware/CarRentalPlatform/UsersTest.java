package com.hexaware.CarRentalPlatform;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.repository.UsersRepository;

@SpringBootTest
public class UsersTest {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Test
	void saveMethodTest() 
	{
		Users users=new Users("Nila","N","nila@12","nila@gmail.com","nila@123","9258525810","user");
		Users savedUsers=usersRepository.save(users);
		System.out.println(savedUsers.getUserId()+" created "+savedUsers.getCreatedAt()+" updated "+savedUsers.getUpdatedAt());
	}
	
	@Test
	void updateUsingsaveMethodTest()
	{
		//find entity by id
		Users existing=  usersRepository.findById(2L).get(); 
		//update
		existing.setPassword("nila@12345");
		existing.setUsername("nila@12345");
		//Entitymanager.merge()
		usersRepository.save(existing);
	}
	
	@Test
	void findByIdMethodTest()
	{
		//find entity by id
		Users existing=  usersRepository.findById(2L).get();
	}
	
	@Test
	void saveAllTest()
	{
		//find entity by id
		Users users=new Users("Nila","N","nila@12","nila@gmail.com","nila@123","9258525810","user");
		Users users1=new Users("Nila","N","nila@12","nila@gmail.com","nila@123","9258525810","user");
		usersRepository.saveAll(List.of(users,users1));
	}
	
	@Test
	void findAllTest()
	{
		List<Users> productList=usersRepository.findAll();
		System.out.println(productList);
	}
	
	@Test
	void deleteByIdTest()
	{
		usersRepository.deleteById(7L);
	}

}
