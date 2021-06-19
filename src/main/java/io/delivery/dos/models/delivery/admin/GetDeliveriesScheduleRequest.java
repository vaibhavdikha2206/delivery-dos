package io.delivery.dos.models.delivery.admin;

public class GetDeliveriesScheduleRequest {

	private String pickuptime ;
	private int locationcode ;
	
	public GetDeliveriesScheduleRequest() {}

	public GetDeliveriesScheduleRequest(String pickuptime,int locationcode) {
		super();
		this.pickuptime = pickuptime;
		this.locationcode = locationcode;
	}

	
	public int getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(int locationcode) {
		this.locationcode = locationcode;
	}

	public String getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}
	
	
}