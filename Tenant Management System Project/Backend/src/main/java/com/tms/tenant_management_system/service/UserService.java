package com.tms.tenant_management_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.tms.tenant_management_system.entity.Users; // ✅ CORRECT
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tms.tenant_management_system.configuration.JwtUtil;
import com.tms.tenant_management_system.dto.UserDto;
import com.tms.tenant_management_system.entity.Role;
import com.tms.tenant_management_system.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    public String registerUser(UserDto userDto) {
    	Optional<Users> existingUser = userRepository.findByUsername(userDto.getUsername());
        
        if (existingUser.isPresent()) {
            return "Username already registered";  // Just return this string if user exists
        }

        Users user = new Users(); // 👈 Standard object creation
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.TENANT); // 👈 Default role

        userRepository.save(user);
        return "User registered successfully, Please proceed to login";
    }

    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%to

//    public String login(UserDto userDto) {
//    	try {
//	        Authentication authentication = authenticationManager.authenticate(
//	            new UsernamePasswordAuthenticationToken(
//	                userDto.getUsername(), userDto.getPassword())
//	        );
//	        if (authentication.isAuthenticated()) {
//	            org.springframework.security.core.userdetails.UserDetails userDetails =
//	                (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
//
//	            String token = jwtUtil.generateToken(userDetails);
//	            return token;
//	        } else {
//	            throw new RuntimeException("Login failed");
//	        }
//	    } catch (AuthenticationException e) {
//	        throw new RuntimeException("Invalid username or password.");
//	    }
//    }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
	
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
}