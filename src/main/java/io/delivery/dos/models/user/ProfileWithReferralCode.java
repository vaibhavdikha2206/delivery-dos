package io.delivery.dos.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class ProfileWithReferralCode {

	@Id
	@GeneratedValue
	@Column(name = "userid")
	private String userid;

	@Column(name = "username")
	private String username;

	@Column(name = "referralcode")
	private String referralcode;
	
	@Column(name = "credits")
	private float credits;
	
	@Column(name = "token")
	private String token;
	
	public ProfileWithReferralCode() {

	}

	
	
	public ProfileWithReferralCode(String userid, String username, String referralcode, float credits, String token) {
		super();
		this.userid = userid;
		this.username = username;
		this.referralcode = referralcode;
		this.credits = credits;
		this.token = token;
	}


	public float getCredits() {
		return credits;
	}



	public void setCredits(float credits) {
		this.credits = credits;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getReferralcode() {
		return referralcode;
	}

	public void setReferralcode(String referralcode) {
		this.referralcode = referralcode;
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