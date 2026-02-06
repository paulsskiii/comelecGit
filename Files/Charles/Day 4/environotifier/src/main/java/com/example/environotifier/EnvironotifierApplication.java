package com.example.environotifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnvironotifierApplication implements CommandLineRunner {

    // Inject the service. Spring decides WHICH implementation 
    // (Dev or Prod) to inject based on the profile!
    @Autowired
    private NotificationService notificationService;

    // Inject the DB URL to see which properties file loaded
    @Value("${db.connection.url}")
    private String dbUrl;

	@Value("${app.secret.key}")
	private String secretKey;


    public static void main(String[] args) {
        SpringApplication.run(EnvironotifierApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n--- APP START ---");
        System.out.println("Database URL: " + dbUrl);
        System.out.println("Database Password: " + secretKey);
        
        // Use the service
        notificationService.send("Server Disk Space Low!");
        System.out.println("--- APP END ---\n");
    }
}
