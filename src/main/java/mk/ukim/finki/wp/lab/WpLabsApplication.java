package mk.ukim.finki.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WpLabsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpLabsApplication.class, args);
    }

    //http://localhost:8080/h2-console
    //jdbc:h2:file:./data/booksdb
}