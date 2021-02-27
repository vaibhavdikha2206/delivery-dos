package io.delivery.dos.adminDeliveryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.delivery.admin.GetDeliveriesScheduleRequest;
import io.delivery.dos.models.delivery.admin.GetDeliveriesScheduleResponse;
import io.delivery.dos.models.delivery.availability.AvailabilityRequest;
import io.delivery.dos.models.delivery.userdeliveries.GetDeliveriesListResponse;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.repositories.delivery.AdminDeliveriesRepository;
import io.delivery.dos.repositories.rider.RiderRepository;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.utils.NotifUtil;

@RestController
public class AdminDeliveryUpdateController {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private AdminDeliveriesRepository adminDeliveriesRepository;
	
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

	
	private void checkAdmin(String jwt) throws Exception {
		 String role = jwtUtil.extractRole(jwt);
		 System.out.println("Role is "+role);
		 if(role.equals("ADMIN")) return ;
		 throw new Exception("Not Authorized");
	}
	
}
