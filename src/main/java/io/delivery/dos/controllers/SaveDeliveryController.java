package io.delivery.dos.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.delivery.availability.AvailabilityRequest;
import io.delivery.dos.models.delivery.availability.AvailabilityResponse;
import io.delivery.dos.models.delivery.savedelivery.SaveDeliveryRequestObject;
import io.delivery.dos.models.delivery.savedelivery.SaveDeliveryResponse;
import io.delivery.dos.models.razorpay.GeneratedOrder;
import io.delivery.dos.models.razorpay.RazorPayNotes;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.utils.MapsUtil;
import io.delivery.dos.utils.RazorPayUtil;

@RestController
public class SaveDeliveryController {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private RazorPayUtil razorPayUtil;
	
	@RequestMapping(method=RequestMethod.POST,value="/deliveryRequest")
	public SaveDeliveryResponse deliveryRequest(@RequestBody SaveDeliveryRequestObject saveDeliveryRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws RazorpayException, JSONException { 
	
		// first save it in db with status as Payment_Awaiting
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        
        GeneratedOrder generatedOrder = razorPayUtil.generateOrderId(100,"reciept",2,new RazorPayNotes("key1","key2"));
        
		Deliveries recvdDelivery = new Deliveries(null,userid,saveDeliveryRequestObject.getDeliveryObject().getPickuptime(),
				saveDeliveryRequestObject.getDeliveryObject().getAddressid(),saveDeliveryRequestObject.getDeliveryObject().getDropaddress(),
				saveDeliveryRequestObject.getDeliveryObject().getDroplatitude(),saveDeliveryRequestObject.getDeliveryObject().getDroplongitude(),
				Constants.status_PAYMENT_AWAITING,null,generatedOrder.getID());
		
		Deliveries savedDelivery=deliveriesRepository.save(recvdDelivery);
		
		//after save success get order id and send object		
		
		return new SaveDeliveryResponse(generatedOrder.getAmount(),generatedOrder.getID(),savedDelivery.getDeliveryid()) ; 
	}
	
	
}
