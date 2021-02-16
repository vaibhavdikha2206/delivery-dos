package io.delivery.dos.service.fcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.*;

import io.delivery.dos.constants.Constants;
import io.delivery.dos.models.address.Address;
import io.delivery.dos.models.delivery.Deliveries;
import io.delivery.dos.repositories.address.AddressRepository;
import io.delivery.dos.service.fcm.model.Note;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@Autowired
	AddressRepository addressRepository;
	
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

    public String sendNotificationToMultipleRiders(List<String> registrationTokens,Deliveries delivery) throws FirebaseMessagingException {
    	//only for sending notification to riders yet
    	MulticastMessage message = MulticastMessage.builder()
    			.putAllData(null)
    		    .putData("deliveryId", delivery.getDeliveryid().toString())
    		    .putData("type", delivery.getStatus())
    		    .putData("pickupTime", delivery.getPickuptime())
    		    .addAllTokens(registrationTokens)
    		    .build();
    		BatchResponse response = firebaseMessaging.sendMulticast(message);
    		// See the BatchResponse reference documentation
    		
    		System.out.println(response.getSuccessCount() + " messages were sent successfully");
    	
    		return response.getSuccessCount() + " messages were sent successfully";
    }
    
}
