package io.delivery.dos.models.status;

public class StatusUpdateResponseObject {

	private String updatedStatus ;

	public StatusUpdateResponseObject() {}
	
	public StatusUpdateResponseObject(String updatedStatus) {
		super();
		this.updatedStatus = updatedStatus;
	}

	public String getUpdatedStatus() {
		return updatedStatus;
	}

	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	
}
