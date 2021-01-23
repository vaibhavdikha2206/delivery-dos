package io.delivery.dos.models.vendor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Vendors {

	@Id
	@GeneratedValue
	@Column(name = "vendorid")
	private Integer vendorid;

	@Column(name = "vendor")
	private String vendor;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "rating")
	private Float rating;
	
	public Vendors() {

	}
	
	
	
	public Vendors(Integer vendorid, String vendor, String address, String category, String city, String latitude,
			String longitude, Float rating) {
		super();
		this.vendorid = vendorid;
		this.vendor = vendor;
		this.address = address;
		this.category = category;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.rating = rating;
	}



	public Integer getVendorid() {
		return vendorid;
	}



	public void setVendorid(Integer vendorid) {
		this.vendorid = vendorid;
	}



	public String getVendor() {
		return vendor;
	}



	public void setVendor(String vendor) {
		this.vendor = vendor;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public Float getRating() {
		return rating;
	}



	public void setRating(Float rating) {
		this.rating = rating;
	}

}
