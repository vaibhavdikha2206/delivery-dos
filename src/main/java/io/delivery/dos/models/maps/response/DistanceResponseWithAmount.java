package io.delivery.dos.models.maps.response;

public class DistanceResponseWithAmount {

	private long amount ;

	public DistanceResponseWithAmount() {}
	
	public DistanceResponseWithAmount(long amount) {
		super();
		this.amount = amount;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
}
