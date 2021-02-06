package io.delivery.dos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.delivery.dos.models.address.Address;
import io.delivery.dos.repositories.address.AddressRepository;

@Component
public class AddressUtil {

	@Autowired
	AddressRepository addressRepository;
	
	public Address checkIfAddressCorrespondsToUser(String userid,int addressid) {
		return addressRepository.findOneByUseridAndAddressid(userid, addressid);
	}
}
