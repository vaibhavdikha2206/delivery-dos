package io.delivery.dos.models.maps.request;

import javax.persistence.Column;

public class DistanceRequest {

	private double destinationLat;
	private double destinationLong;
	private int addressid;
	private int weightcategory;
	
	private boolean isDelicate;
	private boolean isBalloonAdded;
	private boolean isBouqetAdded;
	private boolean isTwoCakes;
	
	public DistanceRequest() {}

	public DistanceRequest(double destinationLat, double destinationLong, int addressid, int weightcategory,
			boolean isDelicate, boolean isBalloonAdded, boolean isBouqetAdded, boolean isTwoCakes) {
		super();
		this.destinationLat = destinationLat;
		this.destinationLong = destinationLong;
		this.addressid = addressid;
		this.weightcategory = weightcategory;
		this.isDelicate = isDelicate;
		this.isBalloonAdded = isBalloonAdded;
		this.isBouqetAdded = isBouqetAdded;
		this.isTwoCakes = isTwoCakes;
	}

	public boolean isDelicate() {
		return isDelicate;
	}

	public void setDelicate(boolean isDelicate) {
		this.isDelicate = isDelicate;
	}

	public boolean isBalloonAdded() {
		return isBalloonAdded;
	}

	public void setBalloonAdded(boolean isBalloonAdded) {
		this.isBalloonAdded = isBalloonAdded;
	}

	public boolean isBouqetAdded() {
		return isBouqetAdded;
	}

	public void setBouqetAdded(boolean isBouqetAdded) {
		this.isBouqetAdded = isBouqetAdded;
	}

	public boolean isTwoCakes() {
		return isTwoCakes;
	}

	public void setTwoCakes(boolean isTwoCakes) {
		this.isTwoCakes = isTwoCakes;
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
