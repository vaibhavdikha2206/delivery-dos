package io.delivery.dos.models.security;

public class SecretObject {

	private String secretKey;

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public SecretObject(String secretKey) {
		super();
		this.secretKey = secretKey;
	}
	
	
}
