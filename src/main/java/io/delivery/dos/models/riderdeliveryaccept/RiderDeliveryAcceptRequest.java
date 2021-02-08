package io.delivery.dos.models.riderdeliveryaccept;

public class RiderDeliveryAcceptRequest {

	private Integer deliveryid;

	public RiderDeliveryAcceptRequest() {}
	
	public RiderDeliveryAcceptRequest(Integer deliveryid) {
		super();
		this.deliveryid = deliveryid;
	}

	public Integer getDeliveryid() {
		return deliveryid;
	}

	public void setDeliveryid(Integer deliveryid) {
		this.deliveryid = deliveryid;
	}
	
	
}
