package com.hexaware.CarRentalPlatform;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class CarRentalPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalPlatformApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }


}
