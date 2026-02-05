package com.quest.questledger;

import com.quest.questledger.entity.Player;
import com.quest.questledger.repository.AuditLogRepository;
import com.quest.questledger.repository.PlayerRepository;
import com.quest.questledger.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QuestRunner implements CommandLineRunner {

    @Autowired private GameService gameService;
    @Autowired private PlayerRepository playerRepo;
    @Autowired private AuditLogRepository auditRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========== RPG TRANSACTION SIMULATION ==========");

        // Setup Initial State
        Player p1 = playerRepo.save(new Player("Alice", 100, 50));
        Player p2 = playerRepo.save(new Player("Bob", 100, 50));

        System.out.println("Start State: " + playerRepo.findAll());

        // --- TEST 1: Dirty Checking ---
        System.out.println("\n--- Test 1: Dirty Checking (Healing Alice) ---");
        gameService.healPlayer(p1.getId(), 50);
        // Re-fetch from DB to prove it updated
        Player healedAlice = playerRepo.findById(p1.getId()).orElseThrow();
        System.out.println("Alice's Health (Should be 100): " + healedAlice.getHealth());

        // --- TEST 2: Atomicity (Failed Transfer) ---
        System.out.println("\n--- Test 2: Atomicity (Transfer 2000g - Fails) ---");
        try {
            gameService.transferGold(p1.getId(), p2.getId(), 2000);
        } catch (RuntimeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        // Check Balances: Should be unchanged because of Rollback
        System.out.println("Balances (Should still be 100/100): " + playerRepo.findAll());

        // --- TEST 3: Propagation & Rollback Rules ---
        System.out.println("\n--- Test 3: Propagation (Quest Fails, Audit Remains) ---");
        try {
            gameService.startQuest(p2.getId());
        } catch (Exception e) {
            System.out.println("Quest Error: " + e.getMessage());
        }

        // Verify Bob did NOT get gold (Rollback worked)
        Player bob = playerRepo.findById(p2.getId()).orElseThrow();
        System.out.println("Bob's Gold (Should be 100, not 150): " + bob.getGold());

        // Verify Audit Log EXIST (REQUIRES_NEW worked)
        System.out.println("Audit Log Count (Should be 1): " + auditRepo.count());
    }
}
