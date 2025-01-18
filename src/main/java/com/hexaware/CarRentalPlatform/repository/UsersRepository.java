package com.hexaware.CarRentalPlatform.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.CarRentalPlatform.Models.Users ;

import jakarta.transaction.Transactional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET first_name = :firstName, last_name = :lastName,username=:username, password = :password, " +
            "phone_number = :phoneNumber,updated_at=:updatedAt WHERE user_id = :userId", nativeQuery = true)
    int updateUserDetails(@Param("userId") Long userId,
                          @Param("firstName") String firstName,
                          @Param("lastName") String lastName,
                          @Param("username") String username,
                          @Param("password") String password,
                          @Param("phoneNumber") String phoneNumber,
                          @Param("updatedAt") LocalDateTime updatedAt);
    
    
	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<Users> findByEmail(@Param("email") String email);
	

	 @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	 Users getByEmail(@Param("email") String email);

		@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Users u WHERE u.email = :email")
	boolean existsByEmail(@Param("email") String email);

	
	@Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	Users findByUsername(String username);

	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Users u WHERE u.username = :username")
	boolean existsByUsername(@Param("username") String username);



}