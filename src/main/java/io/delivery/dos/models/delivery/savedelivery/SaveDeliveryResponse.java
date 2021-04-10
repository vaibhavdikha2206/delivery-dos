package io.delivery.dos.models.delivery.savedelivery;

public class SaveDeliveryResponse {

	private long amountDue ;
	private String orderId ;
	private int deliveryId ;
	private String txnToken ; 
	private String paymentMethod ; 
	
	public SaveDeliveryResponse() {}
	
	public SaveDeliveryResponse(long amountDue, String orderId, int deliveryId,String txnToken,String paymentMethod) {
		super();
		this.amountDue = amountDue;
		this.orderId = orderId;
		this.deliveryId = deliveryId;
		this.txnToken = txnToken;
		this.paymentMethod = paymentMethod;
	}

	public String getTxnToken() {
		return txnToken;
	}

	public void setTxnToken(String txnToken) {
		this.txnToken = txnToken;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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
