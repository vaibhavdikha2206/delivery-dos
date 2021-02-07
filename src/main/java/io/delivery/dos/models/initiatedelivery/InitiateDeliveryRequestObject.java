package io.delivery.dos.models.initiatedelivery;

public class InitiateDeliveryRequestObject {

	public String orderid ;
	public int deliveryid ;
	
	public String razorhash ;
	public String razorsignature ;
	public String razorpayid ;
	//rhash , sign , payment id 
	
	public InitiateDeliveryRequestObject() {}
	public InitiateDeliveryRequestObject(String orderid, int deliveryid,String razorhash,String razorsignature,String razorpayid) {
		super();
		this.orderid = orderid;
		this.deliveryid = deliveryid;
		this.razorhash = razorhash;
		this.razorsignature = razorsignature ; 
		this.razorpayid = razorpayid;
	}
	
	
	public String getRazorhash() {
		return razorhash;
	}
	public void setRazorhash(String razorhash) {
		this.razorhash = razorhash;
	}
	public String getRazorsignature() {
		return razorsignature;
	}
	public void setRazorsignature(String razorsignature) {
		this.razorsignature = razorsignature;
	}
	public String getRazorpayid() {
		return razorpayid;
	}
	public void setRazorpayid(String razorpayid) {
		this.razorpayid = razorpayid;
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
