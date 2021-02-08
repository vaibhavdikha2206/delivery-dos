package io.delivery.dos.riderDeliveryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.riderdeliverylist.RiderPendingDeliveriesResponse;
import io.delivery.dos.models.riderdeliverylist.RiderPendingDeliveryResponse;
import io.delivery.dos.repositories.riderdelivery.RiderDeliveryRepository;
import io.delivery.dos.security.util.JwtUtil;

@RestController
public class DetailListController {

	@Autowired
	RiderDeliveryRepository riderDeliveryRepository;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@RequestMapping(method=RequestMethod.GET,value="/getPendingDeliveries")
	public RiderPendingDeliveriesResponse getPendingDeliveryList(@RequestHeader (name="Authorization") String authorizationHeader) throws Exception {
		String jwt = authorizationHeader.substring(7);
        checkRider(jwt);
        List<Deliveries> pendingDeliveries = riderDeliveryRepository.findPendingDeliveries();
        return new RiderPendingDeliveriesResponse(pendingDeliveries);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getPendingDelivery/{deliveryid}")
	public RiderPendingDeliveryResponse getPendingDelivery(@RequestHeader (name="Authorization") String authorizationHeader,@PathVariable("deliveryid") Integer deliveryid) throws Exception {
		String jwt = authorizationHeader.substring(7);
        checkRider(jwt);
        Deliveries pendingDelivery= riderDeliveryRepository.findByDeliveryidAndRideridIsNull(deliveryid);
        return new RiderPendingDeliveryResponse(pendingDelivery);
	}
	
	private void checkRider(String jwt) throws Exception {
		 String role = jwtUtil.extractRole(jwt);
		 System.out.println("Role is "+role);
		 if(role.equals("RIDER")) return ;
		 throw new Exception("Not Authorized");
	}
}
