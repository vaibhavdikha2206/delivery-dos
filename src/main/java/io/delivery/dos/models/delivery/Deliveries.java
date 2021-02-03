package io.delivery.dos.models.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Deliveries {

	@Id
	@GeneratedValue
	@Column(name = "deliveryid")
	private int deliveryid;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "pickuptime")
	private String pickuptime;
	
	@Column(name = "addressid")
	private int addressid;
	
	@Column(name = "dropaddress")
	private String dropaddress;
	
	@Column(name = "droplatitude")
	private Double droplatitude;

	@Column(name = "droplongitude")
	private Double droplongitude;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "riderid")
	private int riderid;

	public Deliveries() {}
	
	public Deliveries(int deliveryid, String userid, String pickuptime, int addressid, String dropaddress,
			Double droplatitude, Double droplongitude, String status, int riderid) {
		super();
		this.deliveryid = deliveryid;
		this.userid = userid;
		this.pickuptime = pickuptime;
		this.addressid = addressid;
		this.dropaddress = dropaddress;
		this.droplatitude = droplatitude;
		this.droplongitude = droplongitude;
		this.status = status;
		this.riderid = riderid;
	}

	public int getDeliveryid() {
		return deliveryid;
	}

	public void setDeliveryid(int deliveryid) {
		this.deliveryid = deliveryid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}

	public int getAddressid() {
		return addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getDropaddress() {
		return dropaddress;
	}

	public void setDropaddress(String dropaddress) {
		this.dropaddress = dropaddress;
	}

	public Double getDroplatitude() {
		return droplatitude;
	}

	public void setDroplatitude(Double droplatitude) {
		this.droplatitude = droplatitude;
	}

	public Double getDroplongitude() {
		return droplongitude;
	}

	public void setDroplongitude(Double droplongitude) {
		this.droplongitude = droplongitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRiderid() {
		return riderid;
	}

	public void setRiderid(int riderid) {
		this.riderid = riderid;
	}
	
	
}
