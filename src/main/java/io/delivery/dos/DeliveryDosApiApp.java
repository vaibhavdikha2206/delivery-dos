package io.delivery.dos;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import io.delivery.dos.models.security.KeyObject;
import io.delivery.dos.models.security.SecretObject;

@SpringBootApplication
@EnableAsync
public class DeliveryDosApiApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(DeliveryDosApiApp.class, args);

	}

	@Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "DeliverDos");
        return FirebaseMessaging.getInstance(app);
    }
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public KeyObject getAPIKey() {
		return new KeyObject("rzp_test_3tLQHIWIJrioiZ");
	}
	
	@Bean
	public SecretObject getAPISecret() {
		return new SecretObject("abuvHv2gRCFwsnMoWmTQr93O");
	}
	
	@Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }
}
