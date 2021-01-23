package io.delivery.dos.models.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Menu  {

	@Id
	@GeneratedValue
	@Column(name = "itemid")
	private int itemid;
	
	@Column(name = "vendorid")
	private int vendorid;

	@Column(name = "image")
	private String image;
	
	@Column(name = "itemcategory")
	private String itemcategory;
	
	@Column(name = "item")
	private String item;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "rating")
	private Float rating;
	
	
	public Menu() {

	}


	public Menu(int itemid, int vendorid, String image, String itemcategory, String item, float amount, Float rating) {
		super();
		this.itemid = itemid;
		this.vendorid = vendorid;
		this.image = image;
		this.itemcategory = itemcategory;
		this.item = item;
		this.amount = amount;
		this.rating = rating;
	}

	
	public int getItemid() {
		return itemid;
	}


	public void setItemid(int itemid) {
		this.itemid = itemid;
	}


	public int getVendorid() {
		return vendorid;
	}


	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getItemcategory() {
		return itemcategory;
	}


	public void setItemcategory(String itemcategory) {
		this.itemcategory = itemcategory;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Float getRating() {
		return rating;
	}


	public void setRating(Float rating) {
		this.rating = rating;
	}
	
}
