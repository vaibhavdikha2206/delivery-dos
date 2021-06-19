package io.delivery.dos.security.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class ProfileUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userid;
	private String username;
	private String password;
	private String role;
	private String email;
	private int locationcode;
	
	public ProfileUser() {
		
	}
	
	public ProfileUser(String userid, String username, String password, String role, String email,int locationcode) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.locationcode = locationcode;
	}

	
	public int getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(int locationcode) {
		this.locationcode = locationcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
