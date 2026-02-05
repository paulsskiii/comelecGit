package com.restfulsnacks.restfulsnacks;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/snacks")
public class SnackController {

    private final SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    // 1. GET ALL (200 OK)
    @GetMapping
    public List<Snack> getAllSnacks() {
        return snackService.findAll();
    }


// 2. GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<Snack> getSnackById(@PathVariable Long id) {
        Optional<Snack> snack = snackService.findById(id);
        
        if (snack.isPresent()) {
            return new ResponseEntity<>(snack.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST creates new resources (NOT Idempotent)
    @PostMapping
    public ResponseEntity<Snack> createSnack(@RequestBody Snack newSnack) {
        Snack savedSnack = snackService.save(newSnack);
        // Return 201 Created explicitly
        return new ResponseEntity<>(savedSnack, HttpStatus.CREATED);
    }


    // 4. PUT (200 OK - Full Update)
    @PutMapping("/{id}")
    public ResponseEntity<Snack> updateSnack(@PathVariable Long id, @RequestBody Snack snackUpdate) {
        Optional<Snack> existing = snackService.findById(id);
        if (existing.isPresent()) {
            Snack snack = existing.get();
            snack.setName(snackUpdate.getName());
            snack.setBrand(snackUpdate.getBrand());
            snack.setPrice(snackUpdate.getPrice());
            return new ResponseEntity<>(snack, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. PATCH (200 OK - Partial Update)
    @PatchMapping("/{id}")
    public ResponseEntity<Snack> updatePrice(@PathVariable Long id, @RequestBody double newPrice) {
        Optional<Snack> existing = snackService.findById(id);
        if (existing.isPresent()) {
            Snack snack = existing.get();
            snack.setPrice(newPrice);
            return new ResponseEntity<>(snack, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE removes the resource
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSnack(@PathVariable Long id) {
        boolean deleted = snackService.delete(id);
        
        if (deleted) {
            // 204 No Content (Success, but nothing to return)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

