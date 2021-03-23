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
		
		System.out.println("builder is "+builder.buildAndExpand().toUri());
		
		
	    return restTemplate.getForObject(builder.buildAndExpand().toUri(), Maps.class);
	}
	
	
	public int getAmountFromParamsInPaisa(Address originAddress,double destinationLat,double destinationLong,int weightCategory, boolean isDelicate, boolean isBalloonAdded, boolean isBouqetAdded, boolean isTwoCake) {
		System.out.println("origin address "+originAddress.getLatitude()+","+originAddress.getLongitude());
		System.out.println("destination address "+destinationLat+","+destinationLong);
		System.out.println("isDelicate "+isDelicate);
		Maps mapval=caclulateDistance(originAddress.getLatitude(),originAddress.getLongitude(),destinationLat,destinationLong);
		return calculateAmountInPaisa(getValueInMetres(mapval),weightCategory,isDelicate,isBalloonAdded,isBouqetAdded,isTwoCake);
	}
	
	private int calculateAmountInPaisa(Double distanceInMetres,int weightCategory, boolean isDelicate, boolean isBalloonAdded, boolean isBouqetAdded, boolean isTwoCake) {
		// convert distance to km then multiply by price per km (in paisa) + 10 rs service charge
		// on top of that metric for weightCategory also Add
		System.out.println("distanceInMetres  "+distanceInMetres);
		return (int) Math.round(200);
		/*return (int) Math.round( Constants.basePriceInPaisa + ((getDistanceForAmountCalculation(distanceInMetres))*(1000))+ getAmountInPaisaForWeightCategory(weightCategory) 
				//extra items pricing
				+ getAmountForDelicateCake(isDelicate) + getAmountForExtraBalloon(isBalloonAdded) + getAmountForExtraBouqet(isBouqetAdded) + getAmountForExtraTwoCake(isTwoCake)
				);*/
	}
	
	private int getAmountForDelicateCake(boolean isDelicate) {
		if(isDelicate) return 3000;
		else return 0;
	}
	
	private int getAmountForExtraBalloon(boolean isBalloonAdded) {
		if(isBalloonAdded) return 2000;
		else return 0;
	}
	
	private int getAmountForExtraBouqet(boolean isBouqetAdded) {
		if(isBouqetAdded) return 3000;
		else return 0;
	}
	
	private int getAmountForExtraTwoCake(boolean isTwoCake) {
		if(isTwoCake) return 4000;
		else return 0;
	}
	
	private int getAmountInPaisaForWeightCategory(int weightCategory) {
		switch(weightCategory) {
		case 1 : return 0;
		case 2 : return 3000;
		default: return 5000;
		}
	}
	
	private double getDistanceForAmountCalculation(Double distanceInMetres) {
		
		if(((distanceInMetres/1000)-2)>=0) {
			return ((distanceInMetres/1000)-2) ;
		}
		else {
			return 0;
		}
	}
	
	private double getValueInMetres(Maps mapObject) {
		System.out.println("mapObject  "+mapObject.getRows().get(0).getElements().get(0).getDistance().getValue());
		return mapObject.getRows().get(0).getElements().get(0).getDistance().getValue();	
	}
}
