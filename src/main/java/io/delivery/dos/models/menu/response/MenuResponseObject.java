package io.delivery.dos.models.menu.response;

import java.util.List;

import io.delivery.dos.models.menu.ItemCategories;
import io.delivery.dos.models.menu.Menu;
import io.delivery.dos.models.user.response.ProfileResponse;

public class MenuResponseObject {

	private List<Menu> menu=null;
	private List<ItemCategories> itemCategories=null;
	private String status = "0";
	
	public MenuResponseObject() {
		
	}
	
	public MenuResponseObject(List<Menu> menu, List<ItemCategories> itemCategories, String status) {
		super();
		this.menu = menu;
		this.itemCategories = itemCategories;
		this.status = status;
	}
	
	public  MenuResponseObject(Object object, Object object2, String string) {
		super();
		this.menu = null;
		this.itemCategories = null;
		this.status = status;
	}
	
	public List<Menu> getMenu() {
		return menu;
	}
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	public List<ItemCategories> getItemCategories() {
		return itemCategories;
	}
	public void setItemCategories(List<ItemCategories> itemCategories) {
		this.itemCategories = itemCategories;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
