package com.quest.questledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.questledger.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
