package io.delivery.dos.models.user.response;


public class UserCreditsResponse {

	
	private float credits;
	private String referralCode;
	
	
	public UserCreditsResponse() {

	}

	public UserCreditsResponse(float credits, String referralCode) {
		super();
		this.credits = credits;
		this.referralCode = referralCode;
	}


	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public float getCredits() {
		return credits;
	}


	public void setCredits(float credits) {
		this.credits = credits;
	}

	
}
