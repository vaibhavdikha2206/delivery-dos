package io.delivery.dos.models.delivery.admin;

public class AssignRiderResponse {

	private int riderAssignStatus ;

	public AssignRiderResponse(int riderAssignStatus) {
		super();
		this.riderAssignStatus = riderAssignStatus;
	}

	public int getRiderAssignStatus() {
		return riderAssignStatus;
	}

	public void setRiderAssignStatus(int riderAssignStatus) {
		this.riderAssignStatus = riderAssignStatus;
	}
	
}
