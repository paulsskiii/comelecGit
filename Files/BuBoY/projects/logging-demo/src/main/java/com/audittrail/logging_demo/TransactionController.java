package com.audittrail.logging_demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/pay")
    public String pay(@RequestParam String user, @RequestParam double amount) {
        log.info("Received API Request at /pay"); // Logs the entry point
        transactionService.processTransaction(user, amount);
        return "Check your server console/logs for output!";
    }
}
