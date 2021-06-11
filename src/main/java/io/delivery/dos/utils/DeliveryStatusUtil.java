package io.delivery.dos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.expressdelivery.ExpressDeliveriesRepository;

@Component
public class DeliveryStatusUtil {

	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	public Integer updateDeliveryStatus(int deliveryid,String status) {
		return deliveriesRepository.updateDeliveryStatus(deliveryid, status);
	}
	
	public Integer updateDeliveryStatusForRider(int deliveryid,String status,String riderid) {
		 System.out.println("updating "+deliveryid+","+status+","+riderid);
		return deliveriesRepository.updateDeliveryStatusForRider(deliveryid, status,riderid);
	}
	
	public Integer updateDeliveryPaymentHash(int deliveryid,String razorHash,String razorSignature,String razorPayid) {
		return deliveriesRepository.updateDeliveryPaymentHash(deliveryid, razorHash,razorSignature,razorPayid);
	}
	
	public Integer updateDeliveryStatusForAdmin(int deliveryid,String status) {
		 System.out.println("updating "+deliveryid+","+status);
		return deliveriesRepository.updateDeliveryStatusForAdmin(deliveryid, status);
	}
	

}
