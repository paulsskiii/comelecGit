package com.quest.questledger.service;

import com.quest.questledger.entity.Player;
import com.quest.questledger.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;

@Service
public class GameService {

    @Autowired
    private PlayerRepository playerRepo;
    
    @Autowired
    private AuditService auditService; // We call another service to test propagation

    // --- 1. ATOMICITY ---
    // Either both balance updates happen, or neither happens.
    @Transactional
    public void transferGold(Long senderId, Long receiverId, int amount) {
        Player sender = playerRepo.findById(senderId).orElseThrow();
        Player receiver = playerRepo.findById(receiverId).orElseThrow();

        sender.setGold(sender.getGold() - amount);
        receiver.setGold(receiver.getGold() + amount);

        // We explicitly save here (though Dirty Checking would also work).
        playerRepo.save(sender);
        playerRepo.save(receiver); 
        
        // Simulating a crash AFTER the math is done but BEFORE method end
        if (amount > 1000) {
            throw new RuntimeException("System Crash! Transfer too large.");
        }
    }

    // --- 2. DIRTY CHECKING ---
    // Notice we do NOT call playerRepo.save().
    // Hibernate detects the change to the object within the transaction and updates DB automatically.
    @Transactional
    public void healPlayer(Long playerId, int healthAmount) {
        Player player = playerRepo.findById(playerId).orElseThrow();
        player.setHealth(player.getHealth() + healthAmount);
        System.out.println(">>> Player healed. No repo.save() called!");
    }

    // --- 3. ROLLBACK RULES & PROPAGATION ---
    // By default, Spring ONLY rolls back on RuntimeException.
    // We add rollbackFor = Exception.class to handle Checked Exceptions (like IOException) too.
    @Transactional(rollbackFor = Exception.class)
    public void startQuest(Long playerId) throws Exception {
        Player player = playerRepo.findById(playerId).orElseThrow();
        
        // 1. Audit log (Uses REQUIRES_NEW, so this saves immediately and stays saved)
        auditService.logAction("Player " + player.getName() + " started a quest.");

        // 2. Give quest reward (This is part of the current transaction)
        player.setGold(player.getGold() + 50);
        playerRepo.save(player);

        // 3. Simulate a failure
        // If we threw a RuntimeException here, rollback happens by default.
        // Because we are throwing a generic/checked Exception, we NEED the @Transactional(rollbackFor...) above.
        // Without that attribute, the player would keep the gold despite the error!
        throw new IOException("Quest Failed! Dragon appeared.");
    }
}
