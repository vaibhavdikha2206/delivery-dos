package io.delivery.dos.repositories.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.delivery.Deliveries;

public interface DeliveriesRepository extends JpaRepository<Deliveries, Integer>{

	@Query(value="select * from Deliveries where userid = :userid  order by pickuptime Desc",nativeQuery = true)
	List<Deliveries> findByUseridOrderbypickuptime(@Param("userid")String userid);
	
	Deliveries findByUseridAndDeliveryid(String userid,int deliveryid);
	
	@Query(value="select Count(Distinct(riderid)) from Deliveries where pickuptime >= :lowerpickuptime && pickuptime <= :upperpickuptime",nativeQuery = true)
	Integer getNumberOfBusyRiders(@Param("lowerpickuptime") String lowerpickuptime,@Param("upperpickuptime") String upperpickuptime);
	
	@Query(value="select Distinct(riderid) from Deliveries where pickuptime >= :lowerpickuptime && pickuptime <= :upperpickuptime  && riderid is not null",nativeQuery = true)
	List<String> getBusyRiders(@Param("lowerpickuptime")String lowerpickuptime,@Param("upperpickuptime")String upperpickuptime);
	
	Deliveries findOneByDeliveryidAndUseridAndOrderid(int deliveryid,String userid,String orderid);
	
	@Modifying
	@Query(value="Update Deliveries set status = :status where deliveryid = :deliveryid",nativeQuery = true)
	Integer updateDeliveryStatus(@Param("deliveryid")int deliveryid,@Param("status")String status);
	
	@Modifying
	@Query(value="Update Deliveries set razorhash = :razorhash,razorsignature= :razorsignature,razorpayid = :razorpayid where deliveryid = :deliveryid",nativeQuery = true)
	Integer updateDeliveryPaymentHash(@Param("deliveryid")int deliveryid,@Param("razorhash")String razorhash,@Param("razorsignature")String razorsignature,@Param("razorpayid")String razorpayid);
}
