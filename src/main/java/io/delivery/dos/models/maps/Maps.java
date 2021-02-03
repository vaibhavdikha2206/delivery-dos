package io.delivery.dos.models.maps;

import java.util.ArrayList;


import io.delivery.dos.models.maps.helper.ElementsArray;

public class Maps {

	private ArrayList<String> destination_addresses;
	private ArrayList<String> origin_addresses;
	private ArrayList<ElementsArray> rows;
	private String status;
	
	public Maps() {}
	
	public Maps(ArrayList<String> destination_addresses, ArrayList<String> origin_addresses,
			ArrayList<ElementsArray> rows, String status) {
		super();
		this.destination_addresses = destination_addresses;
		this.origin_addresses = origin_addresses;
		this.rows = rows;
		this.status = status;
	}

	public ArrayList<String> getDestination_addresses() {
		return destination_addresses;
	}

	public void setDestination_addresses(ArrayList<String> destination_addresses) {
		this.destination_addresses = destination_addresses;
	}

	public ArrayList<String> getOrigin_addresses() {
		return origin_addresses;
	}

	public void setOrigin_addresses(ArrayList<String> origin_addresses) {
		this.origin_addresses = origin_addresses;
	}

	public ArrayList<ElementsArray> getRows() {
		return rows;
	}

	public void setRows(ArrayList<ElementsArray> rows) {
		this.rows = rows;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
}
