package io.delivery.dos.models.delivery.admin;

import java.util.List;

import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.user.response.ProfileResponse;

public class GetDeliveriesScheduleResponse {

	private List<Deliveries> deliveriesSchedule ;
	private List<ProfileResponse> freeriders;
	
	public GetDeliveriesScheduleResponse() {}
	
	public GetDeliveriesScheduleResponse(List<Deliveries> deliveriesSchedule,List<ProfileResponse> freeriders) {
		super();
		this.deliveriesSchedule = deliveriesSchedule;
		this.freeriders = freeriders;
	}

	public List<Deliveries> getDeliveriesSchedule() {
		return deliveriesSchedule;
	}

	public void setDeliveriesSchedule(List<Deliveries> deliveriesSchedule) {
		this.deliveriesSchedule = deliveriesSchedule;
	}

	public List<ProfileResponse> getFreeriders() {
		return freeriders;
	}

	public void setFreeriders(List<ProfileResponse> freeriders) {
		this.freeriders = freeriders;
	}
	
}