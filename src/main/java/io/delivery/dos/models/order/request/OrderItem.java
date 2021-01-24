package io.delivery.dos.models.order.request;

public class OrderItem {

	private int itemid;
	private int qty;
	
	public OrderItem() {
		
	}
	
	public OrderItem(int itemid, int qty) {
		super();
		this.itemid = itemid;
		this.qty = qty;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		return "{ [itemid=" + getItemid() + ", qty=" + getQty() + "] }";
	}
}
