package io.delivery.dos.repositories.riderdelivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.delivery.Deliveries;

public interface RiderDeliveryRepository extends JpaRepository<Deliveries, Integer>{

	@Query(value="select * from Deliveries where riderid is null  order by pickuptime Desc",nativeQuery = true)
	List<Deliveries> findPendingDeliveries();
	
	Deliveries findByDeliveryidAndRideridIsNull(Integer deliveryid);
	
	List<Deliveries> findByRiderid(String riderid);
	
	@Modifying
	@Query(value="Update Deliveries set status = :status,riderid= :riderid where deliveryid = :deliveryid",nativeQuery = true)
	Integer updateDeliveryRiderIdAndDeliveryStatus(@Param("deliveryid")int deliveryid,@Param("status")String status,@Param("riderid")String riderid);
}
