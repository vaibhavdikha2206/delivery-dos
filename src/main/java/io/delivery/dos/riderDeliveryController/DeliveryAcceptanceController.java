package io.delivery.dos.riderDeliveryController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.delivery.DeliverResponseWithOriginAddress;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.riderdeliveryaccept.RiderDeliveryAcceptRequest;
import io.delivery.dos.models.riderdeliveryaccept.RiderDeliveryAcceptResponse;
import io.delivery.dos.models.riderdeliverylist.RiderPendingDeliveriesResponse;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.riderdelivery.RiderDeliveryJoinRepository;
import io.delivery.dos.repositories.riderdelivery.RiderDeliveryRepository;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.service.fcm.model.Note;
import io.delivery.dos.utils.NotifUtil;

@RestController
public class DeliveryAcceptanceController {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	RiderDeliveryRepository riderDeliveryRepository;
	
	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	RiderDeliveryJoinRepository riderDeliveryJoinRepository;
	
	@Autowired
	private NotifUtil notifUtil;
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST,value="/acceptDelivery")
	public RiderDeliveryAcceptResponse acceptDelivery(@RequestBody RiderDeliveryAcceptRequest riderDeliveryAcceptRequest,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception {
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        checkRider(jwt);
		Deliveries delivery = riderDeliveryRepository.findByDeliveryidAndRideridIsNull(riderDeliveryAcceptRequest.getDeliveryid());
        riderDeliveryRepository.updateDeliveryRiderIdAndDeliveryStatus(riderDeliveryAcceptRequest.getDeliveryid(), Constants.delivery_status_Delivery_Scheduled, userid);
        // send notification to user about his deliveryorder accepted
        String userToken = profileRepository.findByUseridCustom( delivery.getUserid()).getToken();
        if(userToken!=null) {
        	sendNotificationToUserForAcceptance(delivery.getUserid(),delivery);
		}
        
        return new RiderDeliveryAcceptResponse(delivery.getStatus(),delivery.getDeliveryid());
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getAcceptedDeliveries")
	public RiderPendingDeliveriesResponse getAcceptedDeliveries(@RequestHeader (name="Authorization") String authorizationHeader) throws Exception {
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        checkRider(jwt);
        List<DeliverResponseWithOriginAddress> acceptedDeliveries = riderDeliveryJoinRepository.findByRiderid(userid,"DELIVERY_SCHEDULED");
        return new RiderPendingDeliveriesResponse(acceptedDeliveries);
	}
	
	private void sendNotificationToUserForAcceptance(String userid,Deliveries delivery) throws FirebaseMessagingException {
		
		String usertoken = profileRepository.findByUseridCustom(userid).getToken();
		if(usertoken!=null) {
			Map<String, String> notemap = new HashMap<String, String>();
			
    		notemap.put("deliveryId", delivery.getDeliveryid().toString());
    		notemap.put("type", Constants.delivery_status_Delivery_Scheduled);
    		notemap.put("pickuptime", delivery.getPickuptime());
    		notemap.put("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING);
    		
    		Note note = new Note(Constants.delivery_scheduled_notification_title_string,String.format(Constants.delivery_scheduled_notification_description_string, delivery.getPickuptime()),notemap,null);
    		
    		notifUtil.sendNotificationToUser(note, usertoken);
		}
	}
	private void checkRider(String jwt) throws Exception {
		 String role = jwtUtil.extractRole(jwt);
		 System.out.println("Role is "+role);
		 if(role.equals("RIDER")) return ;
		 throw new Exception("Not Authorized");
	}
	
}
