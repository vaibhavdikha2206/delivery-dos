package io.delivery.dos.models.maps.response;

public class DistanceResponseWithAmount {

	private double amount ;

	public DistanceResponseWithAmount() {}
	
	public DistanceResponseWithAmount(double amount) {
		super();
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
