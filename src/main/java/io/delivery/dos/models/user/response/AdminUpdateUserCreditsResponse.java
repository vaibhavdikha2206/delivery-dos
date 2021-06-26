package io.delivery.dos.models.user.response;


public class AdminUpdateUserCreditsResponse {

	private Integer updateStatus ;
	
	public AdminUpdateUserCreditsResponse() {
	
	}

	public AdminUpdateUserCreditsResponse(Integer updateStatus) {
		super();
		this.updateStatus = updateStatus;
	}

	public Integer getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}
	
	
}
