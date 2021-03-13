package io.delivery.dos.models.delivery.userdeliveries;

import java.util.List;

import io.delivery.dos.models.delivery.DeliverResponseWithOriginAddress;
import io.delivery.dos.models.delivery.Deliveries;

public class GetDeliveriesListWithAddressResponse {

	private List<DeliverResponseWithOriginAddress> deliveries ;

	
	public GetDeliveriesListWithAddressResponse() {}
	
	public GetDeliveriesListWithAddressResponse(List<DeliverResponseWithOriginAddress> deliveries) {
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
