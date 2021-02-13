package io.delivery.dos.repositories.address;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.address.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	List<Address> findByUserid(String userid);
	Address findOneByUseridAndAddressid(String userid,int addressid);
	Address findOneByAddressid(int addressid);
	
}