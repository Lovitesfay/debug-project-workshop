package nl.pluralsight.stagepass.service;

import nl.pluralsight.stagepass.model.Venue;
import nl.pluralsight.stagepass.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new RuntimeException("Venue with id " + id + " not found!"));
    }

    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue updateVenue(Long id, Venue updatedVenue) {
        Venue existing = getVenueById(id);
            existing.setName(updatedVenue.getName());
            existing.setCity(updatedVenue.getCity());
            existing.setCapacity(updatedVenue.getCapacity());
            return venueRepository.save(existing);

    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
    }

