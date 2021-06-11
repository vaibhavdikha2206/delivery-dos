package io.delivery.dos.models.expressdelivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Deliveries")
public class ExpressDeliveries {

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
		
		@Column(name = "orderid")
		private String orderid;
		
		@Column(name = "totaldeliverycharge")
		private int totaldeliverycharge;
		
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

		@Column(name = "riderid")
		private String riderid;
		
		@Column(name = "creditsused")
		private int creditsused;
		
		@Column(name = "deliverycharge")
		private int deliverycharge;
		
		@Column(name = "paymentMethod")
		private String paymentMethod;
		
		@Column(name = "paytmTxnToken")
		private String paytmTxnToken;
		
		
		
		public ExpressDeliveries() {}


		

		public ExpressDeliveries(Integer deliveryid, String userid, String pickuptime, int originaddressid,
				String dropaddress, Double droplatitude, Double droplongitude, String status, String orderid,
				int totaldeliverycharge, String description, String img, Integer weightcategory,
				String destinationcontact, boolean isDelicate, String riderid, int creditsused, int deliverycharge,
				String paymentMethod, String paytmTxnToken) {
			super();
			this.deliveryid = deliveryid;
			this.userid = userid;
			this.pickuptime = pickuptime;
			this.originaddressid = originaddressid;
			this.dropaddress = dropaddress;
			this.droplatitude = droplatitude;
			this.droplongitude = droplongitude;
			this.status = status;
			this.orderid = orderid;
			this.totaldeliverycharge = totaldeliverycharge;
			this.description = description;
			this.img = img;
			this.weightcategory = weightcategory;
			this.destinationcontact = destinationcontact;
			this.isDelicate = isDelicate;
			this.riderid = riderid;
			this.creditsused = creditsused;
			this.deliverycharge = deliverycharge;
			this.paymentMethod = paymentMethod;
			this.paytmTxnToken = paytmTxnToken;
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



		public String getOrderid() {
			return orderid;
		}



		public void setOrderid(String orderid) {
			this.orderid = orderid;
		}



		public int getTotaldeliverycharge() {
			return totaldeliverycharge;
		}



		public void setTotaldeliverycharge(int totaldeliverycharge) {
			this.totaldeliverycharge = totaldeliverycharge;
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



		public Integer getWeightcategory() {
			return weightcategory;
		}



		public void setWeightcategory(Integer weightcategory) {
			this.weightcategory = weightcategory;
		}



		public String getDestinationcontact() {
			return destinationcontact;
		}



		public void setDestinationcontact(String destinationcontact) {
			this.destinationcontact = destinationcontact;
		}



		public boolean isDelicate() {
			return isDelicate;
		}



		public void setDelicate(boolean isDelicate) {
			this.isDelicate = isDelicate;
		}



		public String getRiderid() {
			return riderid;
		}



		public void setRiderid(String riderid) {
			this.riderid = riderid;
		}



		public int getCreditsused() {
			return creditsused;
		}



		public void setCreditsused(int creditsused) {
			this.creditsused = creditsused;
		}



		public int getDeliverycharge() {
			return deliverycharge;
		}



		public void setDeliverycharge(int deliverycharge) {
			this.deliverycharge = deliverycharge;
		}



		public String getPaymentMethod() {
			return paymentMethod;
		}



		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}



		public String getPaytmTxnToken() {
			return paytmTxnToken;
		}



		public void setPaytmTxnToken(String paytmTxnToken) {
			this.paytmTxnToken = paytmTxnToken;
		}
		
		
		
		
}
