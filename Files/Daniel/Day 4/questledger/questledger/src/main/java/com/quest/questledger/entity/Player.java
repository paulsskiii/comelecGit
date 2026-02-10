package com.quest.questledger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok: Generates Getters, Setters, toString
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int gold;
    private int health;

    public Player(String name, int gold, int health) {
        this.name = name;
        this.gold = gold;
        this.health = health;
    }
}
