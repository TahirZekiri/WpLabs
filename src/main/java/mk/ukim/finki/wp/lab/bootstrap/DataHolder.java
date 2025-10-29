package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// In-memory data holder
@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    // On application startup, initialize the in-memory lists with predefined data
    // On each startup, the lists will be initialized with the same values and
    // the previous values will be lost
    @PostConstruct
    public void init() {
        books.add(new Book("Book1", "History", 9.0));
        books.add(new Book("Book2", "History", 8.5));
        books.add(new Book("Book3", "History", 8.0));
        books.add(new Book("Book4", "History", 7.5));
        books.add(new Book("Book5", "History", 7.0));
        books.add(new Book("Book6", "History", 6.5));
        books.add(new Book("Book7", "History", 6.0));
        books.add(new Book("Book8", "History", 5.5));
        books.add(new Book("Book9", "History", 5.0));
        books.add(new Book("Book10", "History", 9.9));
    }
}
