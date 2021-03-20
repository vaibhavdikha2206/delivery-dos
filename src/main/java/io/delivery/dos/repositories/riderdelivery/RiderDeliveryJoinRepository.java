package io.delivery.dos.repositories.riderdelivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.delivery.DeliverResponseWithOriginAddress;
import io.delivery.dos.models.delivery.Deliveries;


public interface RiderDeliveryJoinRepository  extends JpaRepository<DeliverResponseWithOriginAddress, Integer>{

	/*@Query(value = "select "
			+ "deliveryid,"
			+ "d.userid,"
			+ "pickuptime,"
			+ "dropaddress,"
			+ "droplatitude,"
			+ "droplongitude,"
			+ "status,"
			+ "riderid,"
			+ "orderid,"
			+ "deliverycharge,"
			+ "d.deliveryaddressid as deliveryaddressid,"
			+ "housenumber as originhousenumber,"
			+ "floor as originfloor,"
			+ "block as originblock,"
			+ "latitude as originlat,"
			+ "longitude as originlong,"
			+ "locality as originlocality,"
			+ "city as origincity"
			+ " from deliveries d LEFT JOIN address a ON  d.deliveryaddressid=a.addressid where d.status = :status",nativeQuery = true)
	public List<Deliveries> getJoinedInfo(@Param("status")String status);*/


	@Query(value = "select d.deliveryid,d.userid,d.pickuptime,d.dropaddress,d.droplatitude,d.droplongitude,d.status,d.riderid,d.orderid,d.deliverycharge,d.description,d.img,d.weightcategory,d.destinationcontact,d.originaddressid as originaddressid,a.housenumber as originhousenumber,a.floor as originfloor,a.block as originblock,a.latitude as originlat,a.longitude as originlong,a.locality as originlocality,a.city as origincity,a.addressid as addressid from Deliveries d LEFT JOIN Address a ON  d.originaddressid=a.addressid where d.status = :status order by deliveryid desc",nativeQuery = true)
	public List<DeliverResponseWithOriginAddress> getJoinedInfo(@Param("status")String status);
	
	@Query(value = "select d.deliveryid,d.userid,d.pickuptime,d.dropaddress,d.droplatitude,d.droplongitude,d.status,d.riderid,d.orderid,d.deliverycharge,d.description,d.img,d.weightcategory,d.destinationcontact,d.originaddressid as originaddressid,a.housenumber as originhousenumber,a.floor as originfloor,a.block as originblock,a.latitude as originlat,a.longitude as originlong,a.locality as originlocality,a.city as origincity,a.addressid as addressid from Deliveries d LEFT JOIN Address a ON  d.originaddressid=a.addressid where d.status = :status1 Or d.status = :status2 and riderid = :riderid order by deliveryid desc",nativeQuery = true)
	public List<DeliverResponseWithOriginAddress> getJoinedInfoForPendingAndOnGoingDeliveries(@Param("riderid")String riderid,@Param("status1")String status1,@Param("status2")String status2);
	
	@Query(value = "select d.deliveryid,d.userid,d.pickuptime,d.dropaddress,d.droplatitude,d.droplongitude,d.status,d.riderid,d.orderid,d.deliverycharge,d.description,d.img,d.weightcategory,d.destinationcontact,d.originaddressid as originaddressid,a.housenumber as originhousenumber,a.floor as originfloor,a.block as originblock,a.latitude as originlat,a.longitude as originlong,a.locality as originlocality,a.city as origincity,a.addressid as addressid from Deliveries d LEFT JOIN Address a ON  d.originaddressid=a.addressid where d.status = :status and deliveryid = :deliveryid order by deliveryid desc",nativeQuery = true)
	DeliverResponseWithOriginAddress findByDeliveryidandStatus(@Param("deliveryid")Integer deliveryid,@Param("status")String status);
	
	@Query(value = "select d.deliveryid,d.userid,d.pickuptime,d.dropaddress,d.droplatitude,d.droplongitude,d.status,d.riderid,d.orderid,d.deliverycharge,d.description,d.img,d.weightcategory,d.destinationcontact,d.originaddressid as originaddressid,a.housenumber as originhousenumber,a.floor as originfloor,a.block as originblock,a.latitude as originlat,a.longitude as originlong,a.locality as originlocality,a.city as origincity,a.addressid as addressid from Deliveries d LEFT JOIN Address a ON  d.originaddressid=a.addressid where d.status = :status and riderid = :riderid order by deliveryid desc",nativeQuery = true)
	public List<DeliverResponseWithOriginAddress> findByRiderid(@Param("riderid")String riderid,@Param("status")String status);
}
