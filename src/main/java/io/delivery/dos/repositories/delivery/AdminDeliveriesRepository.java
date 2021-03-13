package io.delivery.dos.repositories.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.delivery.Deliveries;

public interface AdminDeliveriesRepository extends JpaRepository<Deliveries, Integer>{

	@Query(value="select * from Deliveries where status = :status order by abs(TIMESTAMPDIFF(SECOND, now() , pickuptime)) asc",nativeQuery = true)
	List<Deliveries> findByStatusOrderbypickuptime(@Param("status")String status);
	
	//gets you entire schedule at specified pickuptime
	@Query(value="select * from Deliveries where pickuptime = :pickuptime And riderid is not null order by deliveryid desc",nativeQuery = true)
	List<Deliveries> findByPickuptimeWhereRideridIsNotnull(@Param("pickuptime")String pickuptime);
}
