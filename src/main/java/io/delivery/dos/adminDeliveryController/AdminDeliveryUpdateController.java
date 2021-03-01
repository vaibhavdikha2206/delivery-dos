package io.delivery.dos.adminDeliveryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.delivery.admin.GetDeliveriesScheduleRequest;
import io.delivery.dos.models.delivery.admin.GetDeliveriesScheduleResponse;
import io.delivery.dos.models.delivery.availability.AvailabilityRequest;
import io.delivery.dos.models.delivery.userdeliveries.GetDeliveriesListResponse;
import io.delivery.dos.models.status.StatusUpdateRequestObject;
import io.delivery.dos.models.status.StatusUpdateResponseObject;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.repositories.delivery.AdminDeliveriesRepository;
import io.delivery.dos.repositories.rider.RiderRepository;
import io.delivery.dos.security.util.JwtUtil;
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


	@RequestMapping(method=RequestMethod.POST,value="/getSchedule")
	public GetDeliveriesScheduleResponse getUsersDeliveryList(@RequestHeader (name="Authorization") String authorizationHeader,@RequestBody GetDeliveriesScheduleRequest getDeliveriesScheduleRequestObject) throws Exception { 
		// get drivers engaged during requested time
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        checkAdmin(jwt);
        
        // free riders pickuptime
        List<ProfileResponse> freeriders=notifUtil.getFreeDrivers(getDeliveriesScheduleRequestObject.getPickuptime());
        
        //get schedule at pickuptime
        List<Deliveries> deliveries = adminDeliveriesRepository.findByPickuptimeWhereRideridIsNotnull(getDeliveriesScheduleRequestObject.getPickuptime());
        
        
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
