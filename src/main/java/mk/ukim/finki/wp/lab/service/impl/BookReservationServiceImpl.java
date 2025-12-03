package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository bookReservationRepository;
    private final BookRepository bookRepository;

    public BookReservationServiceImpl(BookReservationRepository bookReservationRepository, BookRepository bookRepository) {
        this.bookReservationRepository = bookReservationRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookReservation placeReservation(Long bookId, String readerName, String readerAddress, int numberOfCopies) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BookReservation reservation = new BookReservation(book, readerName, readerAddress, (long) numberOfCopies);
        return bookReservationRepository.save(reservation);
    }
}
