package io.delivery.dos.repositories.rider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.rider.Riderdata;


public interface RiderRepository extends JpaRepository<Riderdata, Integer>{
	
	List<Riderdata> findByRideridNotIn(List<Integer> ids);
}
