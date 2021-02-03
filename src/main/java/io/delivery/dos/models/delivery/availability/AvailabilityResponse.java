package io.delivery.dos.models.delivery.availability;

public class AvailabilityResponse {

	private boolean availability = false ;
	
	public AvailabilityResponse() {}
	
	public AvailabilityResponse(boolean availability) {
		super();
		this.availability = availability;
	}


	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	
}
