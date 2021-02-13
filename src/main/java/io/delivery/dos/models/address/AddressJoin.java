package io.delivery.dos.models.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressJoin  {

	@Id
	@GeneratedValue
	@Column(name="addressid")
	private Integer addressid;
	
	@Column(name = "userid")
	private String userid;

	@Column(name = "housenumber")
	private String housenumber;
	
	@Column(name = "floor")
	private String floor;
	
	@Column(name = "block")
	private String block;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "locality")
	private String locality;
	
	@Column(name = "city")
	private String city;


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getAddressid() {
		return addressid;
	}


	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getHousenumber() {
		return housenumber;
	}


	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}


	public String getFloor() {
		return floor;
	}


	public void setFloor(String floor) {
		this.floor = floor;
	}


	public String getBlock() {
		return block;
	}


	public void setBlock(String block) {
		this.block = block;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public String getLocality() {
		return locality;
	}


	public void setLocality(String locality) {
		this.locality = locality;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public AddressJoin(Integer addressid, String userid, String housenumber, String floor, String block,
			Double latitude, Double longitude, String locality, String city) {
		super();
		this.addressid = addressid;
		this.userid = userid;
		
		this.housenumber = housenumber;
		this.floor = floor;
		this.block = block;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locality = locality;
		this.city = city;

	}
	
	public AddressJoin() {
		
	}
	
	
}