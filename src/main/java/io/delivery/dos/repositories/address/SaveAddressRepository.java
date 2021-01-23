package io.delivery.dos.repositories.address;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.address.Address;

public interface SaveAddressRepository extends JpaRepository<Address, Integer> {
	
	List<Address> findByUserid(String userid);
}