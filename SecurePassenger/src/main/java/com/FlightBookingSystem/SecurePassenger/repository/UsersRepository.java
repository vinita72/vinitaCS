package com.FlightBookingSystem.SecurePassenger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.FlightBookingSystem.SecurePassenger.model.Users;


public interface  UsersRepository extends MongoRepository<Users, String> {
	
	
	    Users findByUsername(String username);
	
	}


