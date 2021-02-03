package io.delivery.dos.models.maps.helper;

public class BaseValue {

	private String text;
	private Double value;
	
	public BaseValue() {}
	
	public BaseValue(String text, Double value) {
		super();
		this.text = text;
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
