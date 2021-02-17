package io.delivery.dos.models.status;

public class StatusUpdateRequestObject {

	private int deliveryid;
	private int status;
	
	public StatusUpdateRequestObject() {}
	
	public StatusUpdateRequestObject(int deliveryid, int status) {
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
