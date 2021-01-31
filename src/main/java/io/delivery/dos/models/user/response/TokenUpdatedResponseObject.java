package io.delivery.dos.models.user.response;

public class TokenUpdatedResponseObject {

	private Integer updateStatus = 0;
	private String status = "Error";
	public Integer getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TokenUpdatedResponseObject(Integer updateStatus, String status) {
		super();
		this.updateStatus = updateStatus;
		this.status = status;
	}
	
	public TokenUpdatedResponseObject() {}
	
}
