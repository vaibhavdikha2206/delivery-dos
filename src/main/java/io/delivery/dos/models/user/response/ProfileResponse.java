package io.delivery.dos.models.user.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class ProfileResponse {

	@Id
	@GeneratedValue
	@Column(name = "userid")
	private String userid;

	@Column(name = "username")
	private String username;


	public ProfileResponse() {

	}

	public ProfileResponse(String userid, String username) {
		super();
		this.userid = userid;
		this.username = username;
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
