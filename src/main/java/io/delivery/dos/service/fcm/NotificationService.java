package io.delivery.dos.service.fcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.*;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.repositories.address.AddressRepository;
import io.delivery.dos.service.fcm.model.Note;


@Service
public class NotificationService {

	@Autowired
	AddressRepository addressRepository;
	
    private final FirebaseMessaging firebaseMessaging;

    public NotificationService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(Note note, String token) throws FirebaseMessagingException {

        /*Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setToken(token)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();*/


        Message message = Message.builder()
        		.putAllData(note.getData())
        	    .putData("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING)
        	    .setToken(token)
        	    .build();

        
        return firebaseMessaging.send(message);
    }

    public String sendNotificationToMultipleUsersForCreditsUpdate(List<String> registrationTokens) {
    	//only for sending notification to riders yet
    	MulticastMessage message = MulticastMessage.builder()
    		    .putData("type", Constants.user_notification_Credits_added)
    		    .putData("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING)
    		    .addAllTokens(registrationTokens)
    		    .build();
    		BatchResponse response = null;
			try {
				response = firebaseMessaging.sendMulticast(message);
			} catch (FirebaseMessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("error is "+e);
			}
    		// See the BatchResponse reference documentation
    		
    		System.out.println(response.getSuccessCount() + " messages were sent successfully");	
    		return response.getSuccessCount() + " messages were sent successfully";
    }
    
    public String sendNotificationToMultipleRiders(List<String> registrationTokens,Deliveries delivery) {
    	//only for sending notification to riders yet
    	MulticastMessage message = MulticastMessage.builder()
    		    .putData("deliveryId", delivery.getDeliveryid().toString())
    		    .putData("type", Constants.delivery_status_Delivery_Scheduling)
    		    .putData("pickupTime", delivery.getPickuptime())
    		    .putData("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING)
    		    .addAllTokens(registrationTokens)
    		    .build();
    		BatchResponse response = null;
			try {
				response = firebaseMessaging.sendMulticast(message);
			} catch (FirebaseMessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("error is "+e);
			}
    		// See the BatchResponse reference documentation
    		
    		System.out.println(response.getSuccessCount() + " messages were sent successfully");
    	
    		return response.getSuccessCount() + " messages were sent successfully";
    }
   
    public String sendNotificationToMultipleAdmins(List<String> registrationTokens,Deliveries delivery,String notificationType) {
    	//only for sending notification to riders yet
    	MulticastMessage message = MulticastMessage.builder()
    		    .putData("deliveryId", delivery.getDeliveryid().toString())
    		    .putData("type", notificationType)
    		    .putData("pickupTime", delivery.getPickuptime())
    		    .putData("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING)
    		    .addAllTokens(registrationTokens)
    		    .build();
    		BatchResponse response = null;
			try {
				response = firebaseMessaging.sendMulticast(message);
			} catch (FirebaseMessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("error is "+e);
			}
    		// See the BatchResponse reference documentation
    		
    		System.out.println(response.getSuccessCount() + " messages were sent successfully");
    	
    		return response.getSuccessCount() + " messages were sent successfully";
    }
    
   public String sendNotificationToMultipleAdminsNoDeliveryObject(List<String> registrationTokens,String deliveryid,String pickuptime,String notificationType) {
    	//only for sending notification to riders yet
    	MulticastMessage message = MulticastMessage.builder()
    		    .putData("deliveryId", deliveryid)
    		    .putData("type", notificationType)
    		    .putData("pickupTime", pickuptime)
    		    .putData("click_action", Constants.FLUTTER_NOTIF_VALUE_STRING)
    		    .addAllTokens(registrationTokens)
    		    .build();
    		BatchResponse response = null;
			try {
				response = firebaseMessaging.sendMulticast(message);
			} catch (FirebaseMessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("error is "+e);
			}
    		// See the BatchResponse reference documentation
    		
    		System.out.println(response.getSuccessCount() + " messages were sent successfully");
    	
    		return response.getSuccessCount() + " messages were sent successfully";
    }
    
}
