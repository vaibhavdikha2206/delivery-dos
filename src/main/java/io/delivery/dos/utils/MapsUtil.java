package io.delivery.dos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.maps.Maps;

@Component
public class MapsUtil {

	@Autowired
	private RestTemplate restTemplate;
	
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
	
}
