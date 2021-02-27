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
import io.delivery.dos.models.delivery.userdeliveries.GetDeliveriesListResponse;
import io.delivery.dos.repositories.delivery.AdminDeliveriesRepository;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.security.util.JwtUtil;


@RestController
public class AdminDataController {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private AdminDeliveriesRepository adminDeliveriesRepository;
	
	@RequestMapping(method=RequestMethod.GET,value="/getDeliveriesList/{status}")
	public GetDeliveriesListResponse getUsersDeliveryList(@RequestHeader (name="Authorization") String authorizationHeader,@PathVariable String status) throws Exception { 
		// get drivers engaged during requested time
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        checkAdmin(jwt);
        
        List<Deliveries> deliveries = adminDeliveriesRepository.findByStatusOrderbypickuptime(status);
        
        return new GetDeliveriesListResponse(deliveries);
	}

	
	private void checkAdmin(String jwt) throws Exception {
		 String role = jwtUtil.extractRole(jwt);
		 System.out.println("Role is "+role);
		 if(role.equals("ADMIN")) return ;
		 throw new Exception("Not Authorized");
	}
	
}
