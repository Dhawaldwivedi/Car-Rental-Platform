package com.hexaware.CarRentalPlatform.secutiry;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.CarRentalPlatform.Models.Users;
import com.hexaware.CarRentalPlatform.Models.Admin;
import com.hexaware.CarRentalPlatform.repository.UsersRepository;
import com.hexaware.CarRentalPlatform.repository.AdminRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the username exists in UsersRepository
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            if (!"USER".equalsIgnoreCase(user.getRole())) {
                throw new UsernameNotFoundException("Role mismatch: User is not authorized");
            }
            return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
            );
        }

        // Check if the username exists in AdminRepository
        Admin admin = adminRepository.findByAdminName(username);
        if (admin != null) {
            if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {
                throw new UsernameNotFoundException("Role mismatch: Admin is not authorized");
            }
            return new org.springframework.security.core.userdetails.User(
                admin.getAdminName(),
                admin.getPassword(),
                new ArrayList<>()
            );
        }

        // If no user or admin is found, throw an exception
        throw new UsernameNotFoundException("User or Admin not found with username: " + username);
    }
}
