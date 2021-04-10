package io.delivery.dos.models.paytm;

public class Body {

	private ResultInfo resultInfo;
	private String txnToken;
	private Boolean isPromoCodeValid;
	private Boolean authenticated;

	public ResultInfo getResultInfo() {
	return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
	this.resultInfo = resultInfo;
	}

	public String getTxnToken() {
	return txnToken;
	}

	public void setTxnToken(String txnToken) {
	this.txnToken = txnToken;
	}

	public Boolean getIsPromoCodeValid() {
	return isPromoCodeValid;
	}

	public void setIsPromoCodeValid(Boolean isPromoCodeValid) {
	this.isPromoCodeValid = isPromoCodeValid;
	}

	public Boolean getAuthenticated() {
	return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
	this.authenticated = authenticated;
	}
	
}
