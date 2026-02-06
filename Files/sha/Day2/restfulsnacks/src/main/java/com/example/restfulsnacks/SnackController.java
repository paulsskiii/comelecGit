package com.example.restfulsnacks;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController // Favors JSON over XML
@RequestMapping("/api/snacks") // Use Nouns, not verbs
public class SnackController {

    private final SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    // GET is for retrieving data
    @GetMapping
    public List<Snack> getAllSnacks() {
        return snackService.findAll();
    }

    // Handling 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Snack> getSnackById(@PathVariable Long id) {
        Optional<Snack> snack = snackService.findById(id);

        if (snack.isPresent()) {
            return new ResponseEntity<>(snack.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // POST creates new resources (NOT Idempotent)
    @PostMapping
    public ResponseEntity<Snack> createSnack(@RequestBody Snack newSnack) {
        Snack savedSnack = snackService.save(newSnack);
        // Return 201 Created explicitly
        return new ResponseEntity<>(savedSnack, HttpStatus.CREATED);
    }

    // PUT is a full replacement
    @PutMapping("/{id}")
    public ResponseEntity<Snack> updateSnack(@PathVariable Long id, @RequestBody Snack snackUpdate) {
        Optional<Snack> existing = snackService.findById(id);

        if (existing.isPresent()) {
            Snack snack = existing.get();
            // Complete override of all fields
            snack.setName(snackUpdate.getName());
            snack.setBrand(snackUpdate.getBrand());
            snack.setPrice(snackUpdate.getPrice());
            return new ResponseEntity<>(snack, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PATCH is a partial update
    @PatchMapping("/{id}")
    public ResponseEntity<Snack> updatePrice(@PathVariable Long id, @RequestBody double newPrice) {
        Optional<Snack> existing = snackService.findById(id);

        if (existing.isPresent()) {
            Snack snack = existing.get();
            // Only updating the price, keeping name/brand intact
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
