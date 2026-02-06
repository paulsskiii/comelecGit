package com.audittrail.logging_demo;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
    
@Service
@Slf4j // Concept: Lombok generates 'private static final Logger log' for us
public class TransactionService {

    public void processTransaction(String user, double amount) {
        // TRACE: Granular flow tracking (usually invisible in Prod)
        log.trace("Entering processTransaction method...");

        // DEBUG: Diagnostic data for developers
        // Concept: Parameterized logging "{}" prevents memory waste if DEBUG is off
        log.debug("Request details - User: {}, Amount: ${}", user, amount);

        try {
            // Simulate Business Logic
            if (amount < 0) {
                throw new IllegalArgumentException("Cannot process negative amount");
            }

            if (amount > 10000) {
                // WARN: Potential issue, but app continues running
                log.warn("High value transaction detected! Amount: ${}. Flagging for audit.", amount);
            }

            // INFO: Business as usual (Success)
            log.info("Transaction processed successfully for user: {}", user);

        } catch (Exception e) {
            // ERROR: Action required. Application failed a task.
            // We pass 'e' as the last argument to print the StackTrace properly.
            log.error("Transaction FAILED for user: {}", user, e);
        }
    }

}
