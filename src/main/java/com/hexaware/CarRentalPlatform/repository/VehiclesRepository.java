package com.hexaware.CarRentalPlatform.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.CarRentalPlatform.Models.Vehicles;
import com.hexaware.CarRentalPlatform.dto.VehiclesDTO.AvailabilityStatus;

import jakarta.transaction.Transactional;

public interface VehiclesRepository extends JpaRepository<Vehicles, Long> {
	
	@Modifying 
    @Transactional
    @Query(value = "UPDATE vehicles SET make = :make, model = :model,imageURL=:imageURL,location=:location, year = :year, " +
            "capacity = :capacity, price_per_day = :pricePerDay,availability_status=:availabilityStatus,updated_at=:updatedAt WHERE vehicle_id = :vehicleId", nativeQuery = true)
    int updateVehicleDetails(@Param("vehicleId") Long vehicleId, 
                          @Param("make") String make,
                          @Param("model") String model,
                          @Param("imageURL") String imageURL,
                          @Param("location") String location,
                          @Param("year") int year,
                          @Param("capacity") int capacity,
                          @Param("pricePerDay") double pricePerDay,
                          @Param("availabilityStatus")AvailabilityStatus availabilityStatus,
                          @Param("updatedAt") LocalDateTime updatedAt);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE vehicles SET availability_status = 'Reserved' WHERE vehicle_id = :vehicleId", nativeQuery = true)
	int updateVehicleStatusToReserved(@Param("vehicleId") Long vehicleId);



    @Query(value = "SELECT * FROM vehicles WHERE availability_status = :status", nativeQuery = true)
    List<Vehicles> findByAvailabilityStatus(@Param("status") String status);
    
    @Query(value = "SELECT * FROM vehicles WHERE make = :make", nativeQuery = true)
    List<Vehicles> findBymake(@Param("make") String make);
    
    @Query(value = "SELECT * FROM vehicles WHERE model = :model", nativeQuery = true)
    List<Vehicles> findBymodel(@Param("model") String model);
    
    @Query(value = "SELECT * FROM vehicles WHERE location = :location", nativeQuery = true)
    List<Vehicles> findBylocation(@Param("location") String location);
    
    @Query(value = "SELECT * FROM vehicles WHERE capacity = :capacity", nativeQuery = true)
    List<Vehicles> findBycapacity(@Param("capacity") int capacity);
    
    @Query(value = "SELECT * FROM vehicles WHERE price_per_day = :pricePerDay", nativeQuery = true)
    List<Vehicles> findBypricePerDay(@Param("pricePerDay") double pricePerDay);

    

}
