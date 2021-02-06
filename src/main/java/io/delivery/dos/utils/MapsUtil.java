package io.delivery.dos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.maps.Maps;
import io.delivery.dos.models.maps.request.DistanceRequest;
import io.delivery.dos.models.maps.response.DistanceResponseWithAmount;
import io.delivery.dos.repositories.address.AddressRepository;

@Component
public class MapsUtil {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	AddressRepository addressRepository;
	
	public Maps caclulateDistance(Double originLat,Double originLong,Double destinationLat,Double destinationLong) {
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
	
	
	public int getAmountFromDistanceInPaisa(Address originAddress,double destinationLat,double destinationLong) {
		Maps mapval=caclulateDistance(originAddress.getLatitude(),originAddress.getLongitude(),destinationLat,destinationLong);
		return calculateAmountInPaisa(getValueInMetres(mapval));
	}
	
	private int calculateAmountInPaisa(Double distanceInMetres) {
		// convert distance to km then multiply by price per km (in paisa) + 10 rs service charge
		
		return (int) Math.round( ((distanceInMetres/1000)*(500))+(1000) );
	}
	
	private double getValueInMetres(Maps mapObject) {
		return mapObject.getRows().get(0).getElements().get(0).getDistance().getValue();	
	}
}
