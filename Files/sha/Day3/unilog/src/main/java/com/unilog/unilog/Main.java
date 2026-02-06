package com.unilog.unilog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        // 1. Start the EntityManagerFactory (Loads persistence.xml settings)
        // This is like "Turning on the Hibernate Engine"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unilog-pu");
        EntityManager em = emf.createEntityManager();

        // 2. Begin Transaction
        // Databases require transactions for safe writing (ACID properties)
        em.getTransaction().begin();

        // 3. Create Data (Pure Java - No SQL)
        Address addr = new Address("123 Java Lane", "Code City", "90210");
        Student student1 = new Student("Alice Coder", "alice@example.com", addr);

        System.out.println("Saving Student to Database...");

        // 4. THE MAGIC: Save the object
        // Hibernate translates this into: INSERT INTO student_records ...
        em.persist(student1);

        // 5. Commit the transaction (Send to DB)
        em.getTransaction().commit();

        System.out.println("--------------------------------------");
        System.out.println("Generated SQL above? ^^^");
        System.out.println("Saved Object ID: " + student1.getId());
        System.out.println("--------------------------------------");

        // Cleanup
        em.close();
        emf.close();
    }
}
