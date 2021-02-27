package io.delivery.dos.models.delivery.admin;

public class GetDeliveriesScheduleRequest {

	private String pickuptime ;
	
	public GetDeliveriesScheduleRequest() {}

	public GetDeliveriesScheduleRequest(String pickuptime) {
		super();
		this.pickuptime = pickuptime;
	}

	public String getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}
	
	
}