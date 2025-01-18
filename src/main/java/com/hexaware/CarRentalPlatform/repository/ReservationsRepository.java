package com.hexaware.CarRentalPlatform.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.CarRentalPlatform.Models.Reservations;
import com.hexaware.CarRentalPlatform.Models.Vehicles;

import jakarta.transaction.Transactional;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {

    
    @Query(value = "SELECT * FROM reservations WHERE user_id = :userId", nativeQuery = true)
    List<Reservations> findByUserId(@Param("userId") Long userId);
    
    @Query(value = "SELECT * FROM reservations WHERE vehicle_id = :vehicleId", nativeQuery = true)
    List<Reservations> findByVehicleId(@Param("vehicleId") Long vehicleId);


    @Query("SELECT r FROM Reservations r WHERE r.pickupDateTime BETWEEN :startDate AND :endDate")
    List<Reservations> findByPickupDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT r FROM Reservations r WHERE r.dropOffDateTime BETWEEN :startDate AND :endDate")
    List<Reservations> findByDropOffDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Modifying 
    @Transactional
    @Query(value = "UPDATE reservations r SET vehicle_id = :vehicleId, r.pickup_date_time = :pickupDateTime, r.drop_off_date_time = :dropOffDateTime, " +
    		" r.total_cost = :totalCost,r.updated_at=:updatedAt WHERE reservation_id = :reservationId", nativeQuery = true)
    int updatereservationDetails(@Param("reservationId") Long reservationId,
    					  @Param("vehicleId") Long vehicleId, 
                          @Param("pickupDateTime") LocalDateTime pickupDateTime,
                          @Param("dropOffDateTime") LocalDateTime dropOffDateTime,
                          @Param("totalCost") double totalCost,
                          @Param("updatedAt") LocalDateTime updatedAt);
    
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservations r WHERE r.user.userId = :userId")
	boolean existsByUserId(@Param("userId") Long userId); 
    
   
    
}