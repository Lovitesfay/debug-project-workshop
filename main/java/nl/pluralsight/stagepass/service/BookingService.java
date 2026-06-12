package nl.pluralsight.stagepass.service;

import nl.pluralsight.stagepass.model.Booking;
import nl.pluralsight.stagepass.model.Concert;
import nl.pluralsight.stagepass.repository.BookingRepository;
import nl.pluralsight.stagepass.repository.ConcertRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ConcertRepository concertRepository;

    public BookingService(BookingRepository bookingRepository, ConcertRepository concertRepository) {
        this.bookingRepository = bookingRepository;
        this.concertRepository = concertRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById( Long id) {

        return bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public List<Booking> getBookingsByConcert(Long concertId) {

        return bookingRepository.findByConcertId(concertId);
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        Concert concert = concertRepository.findById(booking.getConcert().getId())
                .orElseThrow(() -> new RuntimeException("Concert not found"));

        // Compute total price
        booking.setTotalPrice(BigDecimal.ZERO);

        // Set booking date and concert reference
        booking.setBookingDate(LocalDate.now());
        booking.setConcert(concert);

        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);

        }
    }

