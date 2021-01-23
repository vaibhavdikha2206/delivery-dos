package io.delivery.dos.models.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address  {

	@Id
	@GeneratedValue
	@Column(name = "addressid")
	private Integer addressid;
	
	@Column(name = "userid")
	private String userid;

	@Column(name = "address")
	private String address;
	
	@Column(name = "defaultbit")
	private int defaultbit;

	
	public Address() {
	
	}
	
	public Address(Integer addressid, String userid, String address, int defaultbit) {
		super();
		this.addressid = addressid;
		this.userid = userid;
		this.address = address;
		this.defaultbit = defaultbit;
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