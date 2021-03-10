package io.delivery.dos.models.delivery.admin;

public class AssignRiderRequest {

	private String riderid ;
	private int deliveryId ;
	
	public AssignRiderRequest() {}
	
	public AssignRiderRequest(String riderid, int deliveryId) {
		super();
		this.riderid = riderid;
		this.deliveryId = deliveryId;
	}
	
	
	public String getRiderid() {
		return riderid;
	}
	public void setRiderid(String riderid) {
		this.riderid = riderid;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

}