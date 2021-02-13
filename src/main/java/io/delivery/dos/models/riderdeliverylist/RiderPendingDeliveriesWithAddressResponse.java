package io.delivery.dos.models.riderdeliverylist;

import java.util.List;

import io.delivery.dos.models.delivery.DeliverResponseWithOriginAddress;
import io.delivery.dos.models.delivery.Deliveries;


public class RiderPendingDeliveriesWithAddressResponse {

	private List<DeliverResponseWithOriginAddress> deliveriesDataWithAddress;

	public RiderPendingDeliveriesWithAddressResponse() {
		
	}
	public RiderPendingDeliveriesWithAddressResponse(List<DeliverResponseWithOriginAddress> deliveriesDataWithAddress) {
		super();
		this.deliveriesDataWithAddress = deliveriesDataWithAddress;
	}

	public List<DeliverResponseWithOriginAddress> getDeliveries() {
		return deliveriesDataWithAddress;
	}

	public void setDeliveries(List<DeliverResponseWithOriginAddress> deliveriesDataWithAddress) {
		this.deliveriesDataWithAddress = deliveriesDataWithAddress;
	}
	
	
}