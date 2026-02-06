package com.example.environotifier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod") // <--- This Bean only loads in the "prod" profile
public class ProdNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        // Simulates connecting to a real external server
        System.out.println("=============================================");
        System.out.println(" [PROD LIVE] Connecting to SMTP Server...");
        System.out.println(" [PROD LIVE] Sending EMAIL: " + message);
        System.out.println("=============================================");
    }
}
