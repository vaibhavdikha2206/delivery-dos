package io.delivery.dos.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Profile {

	@Id
	@Column(name = "userid")
	private String userid;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "credits")
	private Float credits;
	
	@Column(name = "referralcode")
	private String referralcode;
	
	public Profile() {

	}

	public Profile(String userid, String username, String password, String role, String email, Float credits,
			String referralcode) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.credits = credits;
		this.referralcode = referralcode;
	}

	public Float getCredits() {
		return credits;
	}


	public void setCredits(Float credits) {
		this.credits = credits;
	}


	public String getReferralcode() {
		return referralcode;
	}


	public void setReferralcode(String referralcode) {
		this.referralcode = referralcode;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
