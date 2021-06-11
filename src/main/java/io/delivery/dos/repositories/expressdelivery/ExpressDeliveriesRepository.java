package io.delivery.dos.repositories.expressdelivery;

import org.springframework.data.jpa.repository.JpaRepository;

import io.delivery.dos.models.expressdelivery.ExpressDeliveries;

public interface ExpressDeliveriesRepository extends JpaRepository<ExpressDeliveries, Integer>{
	
	ExpressDeliveries findOneByDeliveryidAndUseridAndOrderid(int deliveryid,String userid,String orderid);
	
}