package io.delivery.dos.models.user.request;

import java.util.List;

public class AdminUpdateUserCreditsRequest {

	private List<String> listOfUsers;

	
	public AdminUpdateUserCreditsRequest(List<String> listOfUsers) {
		super();
		this.listOfUsers = listOfUsers;
	}

	public AdminUpdateUserCreditsRequest() {
		
	}


	public List<String> getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(List<String> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}
	
	
}
