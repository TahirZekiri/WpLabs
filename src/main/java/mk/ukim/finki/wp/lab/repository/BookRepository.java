package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor_Id(Long authorId);
    
    @Query("SELECT b FROM Book b WHERE " +
           "(:text IS NULL OR :text = '' OR LOWER(b.title) LIKE LOWER(CONCAT('%', :text, '%'))) AND " +
           "(:rating IS NULL OR b.averageRating >= :rating)")
    List<Book> searchBooks(@Param("text") String text, @Param("rating") Double rating);
}
