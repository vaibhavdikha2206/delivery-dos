package io.delivery.dos.repositories.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.delivery.dos.models.delivery.DeliverResponseWithOriginAddress;

public interface AdminDeliveryRepositoryWithAddress  extends JpaRepository<DeliverResponseWithOriginAddress, Integer>{

	@Query(value = "select d.deliveryid,d.userid,p.username,d.pickuptime,d.dropaddress,d.droplatitude,d.droplongitude,d.status,d.riderid,d.orderid,d.deliverycharge,d.description,d.img,d.weightcategory,d.destinationcontact,d.isDelicate,d.isBalloonAdded,d.isBouqetAdded,d.isTwoCakes,d.originaddressid as originaddressid,a.housenumber as originhousenumber,a.floor as originfloor,a.block as originblock,a.latitude as originlat,a.longitude as originlong,a.locality as originlocality,a.city as origincity,a.addressid as addressid from Deliveries d LEFT JOIN Address a ON  d.originaddressid=a.addressid LEFT JOIN profile p on p.userid=d.userid where d.status = :status order by deliveryid desc",nativeQuery = true)
	public List<DeliverResponseWithOriginAddress> getJoinedInfo(@Param("status")String status);
	
}