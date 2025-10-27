package com.Learn.DirectAuthFromDb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Learn.DirectAuthFromDb.Dto.LoginDto;
import com.Learn.DirectAuthFromDb.Dto.UserDto;
import com.Learn.DirectAuthFromDb.Entity.User;
import com.Learn.DirectAuthFromDb.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
//-------------------------------------------------Register API------------------------------------------------------------
	public String registerUser(UserDto userDto) {
		
		if(userRepository.existsByUsername(userDto.getUsername())) {
			return "Username Already Exsists.";
		}
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(user);
		return "User Registered Successfully";
	}
//-------------------------------------------------Login API----------------------------------------------------------------
	
//	public String loginUser(String username, String password) {	
//		User user = userRepository.findByUsername(username);
//		if(user != null && user.getPassword().matches(password)) {
//			return "Login Successfull";
//		}
//		return "Invalid Username Or Password";
//	}
//--------------------------------------------------------------------------------------------------------------------------
}
