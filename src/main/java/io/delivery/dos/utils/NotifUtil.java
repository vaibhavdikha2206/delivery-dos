package io.delivery.dos.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.firebase.messaging.FirebaseMessagingException;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.models.rider.Riderdata;
import io.delivery.dos.models.user.response.ProfileResponse;
import io.delivery.dos.repositories.delivery.DeliveriesRepository;
import io.delivery.dos.repositories.rider.RiderRepository;
import io.delivery.dos.repositories.user.ProfileRepository;
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
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Async
	public String sendNotificationToUser(Note note,String token) throws FirebaseMessagingException {
		//System.out.println("sending notification to "+token);
		return firebaseService.sendNotification(note, token);	
	}
	
	@Async
	public void sendNotificationToFreeRiders(Deliveries delivery) throws FirebaseMessagingException {
		
		System.out.println("p1121ickup is "+delivery.getPickuptime());
		
		List<ProfileResponse> freeDrivers = getFreeDrivers(delivery.getPickuptime());
		
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
		firebaseService.sendNotificationToMultipleRiders(riderNotificationList,delivery);
		}
	}
	
	public List<ProfileResponse> getFreeDrivers(String pickuptime){
		
		Timestamp requestTime=DateTimeUtil.convertStringToTimestamp(pickuptime);
		Timestamp upperDateTime = new Timestamp(requestTime.getTime() + TimeUnit.MINUTES.toMillis(29));
		Timestamp lowerDateTime = new Timestamp(requestTime.getTime() - TimeUnit.MINUTES.toMillis(29));
		
		List<String> busyDrivers = deliveriesRepository.getBusyRiders(lowerDateTime.toString(), upperDateTime.toString());
		
		System.out.println("busy drivers"+busyDrivers);
		
		List<ProfileResponse> freeDrivers = new ArrayList<ProfileResponse>();
		if(busyDrivers.size()==0) {
			freeDrivers = riderRepository.findByRole("RIDER");
		}
		else {
			freeDrivers = riderRepository.findByUseridNotIn(busyDrivers,"RIDER");
		}
		
		return freeDrivers;
	}
	
	
	public void sendUpdateToVendorForRider(String riderid,int deliveryid,String notificationType) throws FirebaseMessagingException {
		
		Deliveries delivery = deliveriesRepository.findByRideridAndDeliveryid(riderid, deliveryid);
		System.out.println("printing is ");
		System.out.println("userid is "+delivery.getUserid());
		String usertoken = profileRepository.findByUseridCustom(delivery.getUserid()).getToken();
		if(usertoken!=null) {
			sendNotificationToUserForDeliverySuccess(delivery.getUserid(),delivery,usertoken,notificationType);
		}
		
	}
	
	public void sendUpdateToVendorForAdmin(int deliveryid,String notificationType) throws FirebaseMessagingException {
		
		Deliveries delivery = deliveriesRepository.findByDeliveryid(deliveryid);
		System.out.println("printing is ");
		System.out.println("userid is "+delivery.getUserid());
		String usertoken = profileRepository.findByUseridCustom(delivery.getUserid()).getToken();
		if(usertoken!=null) {
			sendNotificationToUserForDeliverySuccess(delivery.getUserid(),delivery,usertoken,notificationType);
		}
		
	}

	public void sendNotificationToUserForDeliverySuccess(String userid,Deliveries delivery,String usertoken,String notificationType) throws FirebaseMessagingException {
		
		//String usertoken = profileRepository.findByUseridCustom(userid).getToken();
		if(usertoken!=null) {
    		Map<String, String> notemap = new HashMap<String, String>();
    		notemap.put("deliveryId", delivery.getDeliveryid().toString());
    		notemap.put("type", notificationType);
    		notemap.put("pickuptime", delivery.getPickuptime());
    		notemap.put("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING);
    		
    		Note note = new Note(Constants.delivery_scheduling_notification_title_string,String.format(Constants.delivery_scheduling_notification_description_string, delivery.getPickuptime()),notemap,null);
    		
    		System.out.println("sending single notif to "+usertoken);
    		sendNotificationToUser(note, usertoken);
		}
	}
	
}
