package io.delivery.dos.models.riderdeliverylist;


import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.delivery.DeliverResponseWithOriginAddress;
import io.delivery.dos.models.delivery.Deliveries;

public class RiderPendingDeliveryResponse {

	private DeliverResponseWithOriginAddress delivery;
	

	public RiderPendingDeliveryResponse(DeliverResponseWithOriginAddress delivery) {
		super();
		this.delivery = delivery;
	}

	public DeliverResponseWithOriginAddress getDelivery() {
		return delivery;
	}

	public void setDelivery(DeliverResponseWithOriginAddress delivery) {
		this.delivery = delivery;
	}

	
}