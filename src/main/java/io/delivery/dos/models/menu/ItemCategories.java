package io.delivery.dos.models.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class ItemCategories {


	@Id
	@Column(name = "itemcategory")
	private String itemcategory;
	
	
	public ItemCategories() {

	}


	public ItemCategories(String itemcategory) {
		super();
		this.itemcategory = itemcategory;
	}


	public String getItemcategory() {
		return itemcategory;
	}


	public void setItemcategory(String itemcategory) {
		this.itemcategory = itemcategory;
	}


	
}
