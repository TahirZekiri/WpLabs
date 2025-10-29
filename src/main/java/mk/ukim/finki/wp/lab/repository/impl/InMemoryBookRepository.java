package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository {

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(book -> {
                    boolean matchesText = text == null || text.isEmpty() || 
                                         book.getTitle().toLowerCase().contains(text.toLowerCase());
                    boolean matchesRating = rating == null || book.getAverageRating() >= rating;
                    return matchesText && matchesRating;
                })
                .toList();
    }
}
