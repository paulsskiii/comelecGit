package com.example.Unilog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;

@SpringBootApplication
public class UnilogApplication implements org.springframework.boot.CommandLineRunner {

	@jakarta.persistence.PersistenceContext
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(UnilogApplication.class, args);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void run(String... args) throws Exception {
		// 3. Create Data (Pure Java - No SQL)
		Address addr = new Address("123 Java Lane", "Code City", "90210");
		Student student1 = new Student("Alice Coder", "alice@example.com", addr);

		System.out.println("Saving Student to Database...");

		// 4. THE MAGIC: Save the object
		// Hibernate translates this into: INSERT INTO student_records ...
		em.persist(student1);

		// Transaction is committed automatically at the end of this method
	}

}
