package io.delivery.dos.models.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Deliveries {

	@Id
	@GeneratedValue
	@Column(name = "deliveryid")
	private Integer deliveryid;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "pickuptime")
	private String pickuptime;
	
	@Column(name = "originaddressid")
	private int originaddressid;
	
	@Column(name = "dropaddress")
	private String dropaddress;
	
	@Column(name = "droplatitude")
	private Double droplatitude;

	@Column(name = "droplongitude")
	private Double droplongitude;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "riderid")
	private String riderid;

	@Column(name = "orderid")
	private String orderid;

	@Column(name = "deliverycharge")
	private int deliverycharge;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "weightcategory")
	private Integer weightcategory;
	
	@Column(name = "destinationcontact")
	private String destinationcontact;
	
	@JsonProperty
	@Column(name = "isDelicate")
	private boolean isDelicate;

	@JsonProperty
	@Column(name = "isBalloonAdded")
	private boolean isBalloonAdded;

	@JsonProperty
	@Column(name = "isBouqetAdded")
	private boolean isBouqetAdded;
	
	@JsonProperty
	@Column(name = "isTwoCakes")
	private boolean isTwoCakes;
	
	@Column(name = "paymentMethod")
	private String paymentMethod;
	
	@Column(name = "paytmTxnToken")
	private String paytmTxnToken;
	
	@Column(name = "totaldeliverycharge")
	private int totaldeliverycharge;
	
	@Column(name = "creditsused")
	private int creditsused;
	
	@Column(name = "locationcode")
	private int locationcode;
	
	public Deliveries() {}
	
	
	public Deliveries(Integer deliveryid, String userid, String pickuptime, int originaddressid, String dropaddress,
			Double droplatitude, Double droplongitude, String status, String riderid, String orderid,
			int deliverycharge, String description, String img, Integer weightcategory, String destinationcontact,
			boolean isDelicate, boolean isBalloonAdded, boolean isBouqetAdded, boolean isTwoCakes, String paymentMethod,
			String paytmTxnToken, int totaldeliverycharge, int creditsused,int locationcode) {
		super();
		this.deliveryid = deliveryid;
		this.userid = userid;
		this.pickuptime = pickuptime;
		this.originaddressid = originaddressid;
		this.dropaddress = dropaddress;
		this.droplatitude = droplatitude;
		this.droplongitude = droplongitude;
		this.status = status;
		this.riderid = riderid;
		this.orderid = orderid;
		this.deliverycharge = deliverycharge;
		this.description = description;
		this.img = img;
		this.weightcategory = weightcategory;
		this.destinationcontact = destinationcontact;
		this.isDelicate = isDelicate;
		this.isBalloonAdded = isBalloonAdded;
		this.isBouqetAdded = isBouqetAdded;
		this.isTwoCakes = isTwoCakes;
		this.paymentMethod = paymentMethod;
		this.paytmTxnToken = paytmTxnToken;
		this.totaldeliverycharge = totaldeliverycharge;
		this.creditsused = creditsused;
		this.locationcode = locationcode;
	}


	public int getLocationcode() {
		return locationcode;
	}


	public void setLocationcode(int locationcode) {
		this.locationcode = locationcode;
	}


	public int getTotaldeliverycharge() {
		return totaldeliverycharge;
	}

	public void setTotaldeliverycharge(int totaldeliverycharge) {
		this.totaldeliverycharge = totaldeliverycharge;
	}

	public int getCreditsused() {
		return creditsused;
	}

	public void setCreditsused(int creditsused) {
		this.creditsused = creditsused;
	}

	public String getPaytmTxnToken() {
		return paytmTxnToken;
	}

	public void setPaytmTxnToken(String paytmTxnToken) {
		this.paytmTxnToken = paytmTxnToken;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean getIsDelicate() {
		return isDelicate;
	}

	public void setIsDelicate(boolean isDelicate) {
		this.isDelicate = isDelicate;
	}

	public boolean getIsBalloonAdded() {
		return isBalloonAdded;
	}

	public void setIsBalloonAdded(boolean isBalloonAdded) {
		this.isBalloonAdded = isBalloonAdded;
	}

	public boolean getIsBouqetAdded() {
		return isBouqetAdded;
	}

	public void setIsBouqetAdded(boolean isBouqetAdded) {
		this.isBouqetAdded = isBouqetAdded;
	}

	public boolean getIsTwoCakes() {
		return isTwoCakes;
	}

	public void setIsTwoCakes(boolean isTwoCakes) {
		this.isTwoCakes = isTwoCakes;
	}

	public String getDestinationcontact() {
		return destinationcontact;
	}
	
	public void setDestinationcontact(String destinationcontact) {
		this.destinationcontact = destinationcontact;
	}

	public Integer getWeightcategory() {
		if(weightcategory==null) {
			return 2;
		}
		return weightcategory;
	}

	public void setWeightcategory(Integer weightcategory) {
		this.weightcategory = weightcategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	public int getDeliverycharge() {
		return deliverycharge;
	}

	public void setDeliverycharge(int deliverycharge) {
		this.deliverycharge = deliverycharge;
	}
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	public Integer getDeliveryid() {
		return deliveryid;
	}

	public void setDeliveryid(Integer deliveryid) {
		this.deliveryid = deliveryid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}

	public int getOriginaddressid() {
		return originaddressid;
	}

	public void setOriginaddressid(int originaddressid) {
		this.originaddressid = originaddressid;
	}

	public String getDropaddress() {
		return dropaddress;
	}

	public void setDropaddress(String dropaddress) {
		this.dropaddress = dropaddress;
	}

	public Double getDroplatitude() {
		return droplatitude;
	}

	public void setDroplatitude(Double droplatitude) {
		this.droplatitude = droplatitude;
	}

	public Double getDroplongitude() {
		return droplongitude;
	}

	public void setDroplongitude(Double droplongitude) {
		this.droplongitude = droplongitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRiderid() {
		return riderid;
	}

	public void setRiderid(String riderid) {
		this.riderid = riderid;
	}
		
}
