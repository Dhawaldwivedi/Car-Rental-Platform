package com.hexaware.CarRentalPlatform.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.CarRentalPlatform.Models.Admin;

import jakarta.transaction.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE admin SET first_name = :firstName, last_name = :lastName,admin_name=:adminName, password = :password, " +
            "phone_number = :phoneNumber, updated_at=:updatedAt WHERE admin_id = :adminId", nativeQuery = true)
    int updateAdminDetails(@Param("adminId") Long adminId,
                          @Param("firstName") String firstName,
                          @Param("lastName") String lastName,
                          @Param("adminName") String adminName,
                          @Param("password") String password,
                          @Param("phoneNumber") String phoneNumber,
                          @Param("updatedAt") LocalDateTime updatedAt);
	
	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Admin u WHERE u.adminName = :adminName")
	boolean existsByAdminname(@Param("adminName") String adminName);
	
	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Admin u WHERE u.email = :email")
	boolean existsByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM admin WHERE admin_name = :adminName", nativeQuery = true)
	Admin findByAdminName(String adminName);
	
	 @Query(value = "SELECT * FROM admin WHERE email = :email", nativeQuery = true)
	 Admin getByEmail(@Param("email") String email);
	 
	 @Query(value = "SELECT * FROM admin WHERE email = :email", nativeQuery = true)
	 Optional<Admin> findByEmail(@Param("email") String email);


}
