package io.delivery.dos.models.user.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class ProfileResponse {

	@Id
	@GeneratedValue
	@Column(name = "userid")
	private String userid;

	@Column(name = "username")
	private String username;

	@Column(name = "token")
	private String token;

	public ProfileResponse() {

	}

	public ProfileResponse(String userid, String username,String token) {
		super();
		this.userid = userid;
		this.username = username;
		this.token = token;
	}

	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
