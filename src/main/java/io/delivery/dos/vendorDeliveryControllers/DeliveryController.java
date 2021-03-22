package io.delivery.dos.vendorDeliveryControllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.delivery.availability.AvailabilityRequest;
import io.delivery.dos.models.delivery.availability.AvailabilityResponse;
import io.delivery.dos.models.delivery.userdeliveries.GetDeliveriesListResponse;
import io.delivery.dos.models.delivery.userdeliveries.GetSingleDeliveryResponse;
import io.delivery.dos.models.maps.Maps;
import io.delivery.dos.models.maps.request.DistanceRequest;
import io.delivery.dos.models.maps.response.DistanceResponseWithAmount;
import io.delivery.dos.repositories.address.AddressRepository;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.rider.RiderRepository;
import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.utils.DateTimeUtil;
import io.delivery.dos.utils.MapsUtil;
import io.delivery.dos.utils.RazorPayUtil;
import io.delivery.dos.constants.Constants;

@RestController
public class DeliveryController {

	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private RiderRepository riderRepository;
	
	@Autowired
	private RazorPayUtil razorPayUtil;
	
	@Autowired
	private MapsUtil mapsUtil;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
    private JwtUtil jwtUtil;
	
	@RequestMapping(method=RequestMethod.POST,value="/checkAvailable")
	public AvailabilityResponse checkAvailability(@RequestBody AvailabilityRequest availabilityRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) { 
		// get drivers engaged during requested time
		System.out.println("object is "+availabilityRequestObject.getRequestDateTime());
		Timestamp requestTime=DateTimeUtil.convertStringToTimestamp(availabilityRequestObject.getRequestDateTime());
		Timestamp upperDateTime = new Timestamp(requestTime.getTime() + TimeUnit.MINUTES.toMillis(30));
		Timestamp lowerDateTime = new Timestamp(requestTime.getTime() - TimeUnit.MINUTES.toMillis(30));
		System.out.println("requested time was "+requestTime+", upperDateTime was "+upperDateTime+" ,lowerDateTime was "+lowerDateTime);
		
		System.out.println("lowertime is "+lowerDateTime.toString()+", upper is "+upperDateTime.toString());
		
		Integer busyDrivers = deliveriesRepository.getNumberOfBusyRiders(lowerDateTime.toString(), upperDateTime.toString());
		System.out.println("busy drivers are "+busyDrivers+" out of "+riderRepository.findCountByRole("RIDER"));
		
		if(riderRepository.findCountByRole("RIDER")>deliveriesRepository.getNumberOfBusyRiders(lowerDateTime.toString(), upperDateTime.toString()))
			return new AvailabilityResponse(true) ;
		else 
			return new AvailabilityResponse(false);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getAmount")
	public DistanceResponseWithAmount checkDistance(@RequestBody DistanceRequest distanceRequest,@RequestHeader (name="Authorization") String authorizationHeader) { 
		// get drivers engaged during requested time
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        
        System.out.println("getamount for "+userid+","+distanceRequest.getAddressid()+",weightCat "+distanceRequest.getWeightcategory()+", isDelicate "+distanceRequest.isDelicate());
        
        Address originAddress = addressRepository.findOneByUseridAndAddressid(userid, distanceRequest.getAddressid());
        
        return new DistanceResponseWithAmount(
        		razorPayUtil.convertPaisaToRs(mapsUtil.getAmountFromParamsInPaisa(originAddress,distanceRequest.getDestinationLat(),distanceRequest.getDestinationLong(),distanceRequest.getWeightcategory(),
        				distanceRequest.isDelicate(),distanceRequest.isBalloonAdded(),distanceRequest.isBouqetAdded(),distanceRequest.isTwoCakes()
        				))
        		);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getDeliveriesList")
	public GetDeliveriesListResponse getUsersDeliveryList(@RequestHeader (name="Authorization") String authorizationHeader) { 
		// get drivers engaged during requested time
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        
        List<Deliveries> deliveries = deliveriesRepository.findByUseridOrderbypickuptime(userid);
        
        return new GetDeliveriesListResponse(deliveries);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getDelivery/{deliverid}")
	public GetSingleDeliveryResponse getUserDelivery(@PathVariable int deliverid,@RequestHeader (name="Authorization") String authorizationHeader) { 
		// get drivers engaged during requested time
		String jwt = authorizationHeader.substring(7);
        String userid = jwtUtil.extractUsername(jwt);
        
        Deliveries delivery = deliveriesRepository.findByUseridAndDeliveryid(userid,deliverid);
        
        return new GetSingleDeliveryResponse(delivery);
	}
	
}
