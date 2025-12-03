package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository {

    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId((long) (Math.random() * 1000));
            DataHolder.authors.add(author);
        } else {
            DataHolder.authors.removeIf(a -> a.getId().equals(author.getId()));
            DataHolder.authors.add(author);
        }
        return author;
    }

    @Override
    public void delete(String name) {
        DataHolder.authors.removeIf(c -> c.getName().equals(name));
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.authors.removeIf(a -> a.getId().equals(id));
    }

}

