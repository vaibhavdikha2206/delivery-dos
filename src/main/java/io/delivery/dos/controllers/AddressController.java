package io.delivery.dos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.address.request.AddressRequestObject;
import io.delivery.dos.models.address.response.AddressResponseObject;
import io.delivery.dos.models.user.Profile;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.repositories.address.AddressRepository;
import io.delivery.dos.repositories.user.ProfileRepository;


@RestController
public class AddressController {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@RequestMapping(method=RequestMethod.POST,value="/getAddress")
	public AddressResponseObject getAddress(@RequestBody Profile profileObj) {
		if(getCustom(profileObj)==true) {
		List<Address> address = addressRepository.findByUserid(profileObj.getUserid());
		AddressResponseObject responseAddress = new AddressResponseObject(address,"1");
		return responseAddress;
		}
		
		return new AddressResponseObject(null,"0");
	}
	

	@RequestMapping(method=RequestMethod.POST,value="/addAddress")
	public Address addAddress(@RequestBody AddressRequestObject addressRequestObject) {
		if(getCustom(addressRequestObject.getProfileObject())==true) {
			Address x = new Address(null,addressRequestObject.getUserid(),addressRequestObject.getAddress(),addressRequestObject.getDefaultbit());
			return addressRepository.save(x);
		}
		
		return null;
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
