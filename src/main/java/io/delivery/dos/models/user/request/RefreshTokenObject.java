package io.delivery.dos.models.user.request;


public class RefreshTokenObject {

	private String token;
	
	public RefreshTokenObject() {}
	
	public RefreshTokenObject(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
