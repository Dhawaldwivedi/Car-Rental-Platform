package com.hexaware.CarRentalPlatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.CarRentalPlatform.Models.AdminLog;
import com.hexaware.CarRentalPlatform.customexceptions.ResourceNotFoundException;
import com.hexaware.CarRentalPlatform.dto.AdminLogDTO;
import com.hexaware.CarRentalPlatform.service.AdminLogServiceImpl;

@RestController
@RequestMapping("/adminapi/v1/")
public class AdminLogController {
	
	private AdminLogServiceImpl adminLogServiceImpl;

	@Autowired
	public AdminLogController(AdminLogServiceImpl adminLogServiceImpl) {
		super();
		this.adminLogServiceImpl = adminLogServiceImpl;
	}
	
	@PostMapping("/createadminlog")
	ResponseEntity<AdminLogDTO>createUser(@RequestBody AdminLogDTO r)
	{
		return ResponseEntity.ok(this.adminLogServiceImpl.createAdminLog(r));
	}
	
	@GetMapping("/getAdminLogById/{id}")
    public ResponseEntity<Optional<AdminLog>> findAdminLogById(@PathVariable("id") Long actionId)throws ResourceNotFoundException  {
        return ResponseEntity.ok(this.adminLogServiceImpl.getAdminLogById(actionId));
    }

	@GetMapping("/getAllAdminLogs")
    public ResponseEntity<List<AdminLog>> getAllAdminLogs() {
        List<AdminLog> adminLogs = adminLogServiceImpl.getAllAdminLogs();
        return ResponseEntity.ok(adminLogs);
    }
	
	@GetMapping("/getAdminLogsByUserId/{userId}")
    public ResponseEntity<List<AdminLog>> findAdminLogsByUserId(@PathVariable("userId") Long userId)throws ResourceNotFoundException {
        return ResponseEntity.ok(this.adminLogServiceImpl.getAdminLogsByUserId(userId));
    }
    
	@GetMapping("/getAdminLogsByActionDescription/{description}")
    public ResponseEntity<List<AdminLog>> findAdminLogsByActionDescription(@PathVariable("description") String description) {
        return ResponseEntity.ok(this.adminLogServiceImpl.getAdminLogsByActionDescription(description));
    }
    
	@DeleteMapping("/deleteAdminLogByUserId/{id}")
    public ResponseEntity<String> deleteAdminLogByUserId(@PathVariable("id") Long userId)throws ResourceNotFoundException {
        this.adminLogServiceImpl.deleteAdminLogsByUserId(userId);
        return ResponseEntity.ok("Admin log deleted successfully");
    }
	
	@DeleteMapping("/deleteAdminLogById/{id}")
    public ResponseEntity<String> deleteAdminLogById(@PathVariable("id") Long actionId) throws ResourceNotFoundException{
        this.adminLogServiceImpl.deleteAdminLogById(actionId);
        return ResponseEntity.ok("Admin log deleted successfully");
    }
}
