package io.delivery.dos.models.address.request;

import javax.persistence.Column;

import io.delivery.dos.models.user.Profile;


public class AddressRequestObject {

	@Column(name = "address")
	private String address;

	@Column(name = "defaultbit")
	private int defaultbit;

	public AddressRequestObject() {}
	
	public AddressRequestObject(String userid, String address, int defaultbit) {
		super();
		this.address = address;
		this.defaultbit = defaultbit;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDefaultbit() {
		return defaultbit;
	}

	public void setDefaultbit(int defaultbit) {
		this.defaultbit = defaultbit;
	}

	
	
}

