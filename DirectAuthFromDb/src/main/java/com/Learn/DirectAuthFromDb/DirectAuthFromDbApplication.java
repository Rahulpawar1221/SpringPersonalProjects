package com.Learn.DirectAuthFromDb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DirectAuthFromDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectAuthFromDbApplication.class, args);
		System.out.println("Application Started....");
	}

}


//------------------------------------------Summary About Project-------------------------------------------------------------------------------------

//***This mini project is a basic Spring Boot application that demonstrates user registration and authentication directly from a database using Spring Security.***

//1)This mini project is a basic Spring Boot application that provides user registration and login functionality.
//2)When a user registers, their username, password, and a default role (USER) are saved directly into the database.
//3)The login API authenticates users directly from the database using Spring Security, UserDetails, and UserDetailsService.
//4)Password encoding is implemented using BCryptPasswordEncoder for secure storage.
//5)All endpoints are secured using Spring Security, with public access only allowed to /register and /login.
//6)The project structure includes layers for the entity, repository, service, controller, and security configuration.
//7)Each user in the database is associated with a role, making the system ready for implementing role-based access control in the future.
//8)This project is a great starting point for learning Spring Boot Security with database-backed authentication and scalable architecture.