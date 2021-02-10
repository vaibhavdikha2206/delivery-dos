package io.delivery.dos.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.firebase.messaging.FirebaseMessagingException;

import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.rider.Riderdata;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.rider.RiderRepository;
import io.delivery.dos.service.fcm.NotificationService;
import io.delivery.dos.service.fcm.model.Note;

@Component
public class NotifUtil {

	@Autowired
	private NotificationService firebaseService;
	
	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Autowired
	private RiderRepository riderRepository;
	
	@Async
	public String sendNotificationToUser(Note note,String token) throws FirebaseMessagingException {
		//System.out.println("sending notification to "+token);
		return firebaseService.sendNotification(note, token);	
	}
	
	@Async
	public void sendNotificationToFreeRiders(Deliveries delivery) throws FirebaseMessagingException {
		
		System.out.println("p1121ickup is "+delivery.getPickuptime());
		
		Timestamp requestTime=DateTimeUtil.convertStringToTimestamp(delivery.getPickuptime());
		Timestamp upperDateTime = new Timestamp(requestTime.getTime() + TimeUnit.MINUTES.toMillis(30));
		Timestamp lowerDateTime = new Timestamp(requestTime.getTime() - TimeUnit.MINUTES.toMillis(30));
		  	
		List<String> busyDrivers = deliveriesRepository.getBusyRiders(lowerDateTime.toString(), upperDateTime.toString());
		
		System.out.println("busy drivers"+busyDrivers);
		
		List<ProfileResponse> freeDrivers = new ArrayList<ProfileResponse>();
		if(busyDrivers.size()==0) {
			freeDrivers = riderRepository.findByRole("RIDER");
		}
		else {
			freeDrivers = riderRepository.findByUseridNotIn(busyDrivers,"RIDER");
		}
		
		
		List<String> riderNotificationList = new ArrayList<String>();
		
		for (ProfileResponse riderdata : freeDrivers) {
			System.out.println("freedriver "+riderdata.getUserid());
			if(riderdata.getToken()!=null)
			{
				System.out.println("sendmulti "+riderdata.getUserid()+","+riderdata.getToken());
				riderNotificationList.add(riderdata.getToken());
			
			}
		}
		
		System.out.println("sending notification to multicast");
		
		if(riderNotificationList.size()>0) {
		firebaseService.sendNotificationToMultipleRiders(riderNotificationList,"Notification",delivery);
		}
	}
	
	/*long pause = 5000 ; 
	
	try {
		Thread.sleep(pause);
	}
	catch(Exception e){
		
	}*/
}
