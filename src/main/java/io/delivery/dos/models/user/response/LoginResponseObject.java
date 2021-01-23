package io.delivery.dos.models.user.response;

import java.util.List;

public class LoginResponseObject {

	private ProfileResponse profile=null;
	private String status = "0";
	
	public LoginResponseObject(ProfileResponse profile,String status) {
		super();
		this.profile = profile;
		this.status=status;
	}

	public ProfileResponse getProfile() {
		return profile;
	}

	public void setProfile(ProfileResponse profile) {
		this.profile = profile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
