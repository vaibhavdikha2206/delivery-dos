package io.delivery.dos.models.delivery.userdeliveries;

import io.delivery.dos.models.delivery.Deliveries;

public class GetSingleDeliveryResponse {

	private Deliveries delivery ;

	public GetSingleDeliveryResponse() {}
	
	public GetSingleDeliveryResponse(Deliveries delivery) {
		super();
		this.delivery = delivery;
	}

	public Deliveries getDelivery() {
		return delivery;
	}

	public void setDelivery(Deliveries delivery) {
		this.delivery = delivery;
	}
	
	
}
