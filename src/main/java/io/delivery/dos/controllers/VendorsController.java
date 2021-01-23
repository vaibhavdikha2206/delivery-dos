package io.delivery.dos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.response.LoginResponseObject;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.models.vendor.Category;
import io.delivery.dos.models.vendor.Vendors;
import io.delivery.dos.models.vendor.response.CategoryResponse;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.repositories.vendors.CategoriesRepository;
import io.delivery.dos.repositories.vendors.VendorsRepository;

@RestController
public class VendorsController {

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	VendorsRepository vendorsRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@RequestMapping(method=RequestMethod.POST,value="/vendors/{category}/{page}")
	public Page<Vendors> getVendors(@RequestBody Profile profileObj,@PathVariable("category") String category,@PathVariable("page") Integer page) {
		System.out.println(profileObj.getPassword());
		if(getCustom(profileObj)==true) {
		Pageable pageable = new PageRequest(page, 2);
		Page<Vendors> responseVendors = vendorsRepository.findByCategory(category,pageable);
		return responseVendors;
		}
		else return null;
	}
	
	public Boolean getCustom(Profile profileObj) {
		ProfileResponse responseProfile = profileRepository.findUserProfile(profileObj.getUserid(),profileObj.getPassword());
		if(responseProfile==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/categories")
	public CategoryResponse getCategories() {
		CategoryResponse response = new CategoryResponse(categoriesRepository.findAll(),"1");
		return response;
	}
	
}