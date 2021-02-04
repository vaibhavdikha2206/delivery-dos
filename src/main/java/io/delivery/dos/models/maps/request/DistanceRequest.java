package io.delivery.dos.models.maps.request;

public class DistanceRequest {

	private double destinationLat;
	private double destinationLong;
	private int addressid;
	
	public DistanceRequest() {}

	public DistanceRequest(double destinationLat, double destinationLong,int addressid) {
		super();
		this.destinationLat = destinationLat;
		this.destinationLong = destinationLong;
		this.addressid = addressid;
	}

	public double getDestinationLat() {
		return destinationLat;
	}

	public void setDestinationLat(double destinationLat) {
		this.destinationLat = destinationLat;
	}

	public double getDestinationLong() {
		return destinationLong;
	}

	public void setDestinationLong(double destinationLong) {
		this.destinationLong = destinationLong;
	}

	public int getAddressid() {
		return addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	
	
}
