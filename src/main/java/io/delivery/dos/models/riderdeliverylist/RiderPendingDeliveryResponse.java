package io.delivery.dos.models.riderdeliverylist;


import io.delivery.dos.models.delivery.Deliveries;

public class RiderPendingDeliveryResponse {

	private Deliveries delivery;

	public RiderPendingDeliveryResponse(Deliveries delivery) {
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