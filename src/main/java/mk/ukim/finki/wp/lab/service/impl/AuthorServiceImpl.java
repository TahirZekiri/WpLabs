package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    private final AuthorRepository authorRepository;
    
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }


    @Override
    public Author save(Long authorId, String name, String surname, String country, String biography) {
        Author author = new Author(name, surname, country, biography);
        if (authorId != null) {
            author.setId(authorId);
        }
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long authorId, String name, String surname, String country, String biography) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        author.setBiography(biography);
        return authorRepository.save(author);
    }

    @Override
    public void delete(String name){
        authorRepository.findAll().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .ifPresent(authorRepository::delete);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}

