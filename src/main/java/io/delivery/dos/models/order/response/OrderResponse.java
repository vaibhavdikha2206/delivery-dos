package io.delivery.dos.models.order.response;

public class OrderResponse {

	private String status ;
	private int orderid;
	public OrderResponse(String status, int orderid) {
		super();
		this.status = status;
		this.orderid = orderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	
}
