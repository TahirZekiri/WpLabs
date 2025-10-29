package mk.ukim.finki.wp.lab.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get search parameters
        String searchText = req.getParameter("searchText");
        String searchRatingStr = req.getParameter("searchRating");
        String deleteBook = req.getParameter("deleteBook");

        List<Book> books;
        
        // If search parameters are provided, use searchBooks, otherwise use listAll
        if ((searchText != null && !searchText.trim().isEmpty()) || 
            (searchRatingStr != null && !searchRatingStr.trim().isEmpty())) {
            
            // Default to empty string if searchText is null
            String text = (searchText != null) ? searchText : "";
            
            // Default to 0.0 if searchRating is null or empty
            Double rating = 0.0;
            if (searchRatingStr != null && !searchRatingStr.trim().isEmpty()) {
                try {
                    rating = Double.parseDouble(searchRatingStr);
                } catch (NumberFormatException e) {
                    rating = 0.0;
                }
            }
            
            books = bookService.searchBooks(text, rating);
        } else if (deleteBook != null && !deleteBook.trim().isEmpty()) {
            bookService.delete(deleteBook);
            books = bookService.listAll();
        } else {
            books = bookService.listAll();
        }


        // Create Thymeleaf context
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        
        WebContext context = new WebContext(webExchange);
        context.setVariable("books", books);
        context.setVariable("searchText", searchText);
        context.setVariable("searchRating", searchRatingStr);
        
        // Set response content type
        resp.setContentType("text/html");
        
        // Process the template
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
