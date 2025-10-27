package com.Learn.DirectAuthFromDb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.*;

import com.Learn.DirectAuthFromDb.Configuration.JwtUtil;
import com.Learn.DirectAuthFromDb.Dto.UserDto;
import com.Learn.DirectAuthFromDb.Service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private JwtUtil jwtUtil;  // Autowire JwtUtil


//------------------------------------------Register API-------------------------------------------------------------------	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserDto userDto) {
		String msg = userService.registerUser(userDto);
		return msg;
	}
//------------------------------------------Login API----------------------------------------------------------------------
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
	    try {
	        Authentication authentication = authenticationManager
	            .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

	        if (authentication.isAuthenticated()) {
	            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	            String token = jwtUtil.generateToken(userDetails);
	            return ResponseEntity.ok("Login Successful. Token: " + token);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
	        }
	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
	    }
	}


//-----------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/welcome")
    public String welcome() {
        return "Welcome! You are authenticated.";
    }
//-----------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/admin/dashboard")
	public String adminOnlyEndpoint() {
	    return "Welcome, Admin!";
	}

}
