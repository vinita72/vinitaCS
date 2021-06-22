package com.FlightBookingSystem.SecurePassenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.FlightBookingSystem.SecurePassenger.model.Users;
import com.FlightBookingSystem.SecurePassenger.repository.UsersRepository;

import java.util.Arrays;
import java.util.List;



@Component
public class MongoUserDetailsService implements UserDetailsService{
  @Autowired
  private UsersRepository repository;
  
  @Autowired
  private BCryptPasswordEncoder encoder;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = repository.findByUsername(username);
    if(user == null) {
      throw new UsernameNotFoundException("user not found");
    }
    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
 
    return new User(user.getUsername(), user.getPassword(), authorities);
  }
  
  
		public List<Users> getUsers() {
			return repository.findAll();	
			
		}
		
		
		
		@SuppressWarnings("unchecked")
		public List<Users> add(Users users) {			
			users.setPassword(encoder.encode(users.getPassword()));
			return (List<Users>) repository.save(users);	
			
		}
  
}