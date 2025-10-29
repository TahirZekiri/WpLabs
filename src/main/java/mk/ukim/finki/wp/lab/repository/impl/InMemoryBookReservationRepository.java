package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {

    @Override
    public BookReservation save(BookReservation reservation) {
        // Remove any existing category with the same name before adding (prevents duplicates)
        // Note: We're using names as identifiers for simplicity
        // In later lectures you'll learn why unique IDs are the better approach!
        DataHolder.reservations.removeIf(c -> c.getBookTitle().equals(reservation.getBookTitle()));

        DataHolder.reservations.add(reservation);

        return reservation;
    }
}
