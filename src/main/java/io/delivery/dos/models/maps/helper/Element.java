package io.delivery.dos.models.maps.helper;

public class Element {

	private BaseValue distance;
	private BaseValue duration;
	
	public Element() {}

	public Element(BaseValue distance, BaseValue duration) {
		super();
		this.distance = distance;
		this.duration = duration;
	}

	public BaseValue getDistance() {
		return distance;
	}

	public void setDistance(BaseValue distance) {
		this.distance = distance;
	}

	public BaseValue getDuration() {
		return duration;
	}

	public void setDuration(BaseValue duration) {
		this.duration = duration;
	}
	
	
}
