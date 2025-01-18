package com.hexaware.CarRentalPlatform;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.repository.VehiclesRepository;

@SpringBootTest
public class VehiclesTest {
	
	@Autowired
	private VehiclesRepository vehiclesRepository;
	
	@Test
	void saveMethodTest()
	{
		Vehicles vehicles=new Vehicles("Toyota","Corolla","http://myimages/monitor.jpg","Noida",2022,5,50.0,Vehicles.AvailabilityStatus.Reserved );
		Vehicles savedVehicles=vehiclesRepository.save(vehicles);
		System.out.println(savedVehicles.getVehicleId()+" created "+savedVehicles.getCreatedAt()+" updated "+savedVehicles.getUpdatedAt());
	}
	
	@Test
	void updateUsingsaveMethodTest()
	{
		//find entity by id
		Vehicles existing=  vehiclesRepository.findById(1L).get(); 
		//update
		existing.setAvailabilityStatus(Vehicles.AvailabilityStatus.Available);
		//Entitymanager.merge()
		vehiclesRepository.save(existing);
	}
	
	@Test
	void findByIdMethodTest()
	{
		//find entity by id
		Vehicles existing=  vehiclesRepository.findById(1L).get();
	}
	
	@Test
	void saveAllTest()
	{
		//find entity by id
		Vehicles vehicles=new Vehicles("Maruthi","Corolla","http://myimages/maruthi.jpg","Noida",2022,5,50.0,Vehicles.AvailabilityStatus.Reserved);
		Vehicles vehicles1=new Vehicles("BMW","Corolla","http://myimages/bmw.jpg","Noida",2022,5,50.0,Vehicles.AvailabilityStatus.Available);
		vehiclesRepository.saveAll(List.of(vehicles,vehicles1));
	}
	
	@Test
	void findAllTest()
	{
		List<Vehicles> productList=vehiclesRepository.findAll();
		System.out.println(productList);
	}
	
	@Test
	void deleteByIdTest()
	{
		vehiclesRepository.deleteById(1L);
	}

}
