package io.delivery.dos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.menu.ItemCategories;
import io.delivery.dos.models.menu.Menu;
import io.delivery.dos.models.menu.response.MenuResponseObject;
import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.repositories.menu.ItemCategoryRepository;
import io.delivery.dos.repositories.menu.MenuRepository;
import io.delivery.dos.repositories.user.ProfileRepository;


@RestController
public class MenuController {

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	ItemCategoryRepository itemCategoryRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@RequestMapping(method=RequestMethod.POST,value="/menu/{vendorid}")
	public MenuResponseObject getMenu(@RequestBody Profile profileObj,@PathVariable("vendorid") int vendorid) {
		System.out.println(profileObj.getPassword());
		if(getCustom(profileObj)==true) {
		List<Menu> menu = menuRepository.findByVendorid(vendorid);
		List<ItemCategories> itemCategories = itemCategoryRepository.findDistinctItemcategoryByVendorid(vendorid);
		MenuResponseObject responseMenu = new MenuResponseObject(menu,itemCategories,"1");
		return responseMenu;
		}
		else return new MenuResponseObject(null,null,"0");
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
}
