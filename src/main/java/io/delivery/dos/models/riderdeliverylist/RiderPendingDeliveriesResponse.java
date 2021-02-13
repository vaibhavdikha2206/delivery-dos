package io.delivery.dos.models.riderdeliverylist;

import java.util.List;

import io.delivery.dos.models.delivery.DeliverResponseWithOriginAddress;
import io.delivery.dos.models.delivery.Deliveries;

public class RiderPendingDeliveriesResponse {

	private List<DeliverResponseWithOriginAddress> deliveries;

	public RiderPendingDeliveriesResponse(List<DeliverResponseWithOriginAddress> deliveries) {
		super();
		this.deliveries = deliveries;
	}

	public List<DeliverResponseWithOriginAddress> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<DeliverResponseWithOriginAddress> deliveries) {
		this.deliveries = deliveries;
	}
	
	
}
