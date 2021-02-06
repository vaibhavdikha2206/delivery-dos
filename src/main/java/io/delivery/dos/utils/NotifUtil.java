package io.delivery.dos.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.firebase.messaging.FirebaseMessagingException;

import io.delivery.dos.service.fcm.NotificationService;
import io.delivery.dos.service.fcm.model.Note;

@Component
public class NotifUtil {

	@Autowired
	private NotificationService firebaseService;
	
	public String sendNotificationToUser(Note note,String token) throws FirebaseMessagingException {
		return firebaseService.sendNotification(note, token);	
	}
}
