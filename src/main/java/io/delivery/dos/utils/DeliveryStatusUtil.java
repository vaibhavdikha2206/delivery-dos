package io.delivery.dos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.delivery.dos.repositories.delivery.DeliveriesRepository;

@Component
public class DeliveryStatusUtil {

	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	public Integer updateDeliveryStatus(int deliveryid,String status) {
		return deliveriesRepository.updateDeliveryStatus(deliveryid, status);
	}
	
	public Integer updateDeliveryPaymentHash(int deliveryid,String razorHash,String razorSignature,String razorPayid) {
		return deliveriesRepository.updateDeliveryPaymentHash(deliveryid, razorHash,razorSignature,razorPayid);
	}
	
}
