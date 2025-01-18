package com.hexaware.CarRentalPlatform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.AdminLog;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.AdminLogDTO;
import com.hexaware.CarRentalPlatform.repository.AdminLogRepository;

@Service
public class AdminLogServiceImpl implements IAdminLogService {

	private AdminLogRepository adminLogRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	public AdminLogServiceImpl(AdminLogRepository adminLogRepository) {
		super();
		this.adminLogRepository = adminLogRepository;
	}

	@Override
	public AdminLogDTO createAdminLog(AdminLogDTO a) {
		// TODO Auto-generated method stub
		AdminLog adminLog = mapper.map(a, AdminLog.class);
		AdminLog savedAdminLog = this.adminLogRepository.save(adminLog);
		AdminLogDTO adminLogDTO = mapper.map(savedAdminLog, AdminLogDTO.class);
		return adminLogDTO;		}

	@Override
	public Optional<AdminLog> getAdminLogById(Long actionId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = adminLogRepository.existsById(actionId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("AdminLog", "actionId", actionId);
        }
		return adminLogRepository.findById(actionId);
	}

	@Override
	public List<AdminLog> getAllAdminLogs() {
		// TODO Auto-generated method stub
        return (List<AdminLog>) this.adminLogRepository.findAll();
	}

	@Override
	public List<AdminLog> getAdminLogsByUserId(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = adminLogRepository.existsByUserId(userId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("AdminLog", "userId", userId);
        }
        return adminLogRepository.findAllByUserId(userId);
	}

	@Override
	public List<AdminLog> getAdminLogsByActionDescription(String description) {
		// TODO Auto-generated method stub
        return adminLogRepository.findByActionDescription(description);
	}

	@Override
	public void deleteAdminLogsByUserId(Long userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = adminLogRepository.existsByUserId(userId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("AdminLog", "userId", userId);
        }
        this.adminLogRepository.deleteByUserId(userId);
	}

	@Override
	public void deleteAdminLogById(Long actionId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		boolean exists = adminLogRepository.existsById(actionId);

        if (!exists) {
            // Throw exception if the user is not found
            throw new ResourceNotFoundException("AdminLog", "actionId", actionId);
        }
        this.adminLogRepository.deleteById(actionId);
	}

   
}