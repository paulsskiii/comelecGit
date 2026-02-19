package com.quest.questledger.service;

import com.quest.questledger.entity.AuditLog;
import com.quest.questledger.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {

    @Autowired
    private AuditLogRepository auditRepo;

    // REQUIRES_NEW: Suspend the current transaction (if any) and start a new one.
    // Even if the caller transaction rolls back later, this log remains saved.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAction(String action) {
        AuditLog log = new AuditLog(action);
        auditRepo.save(log);
        System.out.println(">>> AUDIT SAVED: " + action);
    }
}
