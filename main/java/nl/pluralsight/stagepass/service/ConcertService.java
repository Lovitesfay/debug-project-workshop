package nl.pluralsight.stagepass.service;

import nl.pluralsight.stagepass.model.Concert;
import nl.pluralsight.stagepass.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertService {

    private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    public Concert getConcertById(Long id) {
        return concertRepository.findById(id).orElseThrow(() -> new RuntimeException("Concert with id " + id + " not found"));
    }

    public Concert createConcert(Concert concert) {
        return concertRepository.save(concert);
    }

    public Concert updateConcert(Long id, Concert updatedConcert) {
        Concert existing = getConcertById(id);
            existing.setTitle(updatedConcert.getTitle());
            existing.setDate(updatedConcert.getDate());
            existing.setArtist(updatedConcert.getArtist());
            existing.setVenue(updatedConcert.getVenue());
            existing.setTotalSeats(updatedConcert.getTotalSeats());
            existing.setAvailableSeats(updatedConcert.getAvailableSeats());
            existing.setTicketPrice(updatedConcert.getTicketPrice());
            return concertRepository.save(existing);

    }

    public void deleteConcert(Long id) {

            concertRepository.deleteById(id);

    }


}
