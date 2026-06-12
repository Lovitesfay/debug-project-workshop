package nl.pluralsight.stagepass.controller;

import jakarta.validation.Valid;
import nl.pluralsight.stagepass.model.Concert;
import nl.pluralsight.stagepass.service.BookingService;
import nl.pluralsight.stagepass.service.ConcertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
@CrossOrigin(origins = "*")
public class ConcertController {

    private final ConcertService concertService;
    private final BookingService bookingService;

    public ConcertController(ConcertService concertService, BookingService bookingService) {
        this.concertService = concertService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Concert>> getAllConcerts() {
        List<Concert> concerts = concertService.getAllConcerts();
        return ResponseEntity.ok(concertService.getAllConcerts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concert> getConcertById(@PathVariable Long id) {
        Concert concert = concertService.getConcertById(id);
        return ResponseEntity.ok(concert);
    }

    @PostMapping
    public ResponseEntity<Concert> createConcert(@RequestBody Concert concert) {
        Concert created = concertService.createConcert(concert);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable Long id, @RequestBody Concert concert) {
        Concert updated = concertService.updateConcert(id, concert);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcert(@PathVariable Long id) {
        concertService.deleteConcert(id); {
        return ResponseEntity.noContent().build();
        }

    }

}
