package io.delivery.dos.adminDeliveryController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.delivery.admin.AssignRiderRequest;
import io.delivery.dos.models.delivery.admin.AssignRiderResponse;
import io.delivery.dos.models.delivery.admin.GetDeliveriesScheduleRequest;
import io.delivery.dos.models.delivery.admin.GetDeliveriesScheduleResponse;
import io.delivery.dos.models.delivery.availability.AvailabilityRequest;
import io.delivery.dos.models.delivery.userdeliveries.GetDeliveriesListResponse;
import io.delivery.dos.models.status.StatusUpdateRequestObject;
import io.delivery.dos.models.status.StatusUpdateResponseObject;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.repositories.delivery.AdminDeliveriesRepository;
import io.delivery.dos.repositories.rider.RiderRepository;
import io.delivery.dos.repositories.riderdelivery.RiderDeliveryRepository;
import io.delivery.dos.repositories.user.ProfileRepository;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.service.fcm.model.Note;
import io.delivery.dos.utils.DeliveryStatusUtil;
import io.delivery.dos.utils.NotifUtil;

@RestController
public class AdminDeliveryUpdateController {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private AdminDeliveriesRepository adminDeliveriesRepository;
	
	@Autowired
	private DeliveryStatusUtil deliveryStatusUtil;
	
	//to get free riders
	@Autowired
	private NotifUtil notifUtil;

	@Autowired
	RiderDeliveryRepository riderDeliveryRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST,value="/assignRider")
	public AssignRiderResponse adminAssignRider(@RequestHeader (name="Authorization") String authorizationHeader,@RequestBody AssignRiderRequest assignRiderRequestObject) throws Exception { 
		// get drivers engaged during requested time
		String jwt = authorizationHeader.substring(7);
        checkAdmin(jwt);
        Deliveries delivery = riderDeliveryRepository.findByDeliveryid(assignRiderRequestObject.getDeliveryId());
        
        // assign rider and send notification to rider 
        riderDeliveryRepository.updateDeliveryRiderIdAndDeliveryStatus(assignRiderRequestObject.getDeliveryId(), Constants.delivery_status_Delivery_Scheduled, assignRiderRequestObject.getRiderid(),delivery.getLocationcode());
        sendNotificationToUserForAcceptanceUpdate(assignRiderRequestObject.getRiderid(),delivery);
                
        // notify vendor 
        sendNotificationToUserForAcceptanceUpdate(delivery.getUserid(),delivery);
        return new AssignRiderResponse(200);
	}

		private void sendNotificationToUserForAcceptanceUpdate(String userid,Deliveries delivery) throws FirebaseMessagingException {
		
		String usertoken = profileRepository.findByUseridCustom(userid).getToken();
		if(usertoken!=null) { 
			Map<String, String> notemap = new HashMap<String, String>();
			
    		notemap.put("deliveryId", delivery.getDeliveryid().toString());
    		notemap.put("type", Constants.delivery_status_Delivery_Scheduled_Updated);
    		notemap.put("pickuptime", delivery.getPickuptime());
    		notemap.put("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING);
    		
    		Note note = new Note(Constants.delivery_scheduled_notification_title_string,String.format(Constants.delivery_scheduled_notification_description_string, delivery.getPickuptime()),notemap,null);
    	
    		notifUtil.sendNotificationToUser(note, usertoken);
		}
	}

	@RequestMapping(method=RequestMethod.POST,value="/getSchedule")
	public GetDeliveriesScheduleResponse getUsersDeliveryList(@RequestHeader (name="Authorization") String authorizationHeader,@RequestBody GetDeliveriesScheduleRequest getDeliveriesScheduleRequestObject) throws Exception { 
		// get drivers engaged in particular location during requested time
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        checkAdmin(jwt);
        
        // free riders pickuptime
        List<ProfileResponse> freeriders=notifUtil.getFreeDrivers(getDeliveriesScheduleRequestObject.getPickuptime(),getDeliveriesScheduleRequestObject.getLocationcode());
        
        //get schedule at pickuptime
        List<Deliveries> deliveries = adminDeliveriesRepository.findByPickuptimeWhereRideridIsNotnull(getDeliveriesScheduleRequestObject.getPickuptime(),getDeliveriesScheduleRequestObject.getLocationcode());
        
        
        return new GetDeliveriesScheduleResponse(deliveries,freeriders);
	}

	@Transactional
	@RequestMapping(method=RequestMethod.POST,value="/adminUpdateStatus")
	public StatusUpdateResponseObject adminUpdateStatus(@RequestBody StatusUpdateRequestObject statusUpdateRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) throws Exception { 
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);  
        checkAdmin(jwt);
        
        System.out.println("going in ");
        switch(statusUpdateRequestObject.getStatus()) {
        case 200 : {
        	int value = deliveryStatusUtil.updateDeliveryStatusForAdmin(statusUpdateRequestObject.getDeliveryid(), Constants.delivery_status_Delivery_Success);
        	System.out.println("code is"+value);
        	notifUtil.sendUpdateToVendorForAdmin(statusUpdateRequestObject.getDeliveryid(),Constants.delivery_status_Delivery_Success);
        	return new StatusUpdateResponseObject( Constants.delivery_status_Delivery_Success );
        }
        
        case 201 : {
        	int value = deliveryStatusUtil.updateDeliveryStatusForAdmin(statusUpdateRequestObject.getDeliveryid(), Constants.delivery_status_Delivery_Ongoing);
        	System.out.println("code is"+value);
        	notifUtil.sendUpdateToVendorForAdmin(statusUpdateRequestObject.getDeliveryid(),Constants.delivery_status_Delivery_Ongoing);
        	return new StatusUpdateResponseObject( Constants.delivery_status_Delivery_Ongoing );
        }
        
        case 202 : {
        	int value = deliveryStatusUtil.updateDeliveryStatusForAdmin(statusUpdateRequestObject.getDeliveryid(), Constants.delivery_status_Delivery_Scheduling);
        	System.out.println("code is"+value);
        	notifUtil.sendUpdateToVendorForAdmin(statusUpdateRequestObject.getDeliveryid(),Constants.delivery_status_Delivery_Scheduling);
        	return new StatusUpdateResponseObject( Constants.delivery_status_Delivery_Scheduling );
        }
        
        case 204 : {
        	int value = deliveryStatusUtil.updateDeliveryStatusForAdmin(statusUpdateRequestObject.getDeliveryid(), Constants.delivery_status_Delivery_Scheduled);
        	System.out.println("code is"+value);
        	notifUtil.sendUpdateToVendorForAdmin(statusUpdateRequestObject.getDeliveryid(),Constants.delivery_status_Delivery_Scheduled);
        	return new StatusUpdateResponseObject( Constants.delivery_status_Delivery_Scheduled );
        }
        
        
        default : { 
        	throw new Exception("Status Code Invalid");
         }
     }
     
        
	}
	
	private void checkAdmin(String jwt) throws Exception {
		 String role = jwtUtil.extractRole(jwt);
		 System.out.println("Role is "+role);
		 if(role.equals("ADMIN")) return ;
		 throw new Exception("Not Authorized");
	}
	
}
