package io.delivery.dos.repositories.rider;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.rider.Riderdata;


public interface RiderRepository extends JpaRepository<Riderdata, Integer>{
	
}
