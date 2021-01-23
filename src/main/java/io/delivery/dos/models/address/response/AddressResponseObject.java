package io.delivery.dos.models.address.response;

import java.util.List;

import io.delivery.dos.models.address.Address;

public class AddressResponseObject {

	private List<Address> addressList=null;
	private String status = "0";
	
	public AddressResponseObject() {
		
	}
	
	public AddressResponseObject(List<Address> addressList, String status) {
		super();
		this.addressList = addressList;
		this.status = status;
	}
	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
