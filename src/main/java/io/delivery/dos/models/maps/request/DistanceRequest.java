package io.delivery.dos.models.maps.request;

public class DistanceRequest {

	private double destinationLat;
	private double destinationLong;
	private int addressid;
	private int weightcategory;
	
	public DistanceRequest() {}

	
	public DistanceRequest(double destinationLat, double destinationLong, int addressid, int weightcategory) {
		super();
		this.destinationLat = destinationLat;
		this.destinationLong = destinationLong;
		this.addressid = addressid;
		this.weightcategory = weightcategory;
	}


	public int getWeightcategory() {
		return weightcategory;
	}


	public void setWeightcategory(int weightcategory) {
		this.weightcategory = weightcategory;
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
