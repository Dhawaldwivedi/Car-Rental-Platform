package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.hexaware.CarRentalPlatform.Models.AdminLog;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.AdminLogDTO;

public interface IAdminLogService {

    AdminLogDTO createAdminLog(AdminLogDTO a);

    Optional<AdminLog> getAdminLogById(Long actionId)throws ResourceNotFoundException;

    List<AdminLog> getAllAdminLogs();

    List<AdminLog> getAdminLogsByUserId(Long userId)throws ResourceNotFoundException;

    List<AdminLog> getAdminLogsByActionDescription(String description);

    void deleteAdminLogsByUserId(Long userId)throws ResourceNotFoundException;

    void deleteAdminLogById(Long actionId)throws ResourceNotFoundException;
}