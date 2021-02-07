package io.delivery.dos.service.fcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.*;

import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.service.fcm.model.Note;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final FirebaseMessaging firebaseMessaging;

    public NotificationService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotification(Note note, String token) throws FirebaseMessagingException {

        Notification notification = Notification
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
                .build();

        return firebaseMessaging.send(message);
    }

    public String sendNotificationToMultipleRiders(List<String> registrationTokens,String type,Deliveries delivery) throws FirebaseMessagingException {
    	
    	MulticastMessage message = MulticastMessage.builder()
    		    .putData("type", type)
    		    .putData("title", "New Delivery Request for "+delivery.getPickuptime())
    		    .putData("body", delivery.getDeliveryid().toString())
    		    .addAllTokens(registrationTokens)
    		    .build();
    		BatchResponse response = firebaseMessaging.sendMulticast(message);
    		// See the BatchResponse reference documentation
    		
    		System.out.println(response.getSuccessCount() + " messages were sent successfully");
    	
    		return response.getSuccessCount() + " messages were sent successfully";
    }
    
    @Async
    public void asyncTestFun() throws FirebaseMessagingException {
    	long pause = 5000 ; 
    	
    	try {
    		Thread.sleep(pause);
    	}
    	catch(Exception e){
    		
    	}
    	
    	
    	System.out.println("async task don notif sent");
    }
}
