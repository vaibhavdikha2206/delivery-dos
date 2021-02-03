package io.delivery.dos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import io.delivery.dos.security.util.JwtUtil;


@RestController
public class AddressController {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
    private JwtUtil jwtUtil;
	 
	@RequestMapping(method=RequestMethod.GET,value="/getAddress")
	public AddressResponseObject getAddress(@RequestHeader (name="Authorization") String authorizationHeader) {
		String jwt = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(jwt);
		List<Address> address = addressRepository.findByUserid(jwtUtil.extractUsername(jwt));
		AddressResponseObject responseAddress = new AddressResponseObject(address,"1");
		return responseAddress;
		
	}
	

	@RequestMapping(method=RequestMethod.POST,value="/addAddress")
	public Address addAddress(@RequestBody Address addressRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) {
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
		Address x = new Address(null,userid,
				addressRequestObject.getDefaultbit(),addressRequestObject.getHousenumber(),
				addressRequestObject.getFloor(),addressRequestObject.getBlock(),
				addressRequestObject.getLatitude(),addressRequestObject.getLongitude(),
				addressRequestObject.getLocality(),addressRequestObject.getCity(),
				addressRequestObject.getPlaceid());
		
		return addressRepository.save(x);
	}
	

}
