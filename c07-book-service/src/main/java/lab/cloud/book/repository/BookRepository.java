package lab.cloud.book.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lab.cloud.book.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,String>  {
    public List<Book> findByAuthorId(String authorId);

    public Book findByAuthorIdAndBookId(String authorId,String bookId);
}
