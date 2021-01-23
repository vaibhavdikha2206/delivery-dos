package io.delivery.dos.repositories.address;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.menu.ItemCategories;
import io.delivery.dos.models.menu.Menu;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	List<Address> findByUserid(String userid);
}