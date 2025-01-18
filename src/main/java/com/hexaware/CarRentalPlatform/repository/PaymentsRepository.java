package com.hexaware.CarRentalPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.CarRentalPlatform.Models.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

    @Query(value = "SELECT * FROM payments WHERE reservation_id = :reservationId", nativeQuery = true)
    List<Payments> findByReservationId(@Param("reservationId") Long reservationId);
    
    
    @Query("SELECT p FROM Payments p WHERE p.reservation.user.userId = :userId")
    List<Payments> findByUserId(Long userId);
    

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Payments p WHERE p.reservation.user.userId = :userId")
	boolean existsByUserId(@Param("userId") Long userId);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Payments p WHERE p.reservation.reservationId = :reservationId")
	boolean existsByReservationId(Long reservationId);

    @Query(value = "SELECT * FROM payments WHERE reservation_id = :reservationId AND vehicle_id = :vehicleId", nativeQuery = true)
    List<Payments> findByReservationIdAndVehicleId(@Param("reservationId") Long reservationId, @Param("vehicleId") Long vehicleId);
    
    
}

