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
	
	public ProfileUser() {
		
	}
	
	public ProfileUser(String userid,String password,String username,String role) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.role=role;
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
