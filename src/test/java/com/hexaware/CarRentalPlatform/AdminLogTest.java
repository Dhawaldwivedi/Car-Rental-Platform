package com.hexaware.CarRentalPlatform;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.CarRentalPlatform.Models.AdminLog;
import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.repository.AdminLogRepository;

@SpringBootTest
public class AdminLogTest {
	
	@Autowired
	private AdminLogRepository adminLogRepository;
	
	@Test
	void saveMethodTest()
	{
		Users user = new Users();
	    user.setUserId(4L);
	    AdminLog adminLog = new AdminLog();
	    adminLog.setUser(user); 
	    adminLog.setActionDescription("Admin updated user details.");
	    
	    AdminLog savedadminLog=adminLogRepository.save(adminLog);
		System.out.println(savedadminLog.getActionId()+" created "+savedadminLog.getActionDate());
	}

	@Test
	void updateUsingsaveMethodTest()
	{
		//find entity by id
		AdminLog existing=  adminLogRepository.findById(1L).get(); 
		//update
		existing.setActionDescription("Admin updated");;
		//Entitymanager.merge()
		adminLogRepository.save(existing);
	}
	
	@Test
	void findByIdMethodTest()
	{
		//find entity by id
		AdminLog existing=  adminLogRepository.findById(1L).get();
	}
	
	@Test
	void findAllTest()
	{
		List<AdminLog> productList=adminLogRepository.findAll();
		System.out.println(productList);
	}
	
	@Test
	void deleteByIdTest()
	{
		adminLogRepository.deleteById(1L);
	}

}
