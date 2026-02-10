package com.example.restfulsnacks;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SnackService {
    private List<Snack> inventory = new ArrayList<>();

    public SnackService() {
        inventory.add(new Snack(1L, "Chips", "Lays", 1.50));
        inventory.add(new Snack(2L, "Chocolate Bar", "Mars", 2.00));
    }

    public List<Snack> findAll() {
        return inventory;
    }

    public Optional<Snack> findById(Long id) {
        return inventory.stream()
                .filter(s -> Objects.equals(s.getId(), id))
                .findFirst();
    }

    public Snack save(Snack snack) {
        // If ID is provided, remove existing snack with that ID to avoid duplicates
        if (snack.getId() != null) {
            delete(snack.getId());
        }
        inventory.add(snack);
        return snack;
    }

    public boolean delete(Long id) {
        return inventory.removeIf(s -> Objects.equals(s.getId(), id));
    }
}
