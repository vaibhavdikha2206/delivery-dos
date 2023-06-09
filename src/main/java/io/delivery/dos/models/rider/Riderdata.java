package io.delivery.dos.models.rider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Riderdata {

	@Id
	@GeneratedValue
	@Column(name = "riderid")
	private int riderid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "rating")
	private Float rating;

	@Column(name = "token")
	private String token;
	
	public Riderdata() {}
	
	public Riderdata(int riderid, String name, String phone, float rating,String token) {
		super();
		this.riderid = riderid;
		this.name = name;
		this.phone = phone;
		this.rating = rating;
		this.token=token;
	}

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getRiderid() {
		return riderid;
	}

	public void setRiderid(int riderid) {
		this.riderid = riderid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
	
}
