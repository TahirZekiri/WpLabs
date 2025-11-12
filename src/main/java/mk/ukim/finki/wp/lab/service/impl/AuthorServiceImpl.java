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
        Author author = new Author(authorId, name, surname, country, biography);

        return authorRepository.save(author);
    }

    @Override
    public Author update(Long authorId, String name, String surname, String country, String biography) {
        Author author = new Author(authorId, name, surname, country, biography);
        return authorRepository.save(author);
    }

    @Override
    public void delete(String name){
        this.authorRepository.delete(name);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}

