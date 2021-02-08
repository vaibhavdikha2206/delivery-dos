package io.delivery.dos.models.riderdeliveryaccept;

public class RiderDeliveryAcceptResponse {

	private String status ;
	private Integer deliveryid;
	public RiderDeliveryAcceptResponse(String status, Integer deliveryid) {
		super();
		this.status = status;
		this.deliveryid = deliveryid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDeliveryid() {
		return deliveryid;
	}
	public void setDeliveryid(Integer deliveryid) {
		this.deliveryid = deliveryid;
	}
	
}
