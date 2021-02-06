package io.delivery.dos.service.fcm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.*;

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

    public String sendNotificationToMultipleUsers(List<String> registrationTokens) throws FirebaseMessagingException {
    	
    	MulticastMessage message = MulticastMessage.builder()
    		    .putData("type", "notification")
    		    .putData("title", "bharwa")
    		    .putData("body", "Hehehehehe")
    		    .addAllTokens(registrationTokens)
    		    .build();
    		BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
    		// See the BatchResponse reference documentation
    		// for the contents of response.
    		System.out.println(response.getSuccessCount() + " messages were sent successfully");
    	
    		return response.getSuccessCount() + " messages were sent successfully";
    		
    }
}
