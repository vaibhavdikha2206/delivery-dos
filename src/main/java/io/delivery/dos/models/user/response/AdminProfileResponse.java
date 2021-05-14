package io.delivery.dos.models.user.response;

import java.util.List;

import io.delivery.dos.models.user.Profile;

public class AdminProfileResponse {

	private List<Profile> profiles ;

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public AdminProfileResponse(List<Profile> profiles) {
		super();
		this.profiles = profiles;
	}
	
}
