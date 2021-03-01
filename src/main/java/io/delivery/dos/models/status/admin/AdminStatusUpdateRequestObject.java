package io.delivery.dos.models.status.admin;

public class AdminStatusUpdateRequestObject {

	private int deliveryid;
	private int status;
	
	public AdminStatusUpdateRequestObject() {}
	
	public AdminStatusUpdateRequestObject(int deliveryid, int status) {
		super();
		this.deliveryid = deliveryid;
		this.status = status;
	}
	
	public int getDeliveryid() {
		return deliveryid;
	}
	public void setDeliveryid(int deliveryid) {
		this.deliveryid = deliveryid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
