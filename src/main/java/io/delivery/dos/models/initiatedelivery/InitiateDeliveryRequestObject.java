package io.delivery.dos.models.initiatedelivery;

public class InitiateDeliveryRequestObject {

	public String orderid ;
	public int deliveryid ;
	
	//rhash , sign , payment id 
	
	public InitiateDeliveryRequestObject() {}
	public InitiateDeliveryRequestObject(String orderid, int deliveryid) {
		super();
		this.orderid = orderid;
		this.deliveryid = deliveryid;
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getDeliveryid() {
		return deliveryid;
	}
	public void setDeliveryid(int deliveryid) {
		this.deliveryid = deliveryid;
	}
	
	
}
