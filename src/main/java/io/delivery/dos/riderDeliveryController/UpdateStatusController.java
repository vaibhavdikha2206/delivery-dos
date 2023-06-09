package io.delivery.dos.riderDeliveryController;

import java.util.HashMap;
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
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.status.StatusUpdateRequestObject;
import io.delivery.dos.models.status.StatusUpdateResponseObject;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.service.fcm.model.Note;
import io.delivery.dos.utils.DeliveryStatusUtil;
import io.delivery.dos.utils.NotifUtil;

@RestController
public class UpdateStatusController {

	@Autowired
	private DeliveryStatusUtil deliveryStatusUtil;
	
	@Autowired
    private JwtUtil jwtUtil;

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private NotifUtil notifUtil;
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST,value="/updateStatus")
	public StatusUpdateResponseObject updateStatus(@RequestBody StatusUpdateRequestObject statusUpdateRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception { 
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);  
        checkRider(jwt);
        
        System.out.println("going in ");
        switch(statusUpdateRequestObject.getStatus()) {
        case 200 : {
        	int value = deliveryStatusUtil.updateDeliveryStatusForRider(statusUpdateRequestObject.getDeliveryid(), Constants.delivery_status_Delivery_Success,userid);
        	System.out.println("code is"+value);
        	notifUtil.sendUpdateToVendorForRider(userid,statusUpdateRequestObject.getDeliveryid(),Constants.delivery_status_Delivery_Success);
        	return new StatusUpdateResponseObject( Constants.delivery_status_Delivery_Success );
        }
        
        case 201 : {
        	int value = deliveryStatusUtil.updateDeliveryStatusForRider(statusUpdateRequestObject.getDeliveryid(), Constants.delivery_status_Delivery_Ongoing,userid);
        	System.out.println("code is"+value);
        	notifUtil.sendUpdateToVendorForRider(userid,statusUpdateRequestObject.getDeliveryid(),Constants.delivery_status_Delivery_Ongoing);
        	return new StatusUpdateResponseObject( Constants.delivery_status_Delivery_Ongoing );
        }
        default : { 
        	throw new Exception("Status Code Invalid");
         }
     }
     
        
	}
	
	private void checkRider(String jwt) throws Exception {
		 String role = jwtUtil.extractRole(jwt);
		 System.out.println("Role is "+role);
		 if(role.equals("RIDER")) return ;
		 throw new Exception("Not Authorized");
	}
	
}
