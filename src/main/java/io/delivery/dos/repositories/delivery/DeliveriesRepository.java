package io.delivery.dos.repositories.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.delivery.Deliveries;

public interface DeliveriesRepository extends JpaRepository<Deliveries, Integer>{

	@Query(value="select Count(Distinct(riderid)) from Deliveries where pickuptime >= :lowerpickuptime && pickuptime <= :upperpickuptime",nativeQuery = true)
	Integer getNumberOfBusyRiders(@Param("lowerpickuptime")String lowerpickuptime,@Param("upperpickuptime")String upperpickuptime);
	
	Deliveries findOneByDeliveryidAndUserid(int deliveryid,String userid);
	
	@Modifying
	@Query(value="Update deliveries set status = :status where deliveryid = :deliveryid",nativeQuery = true)
	Integer updateDeliveryStatus(@Param("deliveryid")int deliveryid,@Param("status")String status);
}
