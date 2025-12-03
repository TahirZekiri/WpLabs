package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String searchText,
                               @RequestParam(required = false) String searchRating,
                               Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        
        List<Book> books;
        
        // If search parameters are provided, we use searchBooks, otherwise we use listAll
        if ((searchText != null && !searchText.trim().isEmpty()) || 
            (searchRating != null && !searchRating.trim().isEmpty())) {
            
            String text = (searchText != null) ? searchText : "";
            
            Double rating = null;
            if (searchRating != null && !searchRating.trim().isEmpty()) {
                try {
                    rating = Double.parseDouble(searchRating);
                } catch (NumberFormatException e) {
                    rating = null;
                }
            }
            
            books = bookService.searchBooks(text, rating);
        } else {
            books = bookService.listAll();
        }
        
        model.addAttribute("books", books);
        model.addAttribute("searchText", searchText);
        model.addAttribute("searchRating", searchRating);
        return "listBooks";
    }

    @GetMapping("/filter-rating")
    public String filterByRating(@RequestParam String rating, Model model) {
        Double ratingValue = null;
        try {
            ratingValue = Double.parseDouble(rating);
        } catch (NumberFormatException e) {
            return "redirect:/books?error=InvalidRating";
        }
        
        List<Book> books = bookService.searchBooks("", ratingValue);
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", null);
        return "book-form";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/books?error=BookNotFound";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.save(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}

