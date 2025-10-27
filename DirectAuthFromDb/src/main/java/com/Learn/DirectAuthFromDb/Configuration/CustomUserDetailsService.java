package com.Learn.DirectAuthFromDb.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import com.Learn.DirectAuthFromDb.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Learn.DirectAuthFromDb.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
//	
//	@Autowired
//    private BCryptPasswordEncoder passwordEncoder;

//    public CustomUserDetailsService(BCryptPasswordEncoder passwordEncoder) { // Constructor injection for BCryptPasswordEncoder
//        this.passwordEncoder = passwordEncoder;
//    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}	
		return new CustomUserDetails(user);	
	}	

}
