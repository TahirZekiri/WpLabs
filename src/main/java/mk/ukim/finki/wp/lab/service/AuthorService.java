package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    List<Author> listAll();
    Optional<Author> findById(Long id);

    Author save(Long authorId, String name, String surname, String country, String biography);
    Author update(Long authorId, String name, String surname, String country, String biography);

    void delete(String name);

    void deleteById(Long id);

}

