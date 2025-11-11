package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
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
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init() {
        authors.add(new Author("Author", "one", "Europe",
                "biography"));
        authors.add(new Author("Author", "two", "Europe",
                "biography"));
        authors.add(new Author("Author", "three", "Europe",
                "biography"));

        books.add(new Book("Book1", "History", 9.0, authors.get(0)));
        books.add(new Book("Book2", "History", 8.5, authors.get(1)));
        books.add(new Book("Book3", "History", 8.0, authors.get(2)));
        books.add(new Book("Book4", "History", 7.5, authors.get(0)));
        books.add(new Book("Book5", "History", 7.0, authors.get(1)));
        books.add(new Book("Book6", "History", 6.5, authors.get(2)));
        books.add(new Book("Book7", "History", 6.0, authors.get(0)));
        books.add(new Book("Book8", "History", 5.5, authors.get(1)));
        books.add(new Book("Book9", "History", 5.0, authors.get(2)));
        books.add(new Book("Book10", "History", 9.9, authors.get(0)));
    }
}
