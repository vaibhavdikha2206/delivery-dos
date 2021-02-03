package io.delivery.dos.models.delivery.availability;

import com.google.api.client.util.DateTime;

public class AvailabilityRequest {

	public String requestDateTime ;

	public String getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public AvailabilityRequest(String requestDateTime) {
		super();
		this.requestDateTime = requestDateTime;
	}
	
	public AvailabilityRequest() {
		
	}
	
}
