package com.Learn.DirectAuthFromDb.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
//-------------------------------------PassWord Encoder--------------------------------------------------------------	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//-----------------------------------------------------------------------------------------------------------------
	@Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
//------------------------------------------------------------------------------------------------------------------
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
	    .csrf().disable()
	    .authorizeHttpRequests(auth -> auth
	        .requestMatchers("/api/register", "/api/login").permitAll()
	        .requestMatchers("/api/admin/**").hasRole("ADMIN")
	        .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
	        .anyRequest().authenticated())
	        .authenticationProvider(authenticationProvider(customUserDetailsService))
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    
	  //  .httpBasic(); // Only for simple testing

	    return http.build();
	}
//------------------------------------ Authentication Manager--------------------------------------------------------
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
//-------------------------------------------------------------------------------------------------------------------
	
	
	
}
