package cc.maids.library_test.controller;

import cc.maids.library_test.entity.Book;
import cc.maids.library_test.entity.Patron;
import cc.maids.library_test.exception.ResourceNotFoundException;
import cc.maids.library_test.exception.ValidationException;
import cc.maids.library_test.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
@Validated
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patron = patronService.getPatronById(id);
        return patron.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patron addPatron(@RequestBody @Valid Patron patron) {
        return patronService.addPatron(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody @Valid Patron patron) {
            Patron updatedPatron =  patronService.updatePatron(id, patron);
            return ResponseEntity.ok(updatedPatron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
            patronService.deletePatron(id);
            return ResponseEntity.noContent().build();
    }
}
