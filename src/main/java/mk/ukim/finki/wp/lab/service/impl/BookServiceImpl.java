package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }
    
    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String genre, Double averageRating, Long authorId) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public void delete(String name){
        bookRepository.delete(bookRepository.findAll().stream()
                .filter(b -> b.getTitle().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found")));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

}
