package io.delivery.dos.models.riderdeliverylist;

import java.util.List;

import io.delivery.dos.models.delivery.Deliveries;

public class RiderPendingDeliveriesResponse {

	private List<Deliveries> deliveries;

	public RiderPendingDeliveriesResponse(List<Deliveries> deliveries) {
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
