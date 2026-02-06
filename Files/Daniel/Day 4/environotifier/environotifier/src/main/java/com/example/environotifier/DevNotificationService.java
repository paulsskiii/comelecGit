package com.example.environotifier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // <--- This Bean only loads in the "dev" profile
public class DevNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        // Simple console log for local testing
        System.out.println("---------------------------------------------");
        System.out.println(" [DEV MOCK] Logged to console: " + message);
        System.out.println(" (No real email was sent)");
        System.out.println("---------------------------------------------");
    }
}
