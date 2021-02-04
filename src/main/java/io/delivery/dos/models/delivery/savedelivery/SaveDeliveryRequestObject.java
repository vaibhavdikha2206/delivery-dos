package io.delivery.dos.models.delivery.savedelivery;

import io.delivery.dos.models.delivery.Deliveries;

public class SaveDeliveryRequestObject {

	public Deliveries deliveryObject;
	
	public SaveDeliveryRequestObject() {}
	
	public SaveDeliveryRequestObject(Deliveries deliveryObject) {
		super();
		this.deliveryObject = deliveryObject;
	}

	public Deliveries getDeliveryObject() {
		return deliveryObject;
	}

	public void setDeliveryObject(Deliveries deliveryObject) {
		this.deliveryObject = deliveryObject;
	}
	
	
}
