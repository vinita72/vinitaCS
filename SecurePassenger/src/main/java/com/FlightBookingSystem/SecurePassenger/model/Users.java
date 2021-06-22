package com.FlightBookingSystem.SecurePassenger.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *  @author admin
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection ="users")
public class Users{

	@Id
	private  String id;
	private  String username;
	private  String password;
	private  String emailid;
	private String role;
	
	
	public Users() {
	}
	
	public Users(String id, String username, String password, String emailid, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.emailid = emailid;
		this.role = role;
	}
	
	
	

	public  String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public  String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public  String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	
}
	
	
	



	





	

	
	
	
	
	
//	  public Passenger(Passenger passenger) {
//	        this.emailId = passenger.getEmailId();
//	        this.password = passenger.getPassword();
//	        this.active = passenger.isActive();
//	        this.authorities = Arrays.stream(passenger.getRoles().split(","))
//	                    .map(SimpleGrantedAuthority::new)
//	                    .collect(Collectors.toList());
//	    }
//	  public String getRoles() {
//	        return roles;
//	    }
//	private Object isActive() {
//		// TODO Auto-generated method stub
//		return active;
//	}
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
	
		
	
	
	
	

