package io.delivery.dos.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;

import io.delivery.dos.security.util.JwtUtil;
import io.delivery.dos.service.fcm.NotificationService;
import io.delivery.dos.service.fcm.model.Note;

@RestController
public class NotificationTestController {

	@Autowired
	private NotificationService firebaseService;

	@RequestMapping("/send-notification")
	@ResponseBody
	public String sendNotification(@RequestBody Note note,
	                               @RequestParam String token) throws FirebaseMessagingException {
		
		
		return firebaseService.sendNotification(note, token);
	}
	
}
