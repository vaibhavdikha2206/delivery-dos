package io.delivery.dos.models.address.request;

import javax.persistence.Column;

import io.delivery.dos.models.user.Profile;


public class AddressRequestObject {

	private Profile profileObject;
	
	@Column(name = "userid")
	private String userid;

	@Column(name = "address")
	private String address;

	@Column(name = "defaultbit")
	private int defaultbit;

	public AddressRequestObject() {}
	
	public AddressRequestObject(Profile profileObject, String userid, String address, int defaultbit) {
		super();
		this.profileObject = profileObject;
		this.userid = userid;
		this.address = address;
		this.defaultbit = defaultbit;
	}
	
	public Profile getProfileObject() {
		return profileObject;
	}

	public void setProfileObject(Profile profileObject) {
		this.profileObject = profileObject;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

