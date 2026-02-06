package com.quest.questledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.questledger.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
