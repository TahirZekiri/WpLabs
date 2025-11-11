package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    // Servlets have been replaced by controllers:
    // - BookListServlet replaced by BookController at /books
    // - BookReservationServlet replaced by BookReservationController at /bookReservation
    
    // All servlet registrations have been removed to allow controllers to handle requests
}

