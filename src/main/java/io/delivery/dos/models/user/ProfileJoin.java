package io.delivery.dos.models.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class ProfileJoin {

	@Id
	@GeneratedValue
	@Column(name = "userid")
	private String userid;

	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	
	public ProfileJoin() {

	}

	
	public ProfileJoin(String userid, String username, String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
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

	
}
