package io.delivery.dos.models.address;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.delivery.dos.models.delivery.Deliveries;

@Entity
@Table(name="Address")
public class Address  {

	@Id
	@GeneratedValue
	@Column(name="addressid")
	private Integer addressid;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "defaultbit")
	private int defaultbit;

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
	
	@Column(name = "placeid")
	private String placeid;
	
	
	

	public String getPlaceid() {
		return placeid;
	}


	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}


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


	public int getDefaultbit() {
		return defaultbit;
	}


	public void setDefaultbit(int defaultbit) {
		this.defaultbit = defaultbit;
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


	public Address(Integer addressid, String userid, int defaultbit, String housenumber, String floor, String block,
			Double latitude, Double longitude, String locality, String city,String placeid) {
		super();
		this.addressid = addressid;
		this.userid = userid;
		this.defaultbit = defaultbit;
		this.housenumber = housenumber;
		this.floor = floor;
		this.block = block;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locality = locality;
		this.city = city;
		this.placeid=placeid;
	}
	
	public Address() {
		
	}
	
	
}