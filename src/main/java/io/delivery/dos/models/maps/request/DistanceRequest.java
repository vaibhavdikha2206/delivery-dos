package io.delivery.dos.models.maps.request;

public class DistanceRequest {

	private double originLat;
	private double originLong;
	private double destinationLat;
	private double destinationLong;
	
	public DistanceRequest() {}

	public DistanceRequest(double originLat, double originLong, double destinationLat, double destinationLong) {
		super();
		this.originLat = originLat;
		this.originLong = originLong;
		this.destinationLat = destinationLat;
		this.destinationLong = destinationLong;
	}

	public double getOriginLat() {
		return originLat;
	}

	public void setOriginLat(double originLat) {
		this.originLat = originLat;
	}

	public double getOriginLong() {
		return originLong;
	}

	public void setOriginLong(double originLong) {
		this.originLong = originLong;
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
	
}
