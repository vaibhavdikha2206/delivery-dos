package io.delivery.dos.controllers;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.delivery.dos.models.delivery.availability.AvailabilityRequest;
import io.delivery.dos.models.delivery.availability.AvailabilityResponse;
import io.delivery.dos.models.maps.Maps;
import io.delivery.dos.models.maps.request.DistanceRequest;
import io.delivery.dos.models.maps.response.DistanceResponseWithAmount;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.rider.RiderRepository;
import io.delivery.dos.utils.DateTimeUtil;
import io.delivery.dos.constants.Constants;

@RestController
public class DeliveryController {

	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private RiderRepository riderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.POST,value="/checkAvailable")
	public AvailabilityResponse checkAvailability(@RequestBody AvailabilityRequest availabilityRequestObject,@RequestHeader (name="Authorization") String authorizationHeader) { 
		// get drivers engaged during requested time
		System.out.println("object is "+availabilityRequestObject.getRequestDateTime());
		Timestamp requestTime=DateTimeUtil.convertStringToTimestamp(availabilityRequestObject.getRequestDateTime());
		Timestamp upperDateTime = new Timestamp(requestTime.getTime() + TimeUnit.MINUTES.toMillis(30));
		Timestamp lowerDateTime = new Timestamp(requestTime.getTime() - TimeUnit.MINUTES.toMillis(30));
		System.out.println("requested time was "+requestTime+", upperDateTime was "+upperDateTime+" ,lowerDateTime was "+lowerDateTime);
		Integer busyDrivers = deliveriesRepository.getNumberOfBusyRiders(lowerDateTime.toString(), upperDateTime.toString());
		System.out.println("busy drivers are "+busyDrivers+" out of "+riderRepository.count());
		
		if(riderRepository.count()>deliveriesRepository.getNumberOfBusyRiders(lowerDateTime.toString(), upperDateTime.toString()))
			return new AvailabilityResponse(true) ;
		else 
			return new AvailabilityResponse(false);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getAmount")
	public DistanceResponseWithAmount checkDistance(@RequestBody DistanceRequest distanceRequest,@RequestHeader (name="Authorization") String authorizationHeader) { 
		// get drivers engaged during requested time
		Maps mapval=caclulateDistance(distanceRequest.getOriginLat(),distanceRequest.getOriginLong(),distanceRequest.getDestinationLat(),distanceRequest.getDestinationLong());
		return new DistanceResponseWithAmount(calculateAmount(getValueInMetres(mapval)));
	}
	
	
	private double getValueInMetres(Maps mapObject) {
		return mapObject.getRows().get(0).getElements().get(0).getDistance().getValue();	
	}
	
	private double calculateAmount(Double distanceInMetres) {
		return (distanceInMetres/1000)*5;
	}
	
	private Maps caclulateDistance(Double originLat,Double originLong,Double destinationLat,Double destinationLong) {
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json";
		
		String.format("%s;, %s;", originLat, originLong);
		
		// Query parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
		        // Add query parameter
		        .queryParam("units", Constants.keyMetric)
		        .queryParam("origins", String.format("%f, %f", originLat, originLong))
		        .queryParam("destinations", String.format("%f, %f", destinationLat, destinationLong))
		        .queryParam("key", Constants.keyMap);
		
		System.out.println(builder.buildAndExpand().toUri());
		
		
	    return restTemplate.getForObject(builder.buildAndExpand().toUri(), Maps.class);
	}
}
