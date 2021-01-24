package io.delivery.dos.models.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Orders {

	@Id
	@GeneratedValue
	@Column(name = "orderid")
	private Integer orderid;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "vendorid")
	private int vendorid;
	
	@Column(name = "addressid")
	private int addressid;
	
	@Column(name = "orderdescription")
	private String orderdescription;

	@Column(name = "amount")
	private float amount;

	

	public Orders(Integer orderid, String userid, int vendorid, int addressid, String orderdescription, float amount) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.vendorid = vendorid;
		this.addressid = addressid;
		this.orderdescription = orderdescription;
		this.amount = amount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	public int getAddressid() {
		return addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}

	public String getOrderdescription() {
		return orderdescription;
	}

	public void setOrderdescription(String orderdescription) {
		this.orderdescription = orderdescription;
	}
	
	
}
