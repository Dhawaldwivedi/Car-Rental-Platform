package com.hexaware.CarRentalPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.CarRentalPlatform.secutiry.JwtAuthenticationEntryPoint;
import com.hexaware.CarRentalPlatform.secutiry.JwtAuthenticationFilter;



@Configuration

@EnableMethodSecurity

public class SecurityConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	private JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(UserDetailsService userDetailsService,

			JwtAuthenticationEntryPoint authenticationEntryPoint,

			JwtAuthenticationFilter jwtAuthenticationFilter) {

		this.userDetailsService = userDetailsService;

		this.authenticationEntryPoint = authenticationEntryPoint;

		this.jwtAuthenticationFilter = jwtAuthenticationFilter;

	}

	@Bean

	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception

	{

		return configuration.getAuthenticationManager();

	}

	@Bean

	public static PasswordEncoder passwordEncoder()

	{

		return new BCryptPasswordEncoder();

	}

			 
			@Bean
				SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
				{
					httpSecurity.csrf().disable()
						.authorizeHttpRequests((authorize) ->
						//authorize.anyRequest().authenticated()
						authorize.requestMatchers( "/auth/**").permitAll().
						requestMatchers( "/userapi/**").permitAll().
						requestMatchers( "/vehicleapi/**").permitAll().
						requestMatchers( "/reviewsapi/**").permitAll().
						requestMatchers( "/reservationapi/**").permitAll().
						requestMatchers( "/paymentsapi/**").permitAll().
						requestMatchers( "/admindataapi/**").permitAll().
						requestMatchers( "/adminapi/**").permitAll()
						.anyRequest().authenticated())
						.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
						.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
					httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
					return httpSecurity.build();
				}
			 

}
