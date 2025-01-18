package com.hexaware.CarRentalPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.CarRentalPlatform.Models.AdminLog;

import jakarta.transaction.Transactional;

public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {

	@Query("SELECT al FROM AdminLog al WHERE al.user.userId = :userId")
    List<AdminLog> findAllByUserId(@Param("userId") Long userId);
    
    @Query("SELECT al FROM AdminLog al WHERE al.actionDescription LIKE %:description%")
    List<AdminLog> findByActionDescription(@Param("description") String description);
        
    @Transactional
    @Modifying
    @Query("DELETE FROM AdminLog al WHERE al.user.userId = :userId")
    int deleteByUserId(@Param("userId") Long userId);
    
 
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM AdminLog a WHERE a.user.userId = :userId")
	boolean existsByUserId(Long userId);
}