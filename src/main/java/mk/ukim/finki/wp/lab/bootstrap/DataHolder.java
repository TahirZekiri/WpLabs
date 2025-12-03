package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        if (authorRepository.count() == 0) {
            Author author1 = authorRepository.save(new Author("Author", "one", "Europe", "biography"));
            Author author2 = authorRepository.save(new Author("Author", "two", "Europe", "biography"));
            Author author3 = authorRepository.save(new Author("Author", "three", "Europe", "biography"));

            if (bookRepository.count() == 0) {
                bookRepository.save(new Book("Book1", "History", 9.0, author1));
                bookRepository.save(new Book("Book2", "History", 8.5, author2));
                bookRepository.save(new Book("Book3", "History", 8.0, author3));
                bookRepository.save(new Book("Book4", "History", 7.5, author1));
                bookRepository.save(new Book("Book5", "History", 7.0, author2));
                bookRepository.save(new Book("Book6", "History", 6.5, author3));
                bookRepository.save(new Book("Book7", "History", 6.0, author1));
                bookRepository.save(new Book("Book8", "History", 5.5, author2));
                bookRepository.save(new Book("Book9", "History", 5.0, author3));
                bookRepository.save(new Book("Book10", "History", 9.9, author1));
            }
        }
    }
}
