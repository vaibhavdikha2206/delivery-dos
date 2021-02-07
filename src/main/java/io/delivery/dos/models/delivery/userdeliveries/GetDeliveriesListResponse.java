package io.delivery.dos.models.delivery.userdeliveries;

import java.util.List;

import io.delivery.dos.models.delivery.Deliveries;

public class GetDeliveriesListResponse {

	private List<Deliveries> deliveries ;

	
	public GetDeliveriesListResponse() {}
	
	public GetDeliveriesListResponse(List<Deliveries> deliveries) {
		super();
		this.deliveries = deliveries;
	}

	public List<Deliveries> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Deliveries> deliveries) {
		this.deliveries = deliveries;
	}
	
	
}
