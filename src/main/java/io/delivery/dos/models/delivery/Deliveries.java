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
	private Integer deliveryid;
	
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
	private String riderid;

	@Column(name = "orderid")
	private String orderid;

	@Column(name = "deliverycharge")
	private int deliverycharge;
	
	public Deliveries() {}
	
	public Deliveries(Integer deliveryid, String userid, String pickuptime, int addressid, String dropaddress,
			Double droplatitude, Double droplongitude, String status, String riderid,String orderid,int deliverycharge) {
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
		this.orderid = orderid;
		this.deliverycharge=deliverycharge;
	}

	public int getDeliverycharge() {
		return deliverycharge;
	}

	public void setDeliverycharge(int deliverycharge) {
		this.deliverycharge = deliverycharge;
	}
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public Integer getDeliveryid() {
		return deliveryid;
	}

	public void setDeliveryid(Integer deliveryid) {
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

	public String getRiderid() {
		return riderid;
	}

	public void setRiderid(String riderid) {
		this.riderid = riderid;
	}
		
}
