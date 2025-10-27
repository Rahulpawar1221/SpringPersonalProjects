package com.JWT.LearnJWT.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.JWT.LearnJWT.Service.CustomUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
		    .httpBasic(withDefaults());
		return http.build();
	}
//----------------------------------------------------------------------------------------------------------------
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
//----------------------------------------------------------------------------------------------------------------

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//----------------------------------------------------------------------------------------------------------------

	public AuthenticationManager authenticationmanager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		
		return new ProviderManager(daoAuthenticationProvider);
	}
}
