package io.delivery.dos.models.order.request;

import java.util.List;

public class OrderRequest {

	private int vendorid;
	private int addressid;
	// add address object 
	private List<OrderItem> orderlist;
	private float totalamount;
	

	public OrderRequest(int vendorid, int addressid, List<OrderItem> orderlist, float totalamount) {
		super();
		this.vendorid = vendorid;
		this.addressid = addressid;
		this.orderlist = orderlist;
		this.totalamount = totalamount;
	}

	public OrderRequest() {
		
	}
	
	
	public float getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(float totalamount) {
		this.totalamount = totalamount;
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
	public List<OrderItem> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<OrderItem> orderlist) {
		this.orderlist = orderlist;
	}
	
}
