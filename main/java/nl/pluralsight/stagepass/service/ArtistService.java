package nl.pluralsight.stagepass.service;

import nl.pluralsight.stagepass.model.Artist;
import nl.pluralsight.stagepass.model.Booking;
import nl.pluralsight.stagepass.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Value("${artists.auto-publish}")
    private boolean autoPublish;
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(Long id) {

        return artistRepository.findById(id).orElseThrow(() -> new RuntimeException("No artist found with id " + id));
    }

    public Artist createArtist(Artist artist) {

        return artistRepository.save(artist);
    }

    public Artist updateArtist(Long id, Artist updatedArtist) {
        Artist existing = getArtistById(id);
            existing.setName(updatedArtist.getName());
            existing.setGenre(updatedArtist.getGenre());
            existing.setBio(updatedArtist.getBio());
            return artistRepository.save(existing);

    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}
