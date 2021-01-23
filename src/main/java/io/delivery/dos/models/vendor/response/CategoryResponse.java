package io.delivery.dos.models.vendor.response;

import java.util.List;

import io.delivery.dos.models.vendor.Category;

public class CategoryResponse {

	private List<Category> categoryData=null;
	private String status = "0";
	
	
	public CategoryResponse(List<Category> categoryData, String status) {
		super();
		this.categoryData = categoryData;
		this.status = status;
	}
	
	public List<Category> getCategoryData() {
		return categoryData;
	}
	public void setCategoryData(List<Category> categoryData) {
		this.categoryData = categoryData;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
