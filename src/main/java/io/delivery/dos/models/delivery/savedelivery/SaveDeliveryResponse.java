package io.delivery.dos.models.delivery.savedelivery;

public class SaveDeliveryResponse {

	private long amountDue ;
	private String orderId ;
	private int deliveryId ;
	
	public SaveDeliveryResponse() {}
	
	public SaveDeliveryResponse(long amountDue, String orderId, int deliveryId) {
		super();
		this.amountDue = amountDue;
		this.orderId = orderId;
		this.deliveryId = deliveryId;
	}

	public long getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(long amountDue) {
		this.amountDue = amountDue;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	
	
}
