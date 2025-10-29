package mk.ukim.finki.wp.lab.config;

import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.web.servlets.BookListServlet;
import mk.ukim.finki.wp.lab.web.servlets.BookReservationServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<BookListServlet> bookListServlet(
            SpringTemplateEngine springTemplateEngine,
            BookService bookService) {
        ServletRegistrationBean<BookListServlet> bean = new ServletRegistrationBean<>(
                new BookListServlet(springTemplateEngine, bookService),
                "/"
        );
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean<BookReservationServlet> bookReservationServlet(
            SpringTemplateEngine springTemplateEngine,
            BookReservationService bookReservationService) {
        ServletRegistrationBean<BookReservationServlet> bean = new ServletRegistrationBean<>(
                new BookReservationServlet(springTemplateEngine, bookReservationService),
                "/bookReservation"
        );
        bean.setLoadOnStartup(1);
        return bean;
    }
}

