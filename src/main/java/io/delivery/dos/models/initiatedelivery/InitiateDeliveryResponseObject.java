package io.delivery.dos.models.initiatedelivery;

public class InitiateDeliveryResponseObject {

	private int deliveryId;
	private String status ;
	
	
	public InitiateDeliveryResponseObject(int deliveryId, String status) {
		super();
		this.deliveryId = deliveryId;
		this.status = status;
	}
	
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
